package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;

public abstract class Knight extends ChessFigure {

    public Knight(String coordinates) {
        this.coordinates = coordinates;
    }

    public ArrayList<ArrayList<String>> getAvailableMoves() {
        ArrayList<ArrayList<String>> arrayListsOfMoves = new ArrayList<ArrayList<String>>();

        String temp = this.getCoordinates();
        ArrayList<String> availableMoves = new ArrayList<String>();

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) + 2)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) + 1));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) + 2)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) - 1));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) - 2)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) + 1));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) - 2)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) - 1));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) - 1)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) + 2));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) - 1)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) - 2));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) + 1)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) + 2));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        temp = String.valueOf(Character.toString(this.getCoordinates().charAt(0) + 1)) + String.valueOf(Character.toString(this.getCoordinates().charAt(1) - 2));

        if (temp.matches("^[A-Ha-h][1-8]$")) // если входит
            availableMoves.add(temp);

        arrayListsOfMoves.add(availableMoves);

        return arrayListsOfMoves;
    };

    @Override
    public String getClassName() {
        return "Knight";
    }
}
