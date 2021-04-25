package ChessGame.ChessBody.FigureType.QueenColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.NoFigure;
import ChessGame.ChessBody.FigureType.PawnColour.WhitePawn;
import ChessGame.ChessBody.FigureType.Queen;

import java.util.HashMap;

public class BlackQueen extends Queen {
    String colour;

    @Override
    public ChessFigure deepClone() {
        return new BlackQueen(this.getCoordinates());
    }

    public BlackQueen(String coordinates) {
        super(coordinates);
        this.colour = BLACK;
    }

    @Override
    public String getFigureColour() {
        return colour;
    }

    @Override
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        HashMap<String,ChessFigure> mapForTest = deepCloneMap(desc);
        mapForTest.put(this.getCoordinates(), new NoFigure(this.getCoordinates()));
        if (game.getBlackKing().isUnderCheck(mapForTest)) return true;
        return false;
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

                if (desc.get(coordinates).getFigureColour().equals(ChessFigure.WHITE) || desc.get(coordinates).getClassName().equals("NoFigure"))
                    return true;
            }
        }



        throw new InvalidInputError();
    }
}
