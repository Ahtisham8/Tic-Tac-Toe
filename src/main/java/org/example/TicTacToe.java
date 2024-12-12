package org.example; 

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char playerOneMark = 'X';
    static char playerTwoMark = 'O';
        public static void main(String[] args) {
            
            char[][] board = {{'1', '2', '3'}, 
                            {'4', '5', '6'},                    
                            {'7', '8', '9'}};
            Scanner scanner = new Scanner(System.in);
            
            gameMenu(board, scanner);
    
    
                
        }
    
        private static void playerVsComputer(char[][] board, Scanner scanner)
        {
    
            System.out.println("Let's Begin: \n");
            int computerTurn = 0;
            computerTurn = generateComputerMove(board, computerTurn);
    
            while (true) {
                printBoard(board);
                playerOneTurn(scanner, board);
    
                if (isGameDone(board, "The computer player", playerOneMark, playerTwoMark)) {
                    break;
                }
    
                computerTurn = generateComputerMove(board, computerTurn);
                makeComputerOrPlayerTwoMove(computerTurn, board, playerTwoMark);
            System.out.println("The computer has moved in space " + computerTurn);

            if (isGameDone(board, "The computer player", playerOneMark, playerTwoMark)) {
                break;
            } 
            
        }
    }

    private static int generateComputerMove(char[][] board, int computerTurn)
    {
        Random rand = new Random();
        
        while (true) {
            computerTurn = rand.nextInt(9) + 1;
            if (validSpot(computerTurn, board)) {
                return computerTurn;
            }
        }
    }

    private static void customCharacterPrompt(Scanner scanner)
    {
        boolean sameChar = false;
        System.out.println("Please choose the custom player marks:\n");
        System.out.println("Custom mark for Player One: ");
        String playerInput = scanner.nextLine();
        playerOneMark = validateCustomCharacter(scanner, playerInput);

        System.out.println("Custom mark for Player Two/Computer: ");
        playerInput = scanner.nextLine();
        playerTwoMark = validateCustomCharacter(scanner, playerInput);

        if (playerOneMark == playerTwoMark) {
            sameChar = true;
        }
        while(sameChar)
        {
            System.out.println("Please choose a different mark for Player Two/Computer: ");
            playerTwoMark = scanner.next().charAt(0);

            if (playerOneMark != playerTwoMark) {
                sameChar = false;
            }
        }
    }

    private static char validateCustomCharacter(Scanner scanner, String customMark)
    {       
        boolean invalidEntry = true;
        char customChar;

        while (invalidEntry) {
            if (customMark.length() != 1) {
                System.out.println("That was not a valid Character, please try again:");
                customMark = scanner.nextLine();

            }
            
            if (customMark.trim().isEmpty()) {
                System.out.println("You cannot enter any whitespaces, please try again:");
                customMark = scanner.nextLine();
            }

            if (customMark.length() == 1 && !customMark.trim().isEmpty()) {
                invalidEntry = false;
                break;
            }
        }

        customChar = customMark.charAt(0);

        return customChar;
    }

    private static void gameMenu(char[][] board, Scanner scanner) {
        System.out.println("Welcome to Tic-Tac-Toe:\n");
        customCharacterPrompt(scanner);
        System.out.println("\n\nPlease choose a game mode:\n");
        System.out.println("(1) Human vs. human ");
        System.out.println("(2) Human vs. computer \n");
        boolean validMode = true;
        boolean repeatExitMenu = true;
        int gameMode = 0;

        gameMode = validateNumber(scanner, gameMode);
      

        //If user picks valid game mode
        if (gameMode == 1) {
            playerVsPlayer(board, scanner); 
            validMode = false;  
            resetBoard(board);
            while (repeatExitMenu) {
                repeatExitMenu = exitMenu(scanner, gameMode, board, repeatExitMenu);
                resetBoard(board);
            }
            
        }
        else if (gameMode == 2) {
            playerVsComputer(board, scanner);
            validMode = false;
            resetBoard(board);
            while (repeatExitMenu) {
                repeatExitMenu = exitMenu(scanner, gameMode, board, repeatExitMenu);
                resetBoard(board);
            }
        }

        //If User enters wrong game mode option
        while (validMode) {
            System.out.println("\nThis is not a valid choice\n");
            System.out.println("Please choose a game mode:\n");
            System.out.println("(1) Human vs. human");
            System.out.println("(2) Human vs. computer \n");

            gameMode = validateNumber(scanner, gameMode);
            
            
            if (gameMode == 1) {
                validMode = false;
                playerVsPlayer(board, scanner);
                resetBoard(board);
                while (repeatExitMenu) {
                    repeatExitMenu = exitMenu(scanner, gameMode, board, repeatExitMenu);
                    resetBoard(board);
                }
                
            }
            else if (gameMode == 2) {
                validMode = false;
                playerVsComputer(board, scanner);
                resetBoard(board);
                while (repeatExitMenu) {
                    repeatExitMenu = exitMenu(scanner, gameMode, board, repeatExitMenu);
                    resetBoard(board);
                }
                
            }
        }
    }

    

    private static boolean exitMenu(Scanner scanner, int gameType, char[][] board, boolean repeatExitMenu)
    {
        System.out.println("\nWould you like to play again?\n");
        System.out.println("(1) Yes\n(2) No\n");
        boolean repeatPrompt = true;
        int input = 0;
        input = validateNumber(scanner, input);

        if (input == 1) {
            if (gameType == 1) {
                repeatExitMenu = true;
                playerVsPlayer(board, scanner);
                repeatPrompt = false;
            }
            else if(gameType == 2)
            {
                repeatExitMenu = true;
                repeatPrompt = false;
                playerVsComputer(board, scanner);
            }
                
        }
        if (input == 2) {
            
            System.out.println("\nGoodbye!\n");
            repeatPrompt = false;
            repeatExitMenu = false;
            
        }
         
        while (repeatPrompt) {
            System.out.println("\nYour selection was invalid, please try again!");
            System.out.println("Would you like to play again?\n");
            System.out.println("(1) Yes\n(2) No\n");

            input = validateNumber(scanner, input);

            if (input == 1) {
                if (gameType == 1) {
                    repeatExitMenu = true;
                    playerVsPlayer(board, scanner);
                    repeatPrompt = false;
                    break;
                }
                else if(gameType == 2)
                {
                    repeatExitMenu = true;
                    repeatPrompt = false;
                    playerVsComputer(board, scanner);
                    break;
                }
                    
            }
            else if (input == 2) {
                System.out.println("\nGoodbye!\n");
                repeatPrompt = false;
                repeatExitMenu = false;
                break;
            }

        }

        return repeatExitMenu;
    }


    public static int validateNumber(Scanner scanner, int num) 
    { 
    
        while(!scanner.hasNextInt()) {
            System.out.println("\nThat was not a valid number, please try again!\n");
            scanner.next();
        }

        num = scanner.nextInt();

        return num;
    }


    private static void playerOneTurn(Scanner scanner, char[][] board)
    {
        
        System.out.println("Player one(X) - where would you like to move?");
        int playerOneMove = 0;
        playerOneMove = validateNumber(scanner, playerOneMove);

        while(!validSpot(playerOneMove, board))
        {
                System.out.println("That move is invalid!");
                System.out.println("Player one(X) - where would you like to move?");
                playerOneMove = scanner.nextInt();
        }

        makeMovePlayerOne(playerOneMove, board, playerOneMark);
        
        printBoard(board);
    }


    private static void playerVsPlayer(char[][] board, Scanner scanner) {
        int playerOneMove;
        int playerTwoMove = 0;
        System.out.println("Let's Begin!\n");

        while (true) {
            printBoard(board);
            playerOneTurn(scanner, board);

            if (isGameDone(board, "Player two", playerOneMark, playerTwoMark)) {
                break;
            }

            System.out.println("Player two(O) - where would you like to move?");
            playerTwoMove = validateNumber(scanner, playerTwoMove);

            while(!validSpot(playerTwoMove, board))
            {
                    System.out.println("That move is invalid!");
                    System.out.println("Player two(O) - where would you like to move?");
                    playerTwoMove = scanner.nextInt();
            }

            makeComputerOrPlayerTwoMove(playerTwoMove, board, playerTwoMark);
            if (isGameDone(board, "Player two", playerOneMark, playerTwoMark)) {
                break;
            }
            
        }
    }

    

    public static void printBoard(char[][] board)
    {
        System.out.println("\n" + board[0][0] + " | " + board[0][1] + " | " + board[0][2]);
        System.out.println("----------");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2]);
        System.out.println("----------");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2] + "\n");
    }

    public static void resetBoard(char[][] board)
    {
        board[0][0] = '1';
        board[0][1] = '2';
        board[0][2] = '3';
        board[1][0] = '4';
        board[1][1] = '5';
        board[1][2] = '6';
        board[2][0] = '7';
        board[2][1] = '8';
        board[2][2] = '9';
    }

    public static void makeMovePlayerOne(int spot, char[][] board, char playerOneMark)
    {
        switch (spot) {
            case 1:
                board[0][0] = playerOneMark;
                break;

            case 2:
                board[0][1] = playerOneMark;
                break;   

            case 3:
                board[0][2] = playerOneMark;
                break;
                
            case 4:
                board[1][0] = playerOneMark;
                break;  

            case 5:
                board[1][1] = playerOneMark;
                break;

            case 6:
                board[1][2] = playerOneMark;
                break;   

            case 7:
                board[2][0] = playerOneMark;
                break;
                
            case 8:
                board[2][1] = playerOneMark;
                break;

            case 9:
                board[2][2] = playerOneMark;
                break;
    
        }
    }

    public static void makeComputerOrPlayerTwoMove(int spot, char[][] board, char playerTwoMark)
    {
        switch (spot) {
            case 1:
                board[0][0] = playerTwoMark;
                break;

            case 2:
                board[0][1] = playerTwoMark;
                break;   

            case 3:
                board[0][2] = playerTwoMark;
                break;
                
            case 4:
                board[1][0] = playerTwoMark;
                break;  

            case 5:
                board[1][1] = playerTwoMark;
                break;

            case 6:
                board[1][2] = playerTwoMark;
                break;   

            case 7:
                board[2][0] = playerTwoMark;
                break;
                
            case 8:
                board[2][1] = playerTwoMark;
                break;

            case 9:
                board[2][2] = playerTwoMark;
                break;
    
        }
    }

    public static Boolean validSpot(int spot, char[][] board)
    {
        switch (spot) {
            case 1:
                return(board[0][0] == '1');
            case 2:
                return(board[0][1] == '2');
            case 3:
                return(board[0][2] == '3');
            case 4:
                return(board[1][0] == '4');
            case 5:
                return(board[1][1] == '5');
            case 6:
                return(board[1][2] == '6');
            case 7:
                return(board[2][0] == '7');
            case 8:
                return(board[2][1] == '8');
            case 9:
                return(board[2][2] == '9');
            default:
                return false;
        }

        
    }

    public static Boolean isGameDone(char[][] board, String secondPlayerName, char playerOneMark, char playerTwoMark)
    {   
        //if player one won
        if (board[0][0] == playerOneMark && board[0][1] == playerOneMark && board[0][2] == playerOneMark ||
            board[1][0] == playerOneMark && board[1][1] == playerOneMark && board[1][2] == playerOneMark ||
            board[2][0] == playerOneMark && board[2][1] == playerOneMark && board[2][2] == playerOneMark ||

            board[0][0] == playerOneMark && board[1][0] == playerOneMark && board[2][0] == playerOneMark ||
            board[0][1] == playerOneMark && board[1][1] == playerOneMark && board[2][1] == playerOneMark ||
            board[0][2] == playerOneMark && board[1][2] == playerOneMark && board[2][2] == playerOneMark ||

            board[0][0] == playerOneMark && board[1][1] == playerOneMark && board[2][2] == playerOneMark ||
            board[0][2] == playerOneMark && board[1][1] == playerOneMark && board[2][0] == playerOneMark)
        {
            
            
            System.out.println("Player One has won the game!");
            return true;
        }

        //if player two wins
        if (board[0][0] == playerTwoMark && board[0][1] == playerTwoMark && board[0][2] == playerTwoMark ||
            board[1][0] == playerTwoMark && board[1][1] == playerTwoMark && board[1][2] == playerTwoMark ||
            board[2][0] == playerTwoMark && board[2][1] == playerTwoMark && board[2][2] == playerTwoMark ||

            board[0][0] == playerTwoMark && board[1][0] == playerTwoMark && board[2][0] == playerTwoMark ||
            board[0][1] == playerTwoMark && board[1][1] == playerTwoMark && board[2][1] == playerTwoMark ||
            board[0][2] == playerTwoMark && board[1][2] == playerTwoMark && board[2][2] == playerTwoMark ||

            board[0][0] == playerTwoMark && board[1][1] == playerTwoMark && board[2][2] == playerTwoMark ||
            board[0][2] == playerTwoMark && board[1][1] == playerTwoMark && board[2][0] == playerTwoMark)
        {
            
            System.out.println("" + secondPlayerName + " has won the game!");
            printBoard(board);
            return true;
        }

        //if tie
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if (board[i][j] != playerOneMark && board[i][j] != playerTwoMark) {
                    return false;
                }
            }
        }
        
        
        System.out.println("The game is a tie!");
        printBoard(board);
        return true;
    }
}





