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
}
