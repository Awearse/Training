package TicTacToe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
	static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new ArrayList<Integer>();

	public static void main(String[] args) {
		
		char[][] board = {{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '},
				{'-', '+', '-', '+', '-'},
				{' ', '|', ' ', '|', ' '}};
		
		printBoard(board);
		
		Scanner scan = new Scanner(System.in);
		
		
		while(true) {
			System.out.println("Enter choice (1-9):");
			int playerPos = scan.nextInt();
			
			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("Positon taken! Enter a correct Position");
				playerPos = scan.nextInt();
			}
			placePiece(board, playerPos, "player");
			String result = checkWinner();
			if(result.length()>0) {
				printBoard(board);
				System.out.println(result);
				break;
			}
			
			Random r = new Random();
			int cpuPos = r.nextInt(9)+1;
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = r.nextInt(9)+1;
			}
			placePiece(board, cpuPos, "cpu");
			
			printBoard(board);
			
			result = checkWinner();
			if(result.length()>0) {
				printBoard(board);
				System.out.println(result);
				break;
			}

		}
	}
			
	
	public static void printBoard(char[][] board) {
		for(char[] row : board) {
			for(char c:row) {
				System.out.print(c);
			}
			System.out.println();
		}	
	}
	
	public static void placePiece(char[][] board, int pos, String user) {
		
		char symbol=' ';
		
		if(user.equals("player")) {
			symbol='X';
			playerPositions.add(pos);
		}else if(user.equals("cpu")) {
			symbol='O';
			cpuPositions.add(pos);
		}
		
		switch(pos) {
		case 1: 
			board[0][0] = symbol;
			break;
		case 2: 
			board[0][2] = symbol;
			break;
		case 3: 
			board[0][4] = symbol;
			break;
		case 4: 
			board[2][0] = symbol;
			break;
		case 5: 
			board[2][2] = symbol;
			break;
		case 6: 
			board[2][4] = symbol;
			break;
		case 7: 
			board[4][0] = symbol;
			break;
		case 8: 
			board[4][2] = symbol;
			break;
		case 9: 
			board[4][4] = symbol;
			break;
		default:
			break;
		}
	}
	
	public static String checkWinner() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);
		
		for(List l: winning) {
			if(playerPositions.containsAll(l)) {
				return "Congratulations player wins!";
			}else if(cpuPositions.contains(l)){
				return "CPU wins! Sorry!";
			}else if(playerPositions.size() + cpuPositions.size()==9) {
				return "This game is a draw!";
			}
	}
		
		
		return "";
		
	}
}