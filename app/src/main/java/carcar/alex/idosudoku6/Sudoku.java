package carcar.alex.idosudoku6;

/**
 * Created by Local User on 1/5/2016.
 */
public class Sudoku {
    public static int board[][];
    public static boolean hide[][];
    private static int BOARD_SIZE;
    private static int GROUP_WIDTH;
    private static int GROUP_HEIGHT;
    private static boolean exclude[];

    private static void defineBoard(int size, int width) {
        board = null;
        exclude = null;
        hide = null;
        BOARD_SIZE = size;
        GROUP_WIDTH = width;
        GROUP_HEIGHT = size / width;
    }

    private static void clearBoard() {
        if (board == null) {
            board = new int[BOARD_SIZE][BOARD_SIZE];
            exclude = new boolean[BOARD_SIZE];
            hide = new boolean[BOARD_SIZE][BOARD_SIZE];
        }
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = 0;
            }
        }
    }

    public static void hide(double percent) {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if(Math.random()<percent) {
                    hide[i][j] = true;
                    board[i][j] = 0;
                } else {
                    hide[i][j] = false;
                }
            }
        }
    }
    private static void printBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    private static int fillBoard() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                int v = fillSquare(i, j);
                if (v == -1) return -1;
                board[i][j] = v;
            }
        }
        return 1;
    }

    private static int fillSquare(int i, int j) {
        int exclusions = fillExcluded(i, j);
        if (exclusions == BOARD_SIZE) {
            return -1; // Stuck, try again
        }
        int n;
        do {
            n = (int) Math.floor(Math.random() * BOARD_SIZE) + 1;
        } while (isExcluded(n));
        return n;
    }

    /* Methods to exclude numbers from selection */
    /* ================================================ */
    private static int fillExcluded(int i, int j) {
        for (int x = 0; x < BOARD_SIZE; x++) {
            exclude[x] = false;
        }
        for (int x = 0; x < BOARD_SIZE; x++) {
            doExclude(board[i][x]);
            doExclude(board[x][j]);
        }
        excludeGroup(i, j);

        int n = 0;
        for (int x = 0; x < BOARD_SIZE; x++) {
            if (exclude[x]) n++;
        }
        return n;
    }

    private static void excludeGroup(int i, int j) {
        int x = (j / GROUP_WIDTH) * GROUP_WIDTH;
        int y = (i / GROUP_HEIGHT) * GROUP_HEIGHT;
        for (int dy = 0; dy < GROUP_HEIGHT; dy++)
            for (int dx = 0; dx < GROUP_WIDTH; dx++)
                doExclude(board[y + dy][x + dx]);
    }

    private static void doExclude(int n) {
        if (n != 0) exclude[n - 1] = true;
    }

    public static boolean isHidden(int x, int y) {
        return hide[x][y];
    }
    private static boolean isExcluded(int n) {
        return exclude[n - 1];
    }

	/* ================================================ */

    public static void create() {
        defineBoard(6, 3);
        do {
            clearBoard();
        } while (fillBoard() == -1);
        printBoard();
    }

    public static void put(int x, int y, int value) {
        board[x][y] = value;
    }

    public static boolean complete() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
