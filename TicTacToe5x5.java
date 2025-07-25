import java.util.Scanner;

public class TicTacToe5x5 {
    static final int SIZE = 5;
    static char[][] board = new char[SIZE][SIZE];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeBoard();
        System.out.println("=== 5x5 井字遊戲 ===");
        printBoard();

        char currentPlayer = 'X';
        while (true) {
            int row, col;
            while (true) {
                System.out.print("玩家 " + currentPlayer + " 請輸入 row col (0-4): ");
                row = scanner.nextInt();
                col = scanner.nextInt();
                if (isValidMove(row, col)) break;
                System.out.println("座標錯誤或已被佔用，請重新輸入！");
            }

            board[row][col] = currentPlayer;
            System.out.println("玩家 " + currentPlayer + " 在位置 (" + row + ", " + col + ") 放置棋子");
            printBoard();

            if (checkWin(currentPlayer)) {
                System.out.println("玩家 " + currentPlayer + " 獲勝！");
                break;
            } else if (isBoardFull()) {
                System.out.println("平手！");
                break;
            }

            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        scanner.close();
    }

    static void initializeBoard() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                board[i][j] = '.';
    }

    static void printBoard() {
        System.out.print("  ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '.';
    }

    static boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++)
            for (int j = 0; j < SIZE; j++)
                if (board[i][j] == '.') return false;
        return true;
    }

    static boolean checkWin(char player) {
        // 檢查所有行
        for (int i = 0; i < SIZE; i++) {
            boolean win = true;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != player) {
                    win = false; break;
                }
            }
            if (win) return true;
        }

        // 檢查所有列
        for (int j = 0; j < SIZE; j++) {
            boolean win = true;
            for (int i = 0; i < SIZE; i++) {
                if (board[i][j] != player) {
                    win = false; break;
                }
            }
            if (win) return true;
        }

        // 檢查主對角線
        boolean win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][i] != player) {
                win = false; break;
            }
        }
        if (win) return true;

        // 檢查反對角線
        win = true;
        for (int i = 0; i < SIZE; i++) {
            if (board[i][SIZE - 1 - i] != player) {
                win = false; break;
            }
        }
        return win;
    }
}
