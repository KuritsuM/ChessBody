package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;

// Офицер
public abstract class Bishop extends ChessFigure {
    public Bishop(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String getClassName() {
        return "Bishop";
    }

    public ArrayList<ArrayList<String>> getAvailableMoves() {
        ArrayList<ArrayList<String>> arrayListsOfMoves = new ArrayList<ArrayList<String>>();

        String temp = this.getCoordinates();
        ArrayList<String> availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) + i) + Character.toString(this.getCoordinates().charAt(1) + i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        temp = this.getCoordinates();
        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) - i) + Character.toString(this.getCoordinates().charAt(1) - i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        temp = this.getCoordinates();
        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) + i) + Character.toString(this.getCoordinates().charAt(1) - i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        temp = this.getCoordinates();
        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) - i) + Character.toString(this.getCoordinates().charAt(1) + i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        return arrayListsOfMoves;
    }
}
