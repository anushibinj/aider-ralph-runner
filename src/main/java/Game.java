public class Game {
    private Board board;
    private Player currentPlayer;

    public Game() {
        this.board = new Board();
        this.currentPlayer = Player.X;
    }

    public Board getBoard() {
        return this.board;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public boolean isGameOver() {
        Board board = getBoard();
        Player currentPlayer = getCurrentPlayer();
        
        if (board.checkHorizontalWin(currentPlayer) || 
            board.checkVerticalWin(currentPlayer) || 
            board.checkDiagonalWin(currentPlayer)) {
            return true;
        }
        
        if (board.isFull()) {
            return true;
        }
        
        return false;
    }

    public boolean isValidMove(int row, int col) {
        if (row < 0 || row >= 3 || col < 0 || col >= 3) {
            return false;
        }
        return getBoard().getCell(row, col) == Player.EMPTY;
    }

    public void start() {
        while (!isGameOver()) {
            System.out.println("Current Board:");
            printBoard();
            System.out.println("Player " + getCurrentPlayer() + "'s turn");
            int[] move = new InputHandler().getValidMove(this);
            getBoard().setCell(move[0], move[1], getCurrentPlayer());
            switchPlayer();
        }
    }

    private void switchPlayer() {
        if (getCurrentPlayer() == Player.X) {
            currentPlayer = Player.O;
        } else {
            currentPlayer = Player.X;
        }
    }

    private void printBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(getBoard().getCell(row, col) + " ");
            }
            System.out.println();
        }
    }
}
