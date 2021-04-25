package ChessGame.ChessBody.FigureType.BishopColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.Bishop;
import ChessGame.ChessBody.FigureType.NoFigure;

import java.util.HashMap;

public class WhiteBishop extends Bishop {
    String colour;

    @Override
    public ChessFigure deepClone() {
        return new WhiteBishop(this.getCoordinates());
    }

    public WhiteBishop(String coord) {
        super(coord);
        this.colour = WHITE;
    }

    @Override
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        HashMap<String,ChessFigure> mapForTest = deepCloneMap(desc);
        mapForTest.put(this.getCoordinates(), new NoFigure(this.getCoordinates()));
        if (game.getWhiteKing().isUnderCheck(mapForTest)) return true;
        return false;
    }

    @Override
    public String getFigureColour() {
        return colour;
    }

    @Override
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError, FigureFoundError, KingWillBeUnderCheckException {
        for (var availableMovesArray : this.getAvailableMoves())
        {
            if (availableMovesArray.contains(coordinates)) {

                if (isKingWillBeUnderCheck(desc,game)) throw new KingWillBeUnderCheckException();

                availableMovesArray.subList(availableMovesArray.indexOf(coordinates), availableMovesArray.size()).clear();
                for (var figureCoordinates: availableMovesArray) {
                    if (!desc.get(figureCoordinates).getClassName().equals("NoFigure")) {
                        throw new FigureFoundError();
                    }
                }

                if (desc.get(coordinates).getFigureColour().equals(ChessFigure.BLACK) || desc.get(coordinates).getClassName().equals("NoFigure"))
                    return true;
            }
        }



        throw new InvalidInputError();
    }
}
