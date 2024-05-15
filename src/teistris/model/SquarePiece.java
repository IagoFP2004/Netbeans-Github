package teistris.model;

import teistris.model.Game;
import teistris.model.Piece;
import teistris.model.Square;
import java.awt.Color;

/**
 *
 * @author iago.franciscoperez
 */
public class SquarePiece extends Piece {

    public SquarePiece(Game game) {
        this.game = game;
        squares = new Square[4];

        squares[0] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, 0, Color.BLUE, game);
        squares[1] = new Square(Game.MAX_X / 2, 0, Color.BLUE, game);
        squares[2] = new Square(Game.MAX_X / 2 - Game.SQUARE_SIDE, Game.SQUARE_SIDE,
                Color.BLUE, game);
        squares[3] = new Square(Game.MAX_X / 2, Game.SQUARE_SIDE, Color.BLUE, game);
    }

    
    
}
