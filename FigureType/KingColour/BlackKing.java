package ChessGame.ChessBody.FigureType.KingColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.BishopColour.WhiteBishop;
import ChessGame.ChessBody.FigureType.King;
import ChessGame.ChessBody.FigureType.NoFigure;
import ChessGame.ChessBody.FigureType.Rook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BlackKing extends King {
    String colour;

    @Override
    public ChessFigure deepClone() {
        return new BlackKing(this.getCoordinates());
    }

    public BlackKing(String coordinates) {
        super(coordinates);
        this.colour = BLACK;
        this.firstMove = true;
    }

    @Override
    public boolean isUnderCheck(HashMap<String, ChessFigure> desc) {
        game.turnOffMoves();

        for (var whiteFigure : desc.entrySet()) {
            if (whiteFigure.getValue().getFigureColour().equals(ChessFigure.WHITE)) {
                try {
                    if (whiteFigure.getValue().move(this.getCoordinates(), desc, game)) {
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
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        game.turnOffMoves();

        for (var whiteFigure : desc.entrySet()) {
            if (whiteFigure.getValue().getFigureColour().equals(ChessFigure.WHITE)) {
                try {
                    if (whiteFigure.getValue().move(coordinates, desc, game)) {
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

            if (blackFigure.getValue().getFigureColour().equals(ChessFigure.BLACK)) {
                ChessFigure figure = blackFigure.getValue();
                HashMap<String, ChessFigure> descForTest;

                ArrayList<String> availableMoves = new ArrayList<>();

                for (var arrayListOfMoves : figure.getAvailableMoves()) {
                    availableMoves.addAll(arrayListOfMoves);
                }

                for (var moveCoordinates : availableMoves) {
                    descForTest = this.deepCloneMap(desc);

                    try {
                        if (figure.move(moveCoordinates, descForTest, game)) descForTest.put(moveCoordinates, figure);
                        if (!isUnderCheck(descForTest)) return false;
                    } catch (FigureFoundError | InvalidInputError | KingWillBeUnderCheckException ignored) { }
                }
            }
        }
        return true;
    }

    @Override
    public boolean isWillBeUnderCheck(String coordinates, HashMap<String, ChessFigure> desc) {
        game.turnOffMoves();

        for (var whiteFigure : desc.entrySet()) {
            if (whiteFigure.getValue().getFigureColour().equals(ChessFigure.WHITE)) {
                try {
                    if (whiteFigure.getValue().move(coordinates, desc, game)) {
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
    protected boolean makeCastling(String coordinates, HashMap<String, ChessFigure> desc) throws FigureFoundError {
        if (desc.get("F8").getClassName().equals("NoFigure") && desc.get("G8").getClassName().equals("NoFigure")) {
            if (!this.isWillBeUnderCheck("F8", desc) && !this.isWillBeUnderCheck("G8", desc) && !this.isUnderCheck(desc)) {
                if (desc.get("H8").getClassName().equals("Rook")) {
                    Rook rook = (Rook)desc.get("H8");
                    if (rook.isFirstMove()) {
                        rook.setCoordinates("F8");
                        rook.firstMoveDone();
                        desc.remove("F8");
                        desc.put("F8", rook);
                        desc.remove("H8");
                        desc.put("H8", new NoFigure("H8"));

                        this.setCoordinates("G8");
                        desc.remove("G8");
                        desc.put("G8", this);
                        desc.remove("E8");
                        firstMove = false;

                        return true;
                    }
                }
            }
        }

        if (desc.get("D8").getClassName().equals("NoFigure") && desc.get("C8").getClassName().equals("NoFigure") && desc.get("B8").getClassName().equals("NoFigure")) {
            if (!this.isWillBeUnderCheck("D8", desc) && !this.isWillBeUnderCheck("C8", desc) && !this.isUnderCheck(desc)) {
                if (desc.get("A8").getClassName().equals("Rook")) {
                    Rook rook = (Rook)desc.get("A8");
                    if (rook.isFirstMove()) {
                        rook.setCoordinates("D8");
                        rook.firstMoveDone();
                        desc.remove("D8");
                        desc.put("D8", rook);
                        desc.remove("A8");
                        desc.put("A8", new NoFigure("A8"));

                        this.setCoordinates("C8");
                        desc.remove("C8");
                        desc.put("C8", this);
                        desc.remove("E8");
                        firstMove = false;

                        return true;
                    }
                }
            }
        }
        throw new FigureFoundError();
    }

    @Override
    public boolean isTotalyStalemated(HashMap<String, ChessFigure> desc) {
        game.turnOffMoves();
        if (super.isStalemated(desc)) {
            for (var figure : desc.entrySet()) {
                if (figure.getValue().getFigureColour().equals(ChessFigure.BLACK)) {
                    ChessFigure blackFigure = figure.getValue();

                    ArrayList<String> availableMoves = new ArrayList<>();

                    for (var arrayListOfMoves : blackFigure.getAvailableMoves()) {
                        availableMoves.addAll(arrayListOfMoves);
                    }

                    for (var moveCoordinates : availableMoves) {
                        try {
                            if (blackFigure.move(moveCoordinates, desc, game)) {
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
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError, FigureFoundError, KingWillBeUnderCheckException {

        if (this.getAvailableMoves().get(0).contains(coordinates)) {

            if (isWillBeUnderCheck(coordinates,desc)) throw new KingWillBeUnderCheckException();

            if (desc.get(coordinates).getFigureColour().equals(ChessFigure.BLACK)) throw new FigureFoundError();

            if (coordinates.equals("G8") || coordinates.equals("C8")) {
                if (makeCastling(coordinates, desc)) return true;
            }

            if (game.isMoveEnabled()) firstMove = false;
            return true;
        }

        throw new InvalidInputError();
    }

    @Override
    public String getFigureColour() {
        return colour;
    }
}
