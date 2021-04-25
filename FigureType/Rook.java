package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;

// Ладья
abstract public class Rook extends ChessFigure {
    protected boolean firstMove;

    public void firstMoveDone() {
        this.firstMove = false;
    }

    public boolean isFirstMove() {
        return firstMove;
    }


    public Rook(String coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<ArrayList<String>> getAvailableMoves() {
        ArrayList<ArrayList<String>> arrayOfAvailableMoves = new ArrayList<ArrayList<String>>();

        String temp;
        ArrayList<String> availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) + i) + Character.toString(this.getCoordinates().charAt(1));

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayOfAvailableMoves.add(availableMoves);

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) - i) + Character.toString(this.getCoordinates().charAt(1));

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayOfAvailableMoves.add(availableMoves);

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) + i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayOfAvailableMoves.add(availableMoves);

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) - i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayOfAvailableMoves.add(availableMoves);

        return arrayOfAvailableMoves;
    }

    @Override
    public String getClassName() {
        return "Rook";
    }
}
