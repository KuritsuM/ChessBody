package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;
import java.util.HashMap;

public class NoFigure extends ChessFigure {
    public NoFigure(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public ChessFigure deepClone() {
        return new NoFigure(this.coordinates);
    }

    @Override
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError {
        throw new InvalidInputError();
    };

    @Override
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        return false;
    }

    @Override
    public ArrayList<ArrayList<String>> getAvailableMoves() {
        return null;
    }

    @Override
    public String getClassName() {
        return "NoFigure";
    }

    @Override
    public String getFigureColour() {
        return "#F0F0F0";
    }
}
