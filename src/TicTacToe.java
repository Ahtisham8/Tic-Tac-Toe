import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        
        char[][] board = {{'1', '2', '3'}, 
                        {'4', '5', '6'},                    
                        {'7', '8', '9'}};
        
        Scanner scanner = new Scanner(System.in);
        int playerOneMove;
        int playerTwoMove;
        
        System.out.println("Tic-Tac-Toe:\n\n");
        printBoard(board);

        while (true) {

            
            System.out.println("Player one(X) - where would you like to move?");
            playerOneMove = scanner.nextInt();

            while(!validSpot(playerOneMove, board))
            {
                    System.out.println("That move is invalid!");
                    System.out.println("Player one(X) - where would you like to move?");
                    playerOneMove = scanner.nextInt();
            }

            makeMovePlayerOne(playerOneMove, board);
            if (isGameDone(board)) {
                break;
            }
            printBoard(board);

            System.out.println("Player two(O) - where would you like to move?");
            playerTwoMove = scanner.nextInt();

            while(!validSpot(playerTwoMove, board))
            {
                    System.out.println("That move is invalid!");
                    System.out.println("Player two(O) - where would you like to move?");
                    playerTwoMove = scanner.nextInt();
            }

            makeMovePlayerTwo(playerTwoMove, board);
            if (isGameDone(board)) {
                break;
            }
            printBoard(board);
        }        
    }

    public static void printBoard(char[][] board)
    {
        System.out.println("\n" + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " | ");
        System.out.println("-----------");
        System.out.println(board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " | ");
        System.out.println("-----------");
        System.out.println(board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " | \n");
    }

    public static void makeMovePlayerOne(int spot, char[][] board)
    {
        switch (spot) {
            case 1:
                board[0][0] = 'X';
                break;

            case 2:
                board[0][1] = 'X';
                break;   

            case 3:
                board[0][2] = 'X';
                break;
                
            case 4:
                board[1][0] = 'X';
                break;  

            case 5:
                board[1][1] = 'X';
                break;

            case 6:
                board[1][2] = 'X';
                break;   

            case 7:
                board[2][0] = 'X';
                break;
                
            case 8:
                board[2][1] = 'X';
                break;

            case 9:
                board[2][2] = 'X';
                break;
    
        }
    }

    public static void makeMovePlayerTwo(int spot, char[][] board)
    {
        switch (spot) {
            case 1:
                board[0][0] = 'O';
                break;

            case 2:
                board[0][1] = 'O';
                break;   

            case 3:
                board[0][2] = 'O';
                break;
                
            case 4:
                board[1][0] = 'O';
                break;  

            case 5:
                board[1][1] = 'O';
                break;

            case 6:
                board[1][2] = 'O';
                break;   

            case 7:
                board[2][0] = 'O';
                break;
                
            case 8:
                board[2][1] = 'O';
                break;

            case 9:
                board[2][2] = 'O';
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

    public static Boolean isGameDone(char[][] board)
    {   

        if (board[0][0] == 'X' && board[0][1] == 'X' && board[0][2] == 'X' ||
            board[1][0] == 'X' && board[1][1] == 'X' && board[1][2] == 'X' ||
            board[2][0] == 'X' && board[2][1] == 'X' && board[2][2] == 'X' ||

            board[0][0] == 'X' && board[1][0] == 'X' && board[2][0] == 'X' ||
            board[0][1] == 'X' && board[1][1] == 'X' && board[2][1] == 'X' ||
            board[0][2] == 'X' && board[1][2] == 'X' && board[2][2] == 'X' ||

            board[0][0] == 'X' && board[1][1] == 'X' && board[2][2] == 'X' ||
            board[0][2] == 'X' && board[1][1] == 'X' && board[2][0] == 'X')
        {
            printBoard(board);
            System.out.println("Player One has won the game!\nThanks for playing!\n");
            return true;
        }

        if (board[0][0] == 'O' && board[0][1] == 'O' && board[0][2] == 'O' ||
            board[1][0] == 'O' && board[1][1] == 'O' && board[1][2] == 'O' ||
            board[2][0] == 'O' && board[2][1] == 'O' && board[2][2] == 'O' ||

            board[0][0] == 'O' && board[1][0] == 'O' && board[2][0] == 'O' ||
            board[0][1] == 'O' && board[1][1] == 'O' && board[2][1] == 'O' ||
            board[0][2] == 'O' && board[1][2] == 'O' && board[2][2] == 'O' ||

            board[0][0] == 'O' && board[1][1] == 'O' && board[2][2] == 'O' ||
            board[0][2] == 'O' && board[1][1] == 'O' && board[2][0] == 'O')
        {
            printBoard(board);
            System.out.println("Player Two has won the game!\nThanks for playing!\n");
            return true;
        }

        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board.length; j++)
            {
                if (board[i][j] != 'X' && board[i][j] != 'O') {
                    return false;
                }
            }
        }
        
        System.out.println("\n\nThe game is a tie!\nThanks for playing!\n");
        printBoard(board);
        return true;
    }
}
