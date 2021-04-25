package ChessGame.ChessBody;

import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;

import java.util.ArrayList;
import java.util.HashMap;

public interface Figure {
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError, FigureFoundError, KingWillBeUnderCheckException;
}
