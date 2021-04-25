package ChessGame.ChessBody.ChessError;

public class KingWillBeUnderCheckException extends Throwable {
    private String errorMessage;

    public void what() {
        System.out.println(errorMessage);
    }

    public KingWillBeUnderCheckException() {
        this.errorMessage = "Король будет под шахом в случае такого хода.";
    }
}
