package ChessGame.ChessBody.FigureType.RookColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.NoFigure;
import ChessGame.ChessBody.FigureType.QueenColour.WhiteQueen;
import ChessGame.ChessBody.FigureType.Rook;

import java.util.ArrayList;
import java.util.HashMap;

public class BlackRook extends Rook {
    String colour;

    @Override
    public ChessFigure deepClone() {
        return new BlackRook(this.getCoordinates());
    }

    public BlackRook(String coordinates) {
        super(coordinates);
        this.colour = BLACK;
        this.firstMove = true;
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

                if (desc.get(coordinates).getFigureColour().equals(ChessFigure.WHITE) || desc.get(coordinates).getClassName().equals("NoFigure")) {
                    if (game.isMoveEnabled()) this.firstMoveDone();
                    return true;
                }
            }
        }



        throw new InvalidInputError();
    }
}
