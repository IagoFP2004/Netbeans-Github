package teistris.model;

import teistris.model.Game;
import teistris.model.Piece;
import teistris.model.Square;
import java.awt.Color;

/**
 *
 * @author iago.franciscoperez
 */
public class LPiece extends Piece {

    private int position;

    public LPiece(Game game) {
        this.game = game;
        squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.MAGENTA, game);
        squares[1] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE, Color.MAGENTA, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE * 2, Color.MAGENTA, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE * 2, Color.MAGENTA, game);
        position = 0;
    }

    @Override
    public boolean rotate() {
        boolean valid;
        switch (position) {
            case 0:
                valid = game.isValidPosition(squares[0].getX() - Game.SQUARE_SIDE, squares[0].getY() + Game.SQUARE_SIDE) 
                        && game.isValidPosition(squares[2].getX() + Game.SQUARE_SIDE, squares[2].getY() - Game.SQUARE_SIDE)
                        && game.isValidPosition(squares[2].getX(), squares[2].getY() - Game.SQUARE_SIDE);
                //Se esta en posicion cero pasa para uno
                if(valid){
                squares[0].setX(squares[0].getX() - Game.SQUARE_SIDE);
                squares[0].setY(squares[0].getY() + Game.SQUARE_SIDE);
                squares[2].setX(squares[2].getX() + Game.SQUARE_SIDE);
                squares[2].setY(squares[2].getY() - Game.SQUARE_SIDE);
                squares[3].setX(squares[2].getX());
                squares[3].setY(squares[2].getY() - Game.SQUARE_SIDE);
                position = 1;
                }
                break;
            case 1:
                valid= game.isValidPosition(squares[0].getX() + Game.SQUARE_SIDE, squares[0].getY() + Game.SQUARE_SIDE)
                        && game.isValidPosition(squares[2].getX() - Game.SQUARE_SIDE, squares[2].getY() - Game.SQUARE_SIDE)
                        && game.isValidPosition(squares[2].getX() - Game.SQUARE_SIDE, squares[2].getY());
                if(valid){
                squares[0].setX(squares[0].getX() + Game.SQUARE_SIDE);
                squares[0].setY(squares[0].getY() + Game.SQUARE_SIDE);
                squares[2].setX(squares[2].getX() - Game.SQUARE_SIDE);
                squares[2].setY(squares[2].getY() - Game.SQUARE_SIDE);
                squares[3].setX(squares[2].getX() - Game.SQUARE_SIDE);
                squares[3].setY(squares[2].getY());
                position = 2;
                }
                break;
            case 2:
                valid = game.isValidPosition(squares[0].getX() + Game.SQUARE_SIDE, squares[0].getY() - Game.SQUARE_SIDE)
                        && game.isValidPosition(squares[2].getX() - Game.SQUARE_SIDE, squares[2].getY() + Game.SQUARE_SIDE)
                        && game.isValidPosition(squares[2].getX(), squares[2].getY() + Game.SQUARE_SIDE);
                if(valid){
                squares[0].setX(squares[0].getX() + Game.SQUARE_SIDE);
                squares[0].setY(squares[0].getY() - Game.SQUARE_SIDE);
                squares[2].setX(squares[2].getX() - Game.SQUARE_SIDE);
                squares[2].setY(squares[2].getY() + Game.SQUARE_SIDE);
                squares[3].setX(squares[2].getX());
                squares[3].setY(squares[2].getY() + Game.SQUARE_SIDE);
                position = 3;
                }
                break;
            case 3:
                valid = game.isValidPosition(squares[0].getX()- Game.SQUARE_SIDE, squares[0].getY()- Game.SQUARE_SIDE)
                        && game.isValidPosition(squares[2].getX()+Game.SQUARE_SIDE, squares[2].getX() + Game.SQUARE_SIDE)
                        && game.isValidPosition(position, squares[2].getY());
                if(valid){
                squares[0].setX(squares[0].getX()- Game.SQUARE_SIDE);
                squares[0].setY(squares[0].getY()- Game.SQUARE_SIDE);
                squares[2].setX(squares[2].getX()+Game.SQUARE_SIDE);
                squares[2].setY(squares[2].getY() + Game.SQUARE_SIDE);
                squares[3].setX(squares[2].getX() + Game.SQUARE_SIDE);
                squares[3].setY(squares[2].getY());
                position=0;
                }
        }
        return false;
    }

}
