package ChessGame.ChessBody.FigureType.KingColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.King;
import ChessGame.ChessBody.FigureType.NoFigure;
import ChessGame.ChessBody.FigureType.Rook;

import java.util.ArrayList;
import java.util.HashMap;

public class WhiteKing extends King {
    String colour;

    @Override
    public ChessFigure deepClone() {
        return new WhiteKing(this.getCoordinates());
    }

    public WhiteKing(String coordinates) {
        super(coordinates);
        this.setCoordinates(coordinates);
        this.colour = WHITE;
        this.firstMove = true;
    }

    @Override
    public String getFigureColour() {
        return colour;
    }

    @Override
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        return false;
    }

    @Override
    public boolean isTotalyStalemated(HashMap<String, ChessFigure> desc) {
        game.turnOffMoves();
        if (super.isStalemated(desc)) {
            for (var figure : desc.entrySet()) {
                if (figure.getValue().getFigureColour().equals(ChessFigure.WHITE)) {
                    ChessFigure whiteFigure = figure.getValue();

                    ArrayList<String> availableMoves = new ArrayList<>();

                    for (var arrayListOfMoves : whiteFigure.getAvailableMoves()) {
                        availableMoves.addAll(arrayListOfMoves);
                    }

                    for (var moveCoordinates : availableMoves) {
                        try {
                            if (whiteFigure.move(moveCoordinates, desc, game)) {
                                game.turnOnMoves();
                                return false;
                            }
                        } catch (FigureFoundError | KingWillBeUnderCheckException | InvalidInputError e) {
                        }
                    }
                }
            }
        }
        game.turnOnMoves();
        return true;
    }

    @Override
    public boolean isUnderCheck(HashMap<String, ChessFigure> desc) {
        game.turnOffMoves();

        for (var blackFigure : desc.entrySet()) {
            if (blackFigure.getValue().getFigureColour().equals(ChessFigure.BLACK)) {
                try {
                    if (blackFigure.getValue().move(this.getCoordinates(), desc, game)) {
                        game.turnOnMoves();
                        return true;
                    }
                }
                catch (FigureFoundError | InvalidInputError | KingWillBeUnderCheckException ignored) {
                }
            }
        }
        game.turnOnMoves();
        return false;
    }

    @Override
    public boolean isWillBeUnderCheck(String coordinates, HashMap<String, ChessFigure> desc) {
        game.turnOffMoves();

        for (var blackFigure : desc.entrySet()) {
            if (blackFigure.getValue().getFigureColour().equals(ChessFigure.BLACK)) {
                try {
                    if (blackFigure.getValue().move(coordinates, desc, game)) {
                        game.turnOnMoves();
                        return true;
                    }
                }
                catch (FigureFoundError | InvalidInputError | KingWillBeUnderCheckException ignored) {
                }
            }
        }
        game.turnOnMoves();
        return false;
    }

    @Override
    public boolean isUnderMate(HashMap<String, ChessFigure> desc) {

        for (var blackFigure : desc.entrySet()) {

            if (blackFigure.getValue().getFigureColour().equals(ChessFigure.WHITE)) {
                ChessFigure figure = blackFigure.getValue();
                HashMap<String, ChessFigure> descForTest;

                ArrayList<String> availableMoves = new ArrayList<>();

                for (var arrayListOfMoves : figure.getAvailableMoves()) {
                    availableMoves.addAll(arrayListOfMoves);
                }

                for (var moveCoordinates : availableMoves) {
                    descForTest = this.deepCloneMap(desc);

                    try {
                        if (figure.move(moveCoordinates, descForTest, game)) {
                            descForTest.put(moveCoordinates, figure);
                            descForTest.put(figure.getCoordinates(), new NoFigure(figure.getCoordinates()));
                        }
                        if (!isUnderCheck(descForTest)) return false;
                    } catch (FigureFoundError | InvalidInputError | KingWillBeUnderCheckException ignored) { }
                }
            }
        }
        return true;
    }

    @Override
    protected boolean makeCastling(String coordinates, HashMap<String, ChessFigure> desc) throws FigureFoundError {
        if (desc.get("F1").getClassName().equals("NoFigure") && desc.get("G1").getClassName().equals("NoFigure")) {
            if (!this.isWillBeUnderCheck("F1", desc) && !this.isWillBeUnderCheck("G1", desc) && !this.isUnderCheck(desc)) {
                if (desc.get("H1").getClassName().equals("Rook")) {
                    Rook rook = (Rook)desc.get("H1");
                    if (rook.isFirstMove()) {
                        rook.setCoordinates("F1");
                        rook.firstMoveDone();
                        desc.remove("F1");
                        desc.put("F1", rook);
                        desc.remove("H1");
                        desc.put("H1", new NoFigure("H1"));

                        this.setCoordinates("G1");
                        desc.remove("G1");
                        desc.put("G1", this);
                        desc.remove("E1");
                        firstMove = false;

                        return true;
                    }
                }
            }
        }

        if (desc.get("D1").getClassName().equals("NoFigure") && desc.get("C1").getClassName().equals("NoFigure") && desc.get("B1").getClassName().equals("NoFigure")) {
            if (!this.isWillBeUnderCheck("D1", desc) && !this.isWillBeUnderCheck("C1", desc) && !this.isUnderCheck(desc)) {
                if (desc.get("A1").getClassName().equals("Rook")) {
                    Rook rook = (Rook)desc.get("A1");
                    if (rook.isFirstMove()) {
                        rook.setCoordinates("D1");
                        rook.firstMoveDone();
                        desc.remove("D1");
                        desc.put("D1", rook);
                        desc.remove("A1");
                        desc.put("A1", new NoFigure("A1"));

                        this.setCoordinates("C1");
                        desc.remove("C1");
                        desc.put("C1", this);
                        desc.remove("E1");
                        firstMove = false;

                        return true;
                    }
                }
            }
        }
        throw new FigureFoundError();
    }

    @Override
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError, FigureFoundError, KingWillBeUnderCheckException {

        if (this.getAvailableMoves().get(0).contains(coordinates)) {

            if (isWillBeUnderCheck(coordinates,desc)) throw new KingWillBeUnderCheckException();

            if (desc.get(coordinates).getFigureColour().equals(ChessFigure.WHITE)) throw new FigureFoundError();

            if (coordinates.equals("G1") || coordinates.equals("C1")) {
                if (makeCastling(coordinates, desc)) return true;
            }

            if (game.isMoveEnabled()) firstMove = false;
            return true;
        }

        throw new InvalidInputError();
    }
}
