package ChessGame.ChessBody;

import ChessGame.ChessBody.ChessError.FigureFoundError;
import ChessGame.ChessBody.ChessError.InvalidInputError;
import ChessGame.ChessBody.ChessError.InvalidTurn;
import ChessGame.ChessBody.ChessError.KingWillBeUnderCheckException;
import ChessGame.ChessBody.FigureType.BishopColour.BlackBishop;
import ChessGame.ChessBody.FigureType.BishopColour.WhiteBishop;
import ChessGame.ChessBody.FigureType.King;
import ChessGame.ChessBody.FigureType.KingColour.BlackKing;
import ChessGame.ChessBody.FigureType.KingColour.WhiteKing;
import ChessGame.ChessBody.FigureType.KnightColour.BlackKnight;
import ChessGame.ChessBody.FigureType.KnightColour.WhiteKnight;
import ChessGame.ChessBody.FigureType.NoFigure;
import ChessGame.ChessBody.FigureType.PawnColour.BlackPawn;
import ChessGame.ChessBody.FigureType.PawnColour.WhitePawn;
import ChessGame.ChessBody.FigureType.QueenColour.BlackQueen;
import ChessGame.ChessBody.FigureType.QueenColour.WhiteQueen;
import ChessGame.ChessBody.FigureType.RookColour.BlackRook;
import ChessGame.ChessBody.FigureType.RookColour.WhiteRook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChessDesc {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    private ArrayList<String> pawnToQueenCoordinates;

    private King blackKing;
    private King whiteKing;
    private String lastMove;

    private boolean realMove;

    public King getBlackKing() {
        return blackKing;
    }

    public King getWhiteKing() {
        return whiteKing;
    }

    private String turnOfColour;

    protected ArrayList<String> history;
    protected HashMap<String, ChessFigure> desc;

    public ChessDesc() {
        desc = new HashMap<String, ChessFigure>();
        realMove = true;
        turnOfColour = ChessFigure.WHITE;


        pawnToQueenCoordinates = new ArrayList<>();
        for (int i = 1; i < 9; ++i) {
            for (char j = 'A'; j < 'I'; ++j) {
                if (i == 1 || i == 8) {
                    String coordinates = Character.toString(j) + Integer.toString(i);
                    pawnToQueenCoordinates.add(coordinates);
                }
            }
        }
        // i - цифра, j - буква
        for (int i = 1; i < 9; ++i) {
            for (int j = 1; j < 9; ++j) {
                switch (i) {
                    case 1: {
                        desc.put("A1", new WhiteRook("A1"));
                        desc.put("H1", new WhiteRook("H1"));

                        desc.put("B1", new WhiteKnight("B1"));
                        desc.put("G1", new WhiteKnight("G1"));

                        desc.put("C1", new WhiteBishop("C1"));
                        desc.put("F1", new WhiteBishop("F1"));

                        desc.put("D1", new WhiteQueen("D1"));
                        desc.put("E1", new WhiteKing("E1"));
                        whiteKing = (King) desc.get("E1");
                        whiteKing.setGame(this);
                    }
                        break;
                    case 2: {
                        char firstCoord, secondCoord;
                        firstCoord = (char) (j + 64);
                        secondCoord = (char) (i + '0');
                        String coordinates = String.valueOf(firstCoord) + String.valueOf(secondCoord);

                        desc.put(coordinates, new WhitePawn(coordinates));
                    }
                        break;
                    case 7: {
                        char firstCoord, secondCoord;
                        firstCoord = (char) (j + 64);
                        secondCoord = (char) (i + '0');
                        String coordinates = String.valueOf(firstCoord) + String.valueOf(secondCoord);

                        desc.put(coordinates, new BlackPawn(coordinates));
                    }
                        break;
                    case 8: {
                        desc.put("A8", new BlackRook("A8"));
                        desc.put("H8", new BlackRook("H8"));

                        desc.put("B8", new BlackKnight("B8"));
                        desc.put("G8", new BlackKnight("G8"));

                        desc.put("C8", new BlackBishop("C8"));
                        desc.put("F8", new BlackBishop("F8"));

                        desc.put("D8", new BlackQueen("D8"));
                        desc.put("E8", new BlackKing("E8"));
                        blackKing = (King) desc.get("E8");
                        blackKing.setGame(this);
                    }
                        break;
                    case 3: case 4: case 5: case 6: {
                        char firstCoord, secondCoord;
                        firstCoord = (char) (j + 64);
                        secondCoord = (char) (i + '0');
                        String coordinates = String.valueOf(firstCoord) + String.valueOf(secondCoord);

                        desc.put(coordinates, new NoFigure(coordinates));
                    }
                        break;
                }
            }
        }
    }

    public void turnOnMoves() {
        realMove = true;
    }

    public void turnOffMoves() {
        realMove = false;
    }

    public boolean isMoveEnabled() {
        return realMove;
    }

    public void drawDescInConsole() {
            boolean isWhiteBackground = true; // false == ANSI_BLACK
            // true  == ANSI_WHITE

            for (int i = 1; i < 9; ++i) {
                for (char j = 'A'; j < 'I'; ++j) {
                    String bgColour;
                    if (isWhiteBackground) {
                        bgColour = ANSI_WHITE_BACKGROUND;
                    } else bgColour = ANSI_GREEN_BACKGROUND;

                    String tempKey = String.valueOf(j) + String.valueOf(i);
                    ChessFigure figure = desc.get(tempKey);

                    String figureType, figureColour;

                    String className = figure.getClassName();
                    switch (className) {
                        case "Pawn":
                            figureType = "P";
                            break;
                        case "Rook":
                            figureType = "R";
                            break;
                        case "Knight":
                            figureType = "N";
                            break;
                        case "Queen":
                            figureType = "Q";
                            break;
                        case "King":
                            figureType = "K";
                            break;
                        case "Bishop":
                            figureType = "B";
                            break;
                        default:
                            figureType = " ";
                            break;
                    }

                    switch (figure.getFigureColour()) {
                        case ChessFigure.BLACK:
                            figureColour = ANSI_BLACK;
                            break;
                        case ChessFigure.WHITE:
                            figureColour = ANSI_YELLOW;
                            break;
                        default:
                            figureColour = ANSI_YELLOW;
                            break;
                    }

                    System.out.print(figureColour + bgColour + figureType + ANSI_RESET);
                    isWhiteBackground = !isWhiteBackground;
                }
                System.out.println("  " + i + ANSI_RESET);
                isWhiteBackground = !isWhiteBackground;
            }
            System.out.println("ABCDEFGH");

    }

    private void changeTurnOfAnotherColour() {
        if (turnOfColour.equals(ChessFigure.WHITE)) turnOfColour = ChessFigure.BLACK;
        else turnOfColour = ChessFigure.WHITE;
    }

    private boolean checkTurnOfColour(ChessFigure figure) throws InvalidTurn {
        if (figure.getFigureColour().equals(turnOfColour)) return true;

        throw new InvalidTurn();
    }

    public HashMap<String, ChessFigure> getDesc() {
        return desc;
    }

    public String getConsoleInput() {
        return new Scanner(System.in).nextLine().toUpperCase();
    }

    private void checkAndChangePawn() {
        for (var coordinates : pawnToQueenCoordinates) {
            ChessFigure figure = desc.get(coordinates);

            if (figure.getClassName().equals("Pawn")) {
                if (figure.getFigureColour().equals(ChessFigure.WHITE)) {
                    desc.put(coordinates, new WhiteQueen(coordinates));
                }
                if (figure.getFigureColour().equals(ChessFigure.BLACK)) {
                    desc.put(coordinates, new BlackQueen(coordinates));
                }
            }
        }
    }

    public String getLastMove() {
        return lastMove;
    }

    public String moveFigure(String input) throws  ArrayIndexOutOfBoundsException {
        drawDescInConsole();

        if (whiteKing.isUnderMate(desc)) { System.out.println("Белый король под матом"); return ChessFigure.BLACK; };
        if (blackKing.isUnderMate(desc)) { System.out.println("Черный король под матом"); return ChessFigure.WHITE; };

        if (whiteKing.isStalemated(desc))
            if (whiteKing.isTotalyStalemated(desc))
                return "STALEMATED";

        if (blackKing.isStalemated(desc))
            if (blackKing.isTotalyStalemated(desc))
                return "STALEMATED";

        try {
            String[] step = input.split("-");
            ChessFigure figure = desc.get(step[0]);

            if (checkTurnOfColour(figure)) {

                figure.setCoordinates(step[0]);

                if (figure.move(step[1], desc, this)) {

                    desc.put(step[0], new NoFigure(step[0]));

                    figure.setCoordinates(step[1]);
                    desc.put(step[1], figure);

                    checkAndChangePawn();
                    lastMove = input;
                    changeTurnOfAnotherColour();
                }
            }
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Слишком много аргументов в воде");
        }
        catch (InvalidInputError e) {
            e.what();
        }
        catch (FigureFoundError e) {
            e.what();
        }
        catch (InvalidTurn e) {
            e.what();
        }
        catch (KingWillBeUnderCheckException e) {
            e.what();
        }
        catch (NullPointerException e) {
            System.out.println("Ошибка ввода");
        }

        return "#F0F0F0";
    }
}
