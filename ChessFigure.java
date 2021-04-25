package ChessGame.ChessBody;

import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class ChessFigure implements Figure {
    public static final String WHITE = "#FFFFFF";
    public static final String BLACK = "#000000";

    protected String coordinates;

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    protected boolean checkForFigure(String coordinates, HashMap<String, ChessFigure> figure) {
        var temp = figure.get(coordinates);
        if (temp.getClassName().equals("NoFigure")) {
            return false;
        }
        else return true;
    }

    public HashMap<String, ChessFigure> deepCloneMap(HashMap<String, ChessFigure> desc) {
        HashMap<String, ChessFigure> clonedMap = new HashMap<>();
        for (var descToClone: desc.entrySet()) {
            clonedMap.put(descToClone.getKey(), descToClone.getValue().deepClone());
        }

        return clonedMap;
    }

    abstract public ChessFigure deepClone();

    abstract public ArrayList<ArrayList<String>> getAvailableMoves();

    abstract public String getClassName();

    abstract public String getFigureColour();

    abstract public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game);
}
