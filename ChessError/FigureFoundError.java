package ChessGame.ChessBody.ChessError;

public class FigureFoundError extends Throwable {
    private String error;

    public void what() {
        System.out.print(error);
    }

    public FigureFoundError() {
        this.error = "К сожалению, по пути находится фигура. Перепроверьте правильность хода!\n";
    }

}
