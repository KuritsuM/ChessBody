package ChessGame.ChessBody.ChessError;

public class InvalidTurn extends Throwable {
    private String error;

    public void what() {
        System.out.print(error);
    }

    public InvalidTurn() {
        this.error = "К сожалению, на данный момент ход игрока играющего другим цветом шахмат.\n";
    }

}