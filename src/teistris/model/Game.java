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

import teistris.model.Piece;
import teistris.model.Square;
import teistris.view.MainWindow;
import java.util.HashMap;
import teistris.view.TeistrisPanel;

/**
 * Clase que implementa o comportamento do xogo do Tetris
 *
 * @author Profe de Programación
 */
public class Game {
    /*
    *atributo para poder sumar linea cuando se complete una
    */
    private int lines;

    private HashMap<String, Square> groundSquares;
    /**
     * Constante que define o tamaño en pixels do lado dun cadrado
     */
    public final static int SQUARE_SIDE = 20;
    /**
     * Constante que define o valor máximo da coordenada x no panel de cadrados
     */
    public final static int MAX_X = 240;
    /**
     * Constante que define o valor máximo da coordenada y no panel de cadrados
     */
    public final static int MAX_Y = 280;

    /**
     * Referenza á peza actual do xogo, que é a única que se pode mover
     */
    private Piece currentPiece;

    /**
     * Referenza á ventá principal do xogo
     */
    private TeistrisPanel mainWindow;

    /**
     * Flag que indica se o xogo está en pausa ou non
     */
    private boolean paused = false;

    /**
     * Número de liñas feitas no xogo
     */
    private int numberOfLines = 0;
    private Piece getSquare;

    /**
     * @return Referenza á ventá principal do xogo
     */
    public TeistrisPanel getMainWindow() {
        return mainWindow;
    }

    /**
     * @param mainWindow Ventá principal do xogo a establecer
     */
    public void setMainWindow(TeistrisPanel mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * @return O estado de pausa do xogo
     */
    public boolean isPaused() {
        return paused;
    }

    /**
     * @param paused O estado de pausa a establecer
     */
    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    /**
     * @return Número de liñas feitas no xogo
     */
    public int getNumberOfLines() {
        return numberOfLines;
    }

    /**
     * @param numberOfLines O número de liñas feitas no xogo
     */
    public void setNumberOfLines(int numberOfLines) {
        this.numberOfLines = numberOfLines;
    }

    /**
     * Construtor da clase, que crea unha primeira peza
     *
     * @param mainWindow Referenza á ventá principal do xogo
     */
    public Game(TeistrisPanel mainWindow) {
        this.mainWindow = mainWindow;
        this.createNewPiece();
        groundSquares = new HashMap();
    }

    /**
     * Move a ficha actual á dereita, se o xogo non está pausado
     */
    public void movePieceRight() {
        if (!paused) {
            currentPiece.moveRight();
        }
    }

    /**
     * Move a ficha actual á esquerda, se o xogo non está pausado
     */
    public void movePieceLeft() {
        if (!paused) {
            currentPiece.moveLeft();
        }
    }

    /**
     * Rota a ficha actual, se o xogo non está pausado
     */
    public void rotatePiece() {
        if (!paused) {
            currentPiece.rotate();
        }
    }

    /**
     * Move a peza actual abaixo, se o xogo non está pausado Se a peza choca con
     * algo e xa non pode baixar, pasa a formar parte do chan e créase unha nova
     * peza
     */
    public void movePieceDown() {
        if ((!paused) && (!currentPiece.moveDown())) {
            this.addPieceToGround();
            this.createNewPiece();
            if (this.hitPieceTheGround()) {
                this.mainWindow.showGameOver();
            }
        }
    }

    /**
     * Método que permite saber se unha posición x,y é válida para un cadrado
     *
     * @param x Coordenada x
     * @param y Coordenada y
     * @return true se esa posición é válida, se non false
     */
    public boolean isValidPosition(int x, int y) {
        if ((x == MAX_X) || (x < 0) || (y == MAX_Y)) {
            return false; // La posición está fuera de los límites
        }
        String coordinates = String.valueOf(x) + "," + String.valueOf(y);
        if (groundSquares.containsKey(coordinates)) {
            return false;
        }
        return true;
    }

    /**
     * Crea unha nova peza e a establece como peza actual do xogo
     */
    private void createNewPiece() {
        int pieceType = new java.util.Random().nextInt(2);
        
       switch(pieceType) {
            case 0:
                currentPiece = new SquarePiece(this);
                break;
            case 1:
                currentPiece = new LPiece(this);
                break;
        }
    }

    /**
     * Engade unha peza ao chan
     */
    private void addPieceToGround() {
        Square[] pieceSquares = currentPiece.getSquares(); // Obtenemos los cuadrados de la pieza actual

        for (Square square : pieceSquares) {
            String coordinates = square.getCoordinates();

            // Agrega el cuadrado al HashMap groundSquares
            groundSquares.put(coordinates, square);
        }

        this.deleteCompletedLines();

    }

    /**
     * Se os cadrados que están forman unha liña completa, bórranse eses
     * cadrados do chan e súmase unha nova liña no número de liñas realizadas
     */
    private void deleteCompletedLines() {
        for (int y = 0; y < MAX_Y; y += Game.SQUARE_SIDE) {

            boolean rawCompleted = true;

            for (int x = 0; (x < Game.MAX_X) && (rawCompleted); x += Game.SQUARE_SIDE) {
                String coordinates = String.valueOf(x) + "," + String.valueOf(y);
                if (!groundSquares.containsKey(coordinates)) {
                    rawCompleted = false;
                }
            }

            if (rawCompleted) {
                lines ++;
                mainWindow.showNumberOfLines(lines);
                deleteLine(y);
            }
        }

    }

    /**
     * Borra todos os cadrados que se atopan na liña indicada pola coordenada y,
     * e baixa todos os cadrados que estean situados por enriba unha posición
     * cara abaixo
     *
     * @param y Coordenada y da liña a borrar
     */
    private void deleteLine(int y) {
        // Borramos los cuadrados de la fila y
        for (int x = 0; x < MAX_X; x += Game.SQUARE_SIDE) {
            mainWindow.deleteSquare(groundSquares.get(x + "," + y).getLblSquare());
            groundSquares.remove(x + "," + y);
        }

        // Hacemos caer los cuadrados que están por encima
        for (int raw = y - SQUARE_SIDE; raw >= 0; raw -= SQUARE_SIDE) {
            for (int x = 0; x < MAX_X; x += Game.SQUARE_SIDE) {
                if (groundSquares.containsKey(x + "," + raw)) {
                    Square s = groundSquares.get(x + "," + raw);
                    groundSquares.remove(x + "," + raw);

                    // Actualizar la posición del cuadrado
                    s.setY(raw + SQUARE_SIDE);
                    groundSquares.put(s.getCoordinates(), s);
                }
            }
        }
    }

    /**
     * Comproba se a peza actual choca cos cadrados do chan
     *
     * @return true se a peza actual choca cos cadrados do chan; se non false
     */
    private boolean hitPieceTheGround() {
        for (Square square : currentPiece.getSquares()) {
            if (groundSquares.containsKey(square.getCoordinates())) {
                return true;
            }
        }
        return false;
    }
}
