/*
 * Copyright (C) 2019 Antonio de Andrés Lema
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package teistris.model;

import teistris.model.Square;
import java.awt.Color;
import static teistris.model.Game.MAX_X;

/**
 * Clase que implementa a peza cadrada do xogo do Tetris
 *
 * @author Profe de Programación
 */
public abstract class Piece {

    /**
     * Referenza ao obxecto xogo
     */
    protected Game game;
//metodos gett y sett  de la referencia al juego
    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
    

    /**
     * Referenzas aos catro cadrados que forman a peza
     */
    protected  Square[] squares;
//metodos gett y sett de los cuadrados que forman la pieza

    public Square[] getSquares() {
        return squares;
    }


    /**
     * Move a ficha á dereita se é posible
     *
     * @param squares
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveRight() {
       // Comprueba si es posible mover cada uno de los cuatro bloques de la ficha hacia la derecha
    if (game.isValidPosition(squares[0].getX() + Game.SQUARE_SIDE, squares[0].getY()) &&
        game.isValidPosition(squares[1].getX() + Game.SQUARE_SIDE, squares[1].getY()) &&
        game.isValidPosition(squares[2].getX() + Game.SQUARE_SIDE, squares[2].getY()) &&
        game.isValidPosition(squares[3].getX() + Game.SQUARE_SIDE, squares[3].getY())) {
        // Si es posible, actualiza las posiciones de los cuatro bloques
        squares[0].setX(squares[0].getX() + Game.SQUARE_SIDE);
        squares[1].setX(squares[1].getX() + Game.SQUARE_SIDE);
        squares[2].setX(squares[2].getX() + Game.SQUARE_SIDE);
        squares[3].setX(squares[3].getX() + Game.SQUARE_SIDE);
        return true;
    } else {
        return false; // No se pudo mover la ficha hacia la derecha
    }

    }

    /**
     * Move a ficha á esquerda se é posible
     *
     * @param squares
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveLeft() {
       // Comprueba si es posible mover cada uno de los cuatro bloques de la ficha hacia la izquierda
    if (game.isValidPosition(squares[0].getX() - Game.SQUARE_SIDE, squares[0].getY()) &&
        game.isValidPosition(squares[1].getX() - Game.SQUARE_SIDE, squares[1].getY()) &&
        game.isValidPosition(squares[2].getX() - Game.SQUARE_SIDE, squares[2].getY()) &&
        game.isValidPosition(squares[3].getX() - Game.SQUARE_SIDE, squares[3].getY())) {
        // Si es posible, actualiza las posiciones de los cuatro bloques
        squares[0].setX(squares[0].getX() - Game.SQUARE_SIDE);
        squares[1].setX(squares[1].getX() - Game.SQUARE_SIDE);
        squares[2].setX(squares[2].getX() - Game.SQUARE_SIDE);
        squares[3].setX(squares[3].getX() - Game.SQUARE_SIDE);
        return true;
    } else {
        return false; // No se pudo mover la ficha hacia la izquierda
    }
    }

    /**
     * Move a ficha a abaixo se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean moveDown() {
        // Comprueba si es posible mover cada uno de los cuatro bloques de la ficha hacia abajo
    if (game.isValidPosition(squares[0].getX(), squares[0].getY() + Game.SQUARE_SIDE) &&
        game.isValidPosition(squares[1].getX(), squares[2].getY() + Game.SQUARE_SIDE) &&
        game.isValidPosition(squares[2].getX(), squares[2].getY() + Game.SQUARE_SIDE) &&
        game.isValidPosition(squares[3].getX(), squares[3].getY() + Game.SQUARE_SIDE)) {
        // Si es posible, actualiza las posiciones de los cuatro bloques
        squares[0].setY(squares[0].getY() + Game.SQUARE_SIDE);
        squares[1].setY(squares[1].getY() + Game.SQUARE_SIDE);
        squares[2].setY(squares[2].getY() + Game.SQUARE_SIDE);
        squares[3].setY(squares[3].getY() + Game.SQUARE_SIDE);
        return true;
    } else {        // A rotación da ficha cadrada non supón ningunha variación na ficha,
        // por iso simplemente devolvemos true
        return false; // No se pudo mover la ficha hacia abajo
    }
    }

    /**
     * Rota a ficha se é posible
     *
     * @return true se o movemento da ficha é posible, se non false
     */
    public boolean rotate() {
        // A rotación da ficha cadrada non supón ningunha variación na ficha,
        // por iso simplemente devolvemos true
        return true;
    }
}

    