package ChessGame.ChessBody.ChessError;

public class InvalidInputError extends Throwable {
    private String error;

    public void what() {
        System.out.print(error);
    }

    public InvalidInputError() {
        this.error = "К сожалению, такой ход невозможен. Постарайтесь перепроверить правильность вашего хода.\n";
    }

}
