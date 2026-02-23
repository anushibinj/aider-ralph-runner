import java.util.Scanner;
import java.util.InputMismatchException;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String readRawStringLine() {
        return scanner.nextLine();
    }

    public int[] parseInput(String input) {
        String[] parts = input.split("\\s+");
        if (parts.length != 2) {
            parts = input.split(",");
            if (parts.length != 2) {
                throw new InputMismatchException("Invalid input format. Please use 'row col' or 'row,col'.");
            }
        }
        try {
            int row = Integer.parseInt(parts[0]);
            int col = Integer.parseInt(parts[1]);
            return new int[] {row, col};
        } catch (NumberFormatException e) {
            throw new InputMismatchException("Invalid input. Please enter numbers for row and column.");
        }
    }

    public int[] getValidMove(Game game) {
        while (true) {
            System.out.print("Enter your move (row col or row,col): ");
            String input = readRawStringLine();
            try {
                int[] move = parseInput(input);
                if (game.getBoard().getCell(move[0], move[1]) == Player.EMPTY) {
                    return move;
                } else {
                    System.out.println("Cell is already occupied. Please choose another cell.");
                }
            } catch (IllegalArgumentException | InputMismatchException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
