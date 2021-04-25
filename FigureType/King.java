package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;
import java.util.HashMap;

abstract public class King extends ChessFigure {
    public King(String coordinates) {
        this.coordinates = coordinates;
    }

    protected boolean firstMove;

    protected ChessDesc game;

    public void setGame(ChessDesc game) {
        this.game = game;
    }

    abstract public boolean isUnderCheck(HashMap<String, ChessFigure> desc);

    abstract public boolean isUnderMate(HashMap<String, ChessFigure> desc);

    abstract public boolean isWillBeUnderCheck(String coordinates, HashMap<String, ChessFigure> desc);

    abstract protected boolean makeCastling(String coordinates, HashMap<String, ChessFigure> desc) throws FigureFoundError;

    // проверки на патовую ситуацию
    public boolean isStalemated(HashMap<String, ChessFigure> desc) {
        if (!isUnderCheck(desc)) {
            for (var moveCoord : this.getAvailableMoves().get(0)) {
                try {
                    if (this.move(moveCoord, desc, game)) return false;
                } catch (FigureFoundError | InvalidInputError | KingWillBeUnderCheckException ignored) {
                }
            }
        }
        return true;
    }

    abstract public boolean isTotalyStalemated(HashMap<String, ChessFigure> desc);

    @Override
    public ArrayList<ArrayList<String>> getAvailableMoves() {
        ArrayList<ArrayList<String>> arrayOfAvailableMoves = new ArrayList<ArrayList<String>>();
        ArrayList<String> availableMoves = new ArrayList<>();

        String key;
        key = Character.toString(this.getCoordinates().charAt(0) - 1) + Character.toString(this.getCoordinates().charAt(1));
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0) + 1) + Character.toString(this.getCoordinates().charAt(1));
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) + 1);
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) - 1);
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0) + 1) + Character.toString(this.getCoordinates().charAt(1) + 1);
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0) - 1) + Character.toString(this.getCoordinates().charAt(1) - 1);
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0) + 1) + Character.toString(this.getCoordinates().charAt(1) - 1);
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        key = Character.toString(this.getCoordinates().charAt(0) - 1) + Character.toString(this.getCoordinates().charAt(1) + 1);
        if (key.matches("^[A-Ha-h][1-8]$")) availableMoves.add(key);

        if (firstMove) {
            key = Character.toString(this.getCoordinates().charAt(0) - 2) + Character.toString(this.getCoordinates().charAt(1));
            availableMoves.add(key);

            key = Character.toString(this.getCoordinates().charAt(0) + 2) + Character.toString(this.getCoordinates().charAt(1));
            availableMoves.add(key);
        }

        arrayOfAvailableMoves.add(availableMoves);

        return arrayOfAvailableMoves;
    }

    @Override
    public String getClassName() {
        return "King";
    }
}
