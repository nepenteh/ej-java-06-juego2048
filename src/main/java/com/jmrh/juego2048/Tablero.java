/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmrh.juego2048;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author josep
 */
public class Tablero implements Serializable {

    public static final int FIL = 4;
    public static final int COL = 4;
    private int[][] tablero;
    private boolean finJuego;
    private int puntuacion;

    public Tablero() {
        finJuego = false;
        puntuacion = 0;
        tablero = new int[FIL][COL];
        for (int f = 0; f < FIL; f++) {
            for (int c = 0; c < COL; c++) {
                tablero[f][c] = 0;
            }
        }
        ponerDos();
        ponerDos();
    }

    /**
     * Devuelve el número de casillas vacías de la fila f
     *
     * @param f
     * @return
     */
    private int vaciasEnFila(int f) {
        int n = 0;
        for (int c = 0; c < COL; c++) {
            if (tablero[f][c] == 0) {
                n++;
            }
        }
        return n;
    }

    /**
     * Devuelve el número de casillas vacías de la columna c
     *
     * @param c
     * @return
     */
    private int vaciasEnColumna(int c) {
        int n = 0;
        for (int f = 0; f < FIL; f++) {
            if (tablero[f][c] == 0) {
                n++;
            }
        }
        return n;
    }
    
    /**
     * Devuelve el número total de casillas vacías del tablero
     * @return 
     */
    private int vacias() {
        int n=0;
        for(int f=0;f<FIL;f++)
            for(int c=0;c<COL;c++)
                if(tablero[f][c]==0) n++;
        return n;
    }

    /**
     * Pone un 2 aleatorio en el tablero
     */
    public void ponerDos() {
        int f;
        int c;

        Random random = new Random();
        //obtiene una fila aleatoria que tenga casillas vacías
        do {
            f = random.nextInt(FIL);
        } while (vaciasEnFila(f) == 0);

        //obtiene una casilla en esa fila que esté vacía
        do {
            c = random.nextInt(COL);
        } while (tablero[f][c] != 0);

        tablero[f][c] = 2;
    }

    /**
     * Muestra el tablero actual. Se apoya en dos métodos adicionales:
     * dibujaLineaHorizontal, que dibuja líneas horizontales de separación
     * dibujaEspacioHoritontal, que mete separaciones para que las celdas sean
     * más grandes
     */
    public void mostrar() {
        for (int f = 0; f < FIL; f++) {
            dibujaLineaHorizontal();
            dibujaEspacioHorizontal();
            System.out.print("|");
            for (int c = 0; c < COL; c++) {
                System.out.print("    ");
                if (tablero[f][c] >= 1000) {
                    System.out.print(tablero[f][c]);
                } else if (tablero[f][c] >= 100) {
                    System.out.print(" " + tablero[f][c]);
                } else if (tablero[f][c] >= 10) {
                    System.out.print(" " + tablero[f][c] + " ");
                } else if (tablero[f][c] > 0) {
                    System.out.print("  " + tablero[f][c] + " ");
                } else if (tablero[f][c] == 0) {
                    System.out.print("    ");
                }
                System.out.print("    |");
            }
            System.out.print("\n");
            dibujaEspacioHorizontal();
        }
        dibujaLineaHorizontal();
    }

    private void dibujaLineaHorizontal() {
        System.out.print("|");
        for (int c = 0; c < COL; c++) {
            for (int i = 0; i < 12; i++) {
                System.out.print("-");
            }
            System.out.print("|");
        }
        System.out.println("\n");
    }

    private void dibujaEspacioHorizontal() {
        System.out.print("|");
        for (int c = 0; c < COL; c++) {
            for (int i = 0; i < 12; i++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.print("\n");
    }
    
    /**
     * Comprueba si el jugador ganó
     * @return 
     */
    public boolean ganador() {
        for(int f=0;f<FIL;f++)
            for(int c=0;c<COL;c++)
                if(tablero[f][c]==2048) return true;
        return false;
    }
    
    /**
     * Comprueba si se llegó a fin de la partida
     * @return 
     */
    public boolean finPartida() {
        return (ganador() || vacias()==0);
    }
    
    /**
     * Devuelve la puntuación obtenida
     * @return 
     */
    public int getPuntuacion() {
        return puntuacion;
    }

    /**
     * Realiza un movimiento abajo.
     * Se apoya en un método que hace ésto para cada columna
     */
    public void mueveAbajo() {
        for (int c = 0; c < COL; c++) 
            mueveAbajoColumna(c);
        if(!finPartida())
            ponerDos();
    }

    /**
     * Hace un movimiento abajo para la columna c
     * Se apoya en dos métodos: colocarAbajo, que sitúa todos los números abajo
     * sumarAbajo: que suma parejas
     * Luego se vuelve a llamar a colocarAbajo para que todos los números queden abajo de nuevo
     * @param c 
     */
    public void mueveAbajoColumna(int c) {
        colocarAbajo(c);
        sumarAbajo(c);
        colocarAbajo(c);
    }

    public void colocarAbajo(int c) {
        if (vaciasEnColumna(c) < 4) { //solo si hay casillas rellenas
            for (int veces = 0; veces < FIL - 1; veces++) {
                for (int f = FIL - 1; f > 0; f--) {
                    if (tablero[f][c] == 0) {
                        tablero[f][c] = tablero[f - 1][c];
                        tablero[f - 1][c] = 0;
                    }
                }
            }
        }
    }

    public void sumarAbajo(int c) {
        if (vaciasEnColumna(c) < 3) { //solo si hay al menos 2 casillas rellenas

            for (int f = FIL - 1; f > 0; f--) {
                if (tablero[f][c] == tablero[f - 1][c]) {
                    tablero[f][c] *= 2;
                    puntuacion += tablero[f][c];
                    tablero[f - 1][c] = 0;
                }
            }

        }
    }
    
    
    /**
     * Realiza un movimiento arriba.
     * Se apoya en un método que hace ésto para cada columna
     */
    public void mueveArriba() {
        for (int c = 0; c < COL; c++) 
            mueveArribaColumna(c);
        if(!finPartida())
            ponerDos();
    }

    /**
     * Hace un movimiento arriba para la columna c
     * Se apoya en dos métodos: colocarArriba, que sitúa todos los números arriba
     * sumarArriba: que suma parejas
     * Luego se vuelve a llamar a colocarArriba para que todos los números queden arriba de nuevo
     * @param c 
     */
    public void mueveArribaColumna(int c) {
        colocarArriba(c);
        sumarArriba(c);
        colocarArriba(c);
    }

    public void colocarArriba(int c) {
        if (vaciasEnColumna(c) < 4) { //solo si hay casillas rellenas
            for (int veces = 0; veces < FIL - 1; veces++) {
                for (int f = 0; f < FIL-1; f++) {
                    if (tablero[f][c] == 0) {
                        tablero[f][c] = tablero[f + 1][c];
                        tablero[f + 1][c] = 0;
                    }
                }
            }
        }
    }

    public void sumarArriba(int c) {
        if (vaciasEnColumna(c) < 3) { //solo si hay al menos 2 casillas rellenas

            for (int f = 0; f < FIL-1; f++) {
                if (tablero[f][c] == tablero[f + 1][c]) {
                    tablero[f][c] *= 2;
                    puntuacion += tablero[f][c];
                    tablero[f + 1][c] = 0;
                }
            }

        }
    }
    
    
    /**
     * Realiza un movimiento derecha.
     * Se apoya en un método que hace ésto para cada fila
     */
    public void mueveDerecha() {
        for (int f = 0; f < FIL; f++) 
            mueveDerechaFila(f);
        if(!finPartida())
            ponerDos();
    }

    /**
     * Hace un movimiento derecha para la fila f
     * Se apoya en dos métodos: colocarDerecha, que sitúa todos los números en la derecha
     * sumarDerecha: que suma parejas
     * Luego se vuelve a llamar a colocarDerecha para que todos los números queden a la derecha de nuevo
     * @param f 
     */
    public void mueveDerechaFila(int f) {
        colocarDerecha(f);
        sumarDerecha(f);
        colocarDerecha(f);
    }

    public void colocarDerecha(int f) {
        if (vaciasEnFila(f) < 4) { //solo si hay casillas rellenas
            for (int veces = 0; veces < COL - 1; veces++) {
                for (int c = COL - 1; c > 0; c--) {
                    if (tablero[f][c] == 0) {
                        tablero[f][c] = tablero[f][c - 1];
                        tablero[f][c - 1] = 0;
                    }
                }
            }
        }
    }

    public void sumarDerecha(int f) {
        if (vaciasEnFila(f) < 3) { //solo si hay al menos 2 casillas rellenas

            for (int c = COL - 1; c > 0; c--) {
                if (tablero[f][c] == tablero[f][c - 1]) {
                    tablero[f][c] *= 2;
                    puntuacion += tablero[f][c];
                    tablero[f][c - 1] = 0;
                }
            }

        }
    }
    
    
    /**
     * Realiza un movimiento izquierda.
     * Se apoya en un método que hace ésto para cada fila
     */
    public void mueveIzquierda() {
        for (int f = 0; f < FIL; f++) 
            mueveIzquierdaFila(f);
        if(!finPartida())
            ponerDos();
    }

    /**
     * Hace un movimiento izquierda para la fila f
     * Se apoya en dos métodos: colocarIzquierda, que sitúa todos los números izquierda
     * sumarIzquierda: que suma parejas
     * Luego se vuelve a llamar a colocarIzquierda para que todos los números queden izquierda de nuevo
     * @param f 
     */
    public void mueveIzquierdaFila(int f) {
        colocarIzquierda(f);
        sumarIzquierda(f);
        colocarIzquierda(f);
    }

    public void colocarIzquierda(int f) {
        if (vaciasEnFila(f) < 4) { //solo si hay casillas rellenas
            for (int veces = 0; veces < COL - 1; veces++) {
                for (int c = 0; c < COL-1; c++) {
                    if (tablero[f][c] == 0) {
                        tablero[f][c] = tablero[f][c + 1];
                        tablero[f][c + 1] = 0;
                    }
                }
            }
        }
    }

    public void sumarIzquierda(int f) {
        if (vaciasEnFila(f) < 3) { //solo si hay al menos 2 casillas rellenas

            for (int c = 0; c < COL-1; c++) {
                if (tablero[f][c] == tablero[f][c + 1]) {
                    tablero[f][c] *= 2;
                    puntuacion += tablero[f][c];
                    tablero[f][c + 1] = 0;
                }
            }

        }
    }
    
}
