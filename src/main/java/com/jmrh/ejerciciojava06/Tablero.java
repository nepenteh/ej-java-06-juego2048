
package com.jmrh.ejerciciojava06;

import java.util.Random;

/**
 *
 * @author josep
 */
public class Tablero {
    
    public static final int FIL = 4;
    public static final int COL = 4;
    private final int[][] tablero;
    
    public Tablero() {
        tablero = new int[FIL][COL];
        for(int f=0;f<FIL;f++) {
            for(int c=0;c<COL;c++) {
                tablero[f][c] = 0;
            }
        }    
        
        ponerDos();
        ponerDos();
        
               
    }
    
    private int vaciasEnFila(int f) {
        int n=0;
        for(int c=0;c<COL;c++) {
            if(tablero[f][c]==0) {
                n++;
            }
        }
        return n;
    }
    
    private int vaciasEnColumna(int c) {
        int n=0;
        for(int f=0;f<FIL;f++) {
            if(tablero[f][c]==0) {
                n++;
            }
        }
        return n;
    }
    
    private int vacias() {
        int n=0;
        for(int f=0;f<FIL;f++) {
            for(int c=0;c<COL;c++) {
                if(tablero[f][c]==0) {
                    n++;
                }
            }
        }
        return n;
    }
    
    
    private void ponerDos() {
        int f;
        int c;
        
        Random random = new Random();
        
        do {
            f = random.nextInt(FIL);
        } while(vaciasEnFila(f) == 0);
        
        do {
            c = random.nextInt(COL);
        } while(tablero[f][c] != 0);
        
        tablero[f][c] = 2;
        
    }
    
    
    public void mostrar() {
        for(int f=0;f<FIL;f++) {
            dibujaLineaHorizontal();
            dibujaEspacioHorizontal();
            System.out.print("|");
            for(int c=0;c<COL;c++) {
                System.out.print("    ");
                if(tablero[f][c]>=1000)
                    System.out.print(tablero[f][c]);
                else if(tablero[f][c] >= 100)
                    System.out.print(" "+tablero[f][c]);
                else if(tablero[f][c] >= 10)
                    System.out.print(" "+tablero[f][c]+" ");
                else if(tablero[f][c] > 0)
                    System.out.print("  "+tablero[f][c]+" ");
                else if(tablero[f][c] == 0)
                    System.out.print("    ");
                System.out.print("    |");
            }
            System.out.print("\n");
            dibujaEspacioHorizontal();
        } 
        dibujaLineaHorizontal();
    }    
    
    private void dibujaLineaHorizontal() {
        
        System.out.print("|");
        for(int c=0;c<COL;c++) {
            for(int i=0;i<12;i++) {
                System.out.print("-");
            }
            System.out.print("|");
        }
        System.out.print("\n");
        
    }
    
    private void dibujaEspacioHorizontal() {
         System.out.print("|");
        for(int c=0;c<COL;c++) {
            for(int i=0;i<12;i++) {
                System.out.print(" ");
            }
            System.out.print("|");
        }
        System.out.print("\n");
    }
    
    public boolean ganador() {
        for(int f=0;f<FIL;f++) {            
            for(int c=0;c<COL;c++) { 
                if(tablero[f][c]==2048) return true;
            }           
        }  
        return false;
    }
    
    public boolean finPartida() {
        return (ganador() || vacias()==0);
    }
    
    public void moverArriba() {
        for(int c=0; c<COL; c++) {
            moverArribaColumna(c);
        }
        if(!finPartida())
                ponerDos();
    }
    
    private void moverArribaColumna(int c) {
        colocarArriba(c);
        sumarArriba(c);
        colocarArriba(c);
    }
    
    private void colocarArriba(int c) {
        
        if(vaciasEnColumna(c) < COL) {
            for(int veces = 0;veces < COL-1;veces++) {
                for(int f=0;f<FIL-1;f++) {
                    if(tablero[f][c]==0) {
                        tablero[f][c] = tablero[f+1][c];
                        tablero[f+1][c] = 0;
                    }
                }
            }
        }
        
    }
    
    
    private void sumarArriba(int c) {
        if(vaciasEnColumna(c)<FIL-1) {
            for(int f=0;f<FIL-1;f++) {
                if(tablero[f][c] == tablero[f+1][c]) {
                    tablero[f][c] *= 2;
                    tablero[f+1][c] = 0;
                }
            }
        }
    }
    
    
    
    
    
    public void moverAbajo() {
        for(int c=0; c<COL; c++) {
            moverAbajoColumna(c);
        }
        if(!finPartida())
                ponerDos();
    }
    
    private void moverAbajoColumna(int c) {
        colocarAbajo(c);
        sumarAbajo(c);
        colocarAbajo(c);
    }
    
    private void colocarAbajo(int c) {
        
        if(vaciasEnColumna(c) < COL) {
            for(int veces = 0;veces < COL-1;veces++) {
                for(int f=FIL-1;f>0;f--) {
                    if(tablero[f][c]==0) {
                        tablero[f][c] = tablero[f-1][c];
                        tablero[f-1][c] = 0;
                    }
                }
            }
        }
        
    }
    
    
    private void sumarAbajo(int c) {
        if(vaciasEnColumna(c)<FIL-1) {
            for(int f=FIL-1;f>0;f--) {
                if(tablero[f][c] == tablero[f-1][c]) {
                    tablero[f][c] *= 2;
                    tablero[f-1][c] = 0;
                }
            }
        }
    }
    
    
    
    public void moverDerecha() {
        for(int f=0; f<COL; f++) {
            moverDerechaFila(f);
        }
        if(!finPartida())
                ponerDos();
    }
    
    private void moverDerechaFila(int f) {
        colocarDerecha(f);
        sumarDerecha(f);
        colocarDerecha(f);
    }
    
    private void colocarDerecha(int f) {
        
        if(vaciasEnFila(f) < FIL) {
            for(int veces = 0;veces < COL-1;veces++) {
                for(int c=COL-1;c>0;c--) {
                    if(tablero[f][c]==0) {
                        tablero[f][c] = tablero[f][c-1];
                        tablero[f][c-1] = 0;
                    }
                }
            }
        }
        
    }
    
    
    private void sumarDerecha(int f) {
        if(vaciasEnFila(f)<FIL-1) {
            for(int c=COL-1;c>0;c--) {
                if(tablero[f][c] == tablero[f][c-1]) {
                    tablero[f][c] *= 2;
                    tablero[f][c-1] = 0;
                }
            }
        }
    }
    
    
    public void moverIzquierda() {
        for(int f=0; f<FIL; f++) {
            moverIzquierdaFila(f);
        }
        if(!finPartida())
                ponerDos();
    }
    
    private void moverIzquierdaFila(int f) {
        colocarIzquierda(f);
        sumarIzquierda(f);
        colocarIzquierda(f);
    }
    
    private void colocarIzquierda(int f) {
        
        if(vaciasEnFila(f) < FIL) {
            for(int veces = 0;veces < COL-1;veces++) {
                for(int c=0;c<COL-1;c++) {
                    if(tablero[f][c]==0) {
                        tablero[f][c] = tablero[f][c+1];
                        tablero[f][c+1] = 0;
                    }
                }
            }
        }
        
    }
    
    
    private void sumarIzquierda(int f) {
        if(vaciasEnFila(f)<FIL-1) {
            for(int c=0;c<COL-1;c++) {
                if(tablero[f][c] == tablero[f][c+1]) {
                    tablero[f][c] *= 2;
                    tablero[f][c+1] = 0;
                }
            }
        }
    }
      
}
