import java.util.Scanner;

public class TicTacToeBoard {
    private char[][] board;
    private char currentPlayer;

    public TicTacToeBoard() {
        board = new char[3][3];
        currentPlayer = 'X';
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                board[i][j] = '-';
    }

    public boolean placeMark(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2)
            return false;
        if (board[row][col] != '-')
            return false;
        board[row][col] = currentPlayer;
        return true;
    }

    public boolean checkWin() {
        // check rows
        for (int i=0; i<3; i++) {
            if (board[i][0] == currentPlayer &&
                board[i][1] == currentPlayer &&
                board[i][2] == currentPlayer)
                return true;
        }
        // check cols
        for (int j=0; j<3; j++) {
            if (board[0][j] == currentPlayer &&
                board[1][j] == currentPlayer &&
                board[2][j] == currentPlayer)
                return true;
        }
        // check diagonals
        if (board[0][0] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][2] == currentPlayer)
            return true;
        if (board[0][2] == currentPlayer &&
            board[1][1] == currentPlayer &&
            board[2][0] == currentPlayer)
            return true;
        return false;
    }

    public boolean isBoardFull() {
        for (int i=0; i<3; i++)
            for (int j=0; j<3; j++)
                if (board[i][j] == '-')
                    return false;
        return true;
    }

    public void changePlayer() {
        currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
    }

    public void printBoard() {
        System.out.println("棋盤狀態:");
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TicTacToeBoard game = new TicTacToeBoard();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            game.printBoard();
            System.out.println("玩家 " + game.currentPlayer + " 請輸入行列(0~2)用空白分隔:");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (!game.placeMark(row, col)) {
                System.out.println("無效位置，請重新輸入");
                continue;
            }

            if (game.checkWin()) {
                game.printBoard();
                System.out.println("玩家 " + game.currentPlayer + " 勝利!");
                break;
            }

            if (game.isBoardFull()) {
                game.printBoard();
                System.out.println("平手!");
                break;
            }

            game.changePlayer();
        }
        scanner.close();
    }
}
