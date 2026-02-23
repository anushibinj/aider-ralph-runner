public class Board {
    private Player[][] cells;

    public Board() {
        this.cells = new Player[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                this.cells[i][j] = Player.EMPTY;
            }
        }
    }

    public Player getCell(int row, int col) {
        if (!isValidCoordinate(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        return this.cells[row][col];
    }

    public void setCell(int row, int col, Player player) {
        if (!isValidCoordinate(row, col)) {
            throw new IllegalArgumentException("Coordinates out of bounds");
        }
        this.cells[row][col] = player;
    }

    private boolean isValidCoordinate(int row, int col) {
        return row >= 0 && row < 3 && col >= 0 && col < 3;
    }
}
