package ChessGame.ChessBody.FigureType;

import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessFigure;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Queen extends ChessFigure {

    public Queen(String coordinates) {
        this.coordinates = coordinates;
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

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) + i) + Character.toString(this.getCoordinates().charAt(1));

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0) - i) + Character.toString(this.getCoordinates().charAt(1));

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) + i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        availableMoves = new ArrayList<String>();
        for (int i = 1; i < 9; ++i) {
            temp = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) - i);

            if (temp.matches("^[A-Ha-h][1-8]$")) availableMoves.add(temp);
        }
        arrayListsOfMoves.add(availableMoves);

        return arrayListsOfMoves;
    }

    @Override
    public String getClassName() {
        return "Queen";
    }
}
