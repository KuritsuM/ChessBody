package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Pawn extends ChessFigure {

    public String lastPosition;
    private ArrayList<String> firstTurn;

    @Override
    public String getClassName() {
        return "Pawn";
    }
}
