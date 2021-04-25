package ChessGame.ChessBody.FigureType.PawnColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.KnightColour.WhiteKnight;
import ChessGame.ChessBody.FigureType.NoFigure;
import ChessGame.ChessBody.FigureType.Pawn;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackPawn extends Pawn {
    ArrayList<String> firstMove;
    String colour;

    @Override
    public ChessFigure deepClone() {
        return new BlackPawn(this.getCoordinates());
    }

    public BlackPawn(String coordinates) {

        this.setCoordinates(coordinates);
        this.colour = BLACK;

        firstMove = new ArrayList<String>();

        for (char i = 'A'; i <= 'I'; ++i) {
            String temp;
            temp = String.valueOf(i) + String.valueOf(7);
            firstMove.add(temp);

            temp = String.valueOf(i) + String.valueOf(2);
            firstMove.add(temp);
        }
    }

    @Override
    public ArrayList<ArrayList<String>> getAvailableMoves() {
        ArrayList<ArrayList<String>> arrayOfAvailableMoves = new ArrayList<>();
        ArrayList<String> availableMoves = new ArrayList<>();


        if (firstMove.contains(this.getCoordinates())) {
            String turn = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) - 2);
            availableMoves.add(turn);
        }

        String turn = Character.toString(this.getCoordinates().charAt(0) - 1) + Character.toString(this.getCoordinates().charAt(1) - 1);
        if (turn.matches("^[A-Ha-h][1-8]$")) availableMoves.add(turn);

        turn = Character.toString(this.getCoordinates().charAt(0)) + Character.toString(this.getCoordinates().charAt(1) - 1);
        if (turn.matches("^[A-Ha-h][1-8]$")) availableMoves.add(turn);

        turn = Character.toString(this.getCoordinates().charAt(0) + 1) + Character.toString(this.getCoordinates().charAt(1) - 1);
        if (turn.matches("^[A-Ha-h][1-8]$")) availableMoves.add(turn);

        arrayOfAvailableMoves.add(availableMoves);

        return arrayOfAvailableMoves;
    }

    @Override
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        HashMap<String,ChessFigure> mapForTest = deepCloneMap(desc);
        mapForTest.put(this.getCoordinates(), new NoFigure(this.getCoordinates()));
        if (game.getBlackKing().isUnderCheck(mapForTest)) return true;
        return false;
    }

    @Override
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError, KingWillBeUnderCheckException {
        System.out.println();

        if (getAvailableMoves().get(0).contains(coordinates)) {

            if (isKingWillBeUnderCheck(desc,game)) throw new KingWillBeUnderCheckException();

            if (getCoordinates().charAt(0) != coordinates.charAt(0)) {
                if (desc.get(coordinates).getFigureColour().equals(ChessFigure.WHITE)) return true;

                // взятие на проходе
                String coordOfAnotherPawn = Character.toString(coordinates.charAt(0)) + Character.toString(coordinates.charAt(1) + 1);
                if (desc.get(coordOfAnotherPawn).getClassName().equals("Pawn")) {
                    String[] coordinatesOfLastMove = game.getLastMove().split("-");
                    if (desc.get(coordinatesOfLastMove[1]).getClassName().equals("Pawn")) {
                        if (firstMove.contains(coordinatesOfLastMove[0])) {
                            desc.put(coordinatesOfLastMove[1], new NoFigure(coordinatesOfLastMove[1]));

                            return true;
                        }
                    }
                }
            }

            if (desc.get(coordinates).getClassName().equals("NoFigure")) {
                String temp = Character.toString(getCoordinates().charAt(0)) + Character.toString(getCoordinates().charAt(1) - 1);
                if (temp.equals(coordinates)) {
                    if (desc.get(temp).getClassName().equals("NoFigure")) return true;
                }

                if (desc.get(temp).getClassName().equals("NoFigure")) {
                    temp = Character.toString(temp.charAt(0)) + Character.toString(temp.charAt(1) - 1);
                    if (desc.get(temp).getClassName().equals("NoFigure")) return true;
                }
            }
        }

        throw new InvalidInputError();
    }

    @Override
    public String getFigureColour() {
        return colour;
    }
}
