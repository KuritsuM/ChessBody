package ChessGame.ChessBody.FigureType.KnightColour;

import ChessGame.ChessBody.ChessDesc;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.ChessFigure;
import ChessGame.ChessBody.FigureType.Knight;
import ChessGame.ChessBody.FigureType.NoFigure;

import java.util.HashMap;

public class WhiteKnight extends Knight {
    String colour;

    public WhiteKnight(String coordinates) {
        super(coordinates);
        this.colour = WHITE;
    }

    @Override
    public ChessFigure deepClone() {
        return new WhiteKnight(this.getCoordinates());
    }

    @Override
    public String getFigureColour() {
        return colour;
    }

    @Override
    public boolean isKingWillBeUnderCheck(HashMap<String, ChessFigure> desc, ChessDesc game) {
        HashMap<String,ChessFigure> mapForTest = deepCloneMap(desc);
        mapForTest.put(this.getCoordinates(), new NoFigure(this.getCoordinates()));
        if (game.getWhiteKing().isUnderCheck(mapForTest)) return true;
        return false;
    }

    @Override
    public boolean move(String coordinates, HashMap<String, ChessFigure> desc, ChessDesc game) throws InvalidInputError, KingWillBeUnderCheckException {

        if (this.getAvailableMoves().get(0).contains(coordinates)) {

            if (isKingWillBeUnderCheck(desc, game)) throw new KingWillBeUnderCheckException();

            if (!desc.get(coordinates).getFigureColour().equals(ChessFigure.WHITE))
                return true;
        }

        throw new InvalidInputError();
    }
}
