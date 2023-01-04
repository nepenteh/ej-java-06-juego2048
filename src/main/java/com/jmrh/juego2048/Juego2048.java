/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.jmrh.juego2048;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *
 * @author josep
 */
public class Juego2048 {

    public static Scanner scanner;
    public static Tablero tablero;
    public static boolean partidaEnJuego = false;
    public static String nombre;
    public static HallOfFame hof;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.print("Introduzca su nombre: ");
        nombre = scanner.nextLine();
        recuperarCrearHallOfFame();
        menuPrincipal();
        guardarHallOfFame();
    }
    
    public static void recuperarCrearHallOfFame() {
        
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream("hall_of_fame.dat");
            ObjectInputStream entrada=new ObjectInputStream(fileIn);
            hof = (HallOfFame) entrada.readObject();
        } catch (FileNotFoundException ex) {
            hof = new HallOfFame();
        } catch (Exception ex) {
            
        } finally {
            try {
                fileIn.close();
            } catch (Exception ex) {
                
            }
        }
            
    }
    
    public static void guardarHallOfFame() {
        
        FileOutputStream  fileOut = null;
        try {
            fileOut = new FileOutputStream("hall_of_fame.dat");
            ObjectOutputStream salida=new ObjectOutputStream(fileOut);
            salida.writeObject(hof);
        } catch (Exception ex) {
            
        } finally {
            try {
                fileOut.close();
            } catch (Exception ex) {
                
            }
        }
         
    }
    
    
    public static void recuperarTablero() {
        
        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream("partida.saved");
            ObjectInputStream entrada=new ObjectInputStream(fileIn);
            tablero = (Tablero) entrada.readObject();
            System.out.println("Partida recuperada");
        } catch (Exception ex) {
            
        } finally {
            try {
                fileIn.close();
            } catch (Exception ex) {
                
            }
        }
            
    }
    
    public static void guardarTablero() {
        
        FileOutputStream  fileOut = null;
        try {
            fileOut = new FileOutputStream("partida.saved");
            ObjectOutputStream salida=new ObjectOutputStream(fileOut);
            salida.writeObject(tablero);
            System.out.println("Partida guardada");
        } catch (Exception ex) {
            
        } finally {
            try {
                fileOut.close();
            } catch (Exception ex) {
                
            }
        }
         
    }

    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\nMenú Principal\n");
            System.out.println("1. Iniciar nueva Partida");
            System.out.println("2. Continuar partida actual");
            System.out.println("3. Guardar la partida actual");
            System.out.println("4. Recuperar la partida guardada");
            System.out.println("5. Ver el tablero de la fama");
            System.out.println("6. Salir");
            System.out.print("Introduce opción: ");
            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch(Exception e) {
                opcion = 0;
            }
            switch (opcion) {
                case 1:
                    iniciarPartida();
                    break;
                case 2:
                    if (!partidaEnJuego) {
                        System.out.println("No hay partida en juego");
                    } else {
                        continuarPartida();
                    }
                    break;
                case 3:
                    guardarTablero();
                    break;
                case 4:
                    recuperarTablero();
                    partidaEnJuego = true;
                    break;
                case 5:
                    hof.mostrar();
                    break;
                case 6:
                    System.out.println("Fin del juego");
                    break;
                default:
                    System.out.println("Opción desconocida");
            }
        } while (opcion != 6);

    }
    
    public static void iniciarPartida() {
        tablero = new Tablero();
        partidaEnJuego = true;
        juego();
    }
    
    public static void continuarPartida() {
        juego();
    }
    public static void juego() {
                       
        String direccion;

        do {
            tablero.mostrar();
            System.out.println("izq (a) / der (d) / arriba (w) / abajo (s) / menu (m): ");
            direccion = scanner.nextLine();
            switch (direccion) {
                case "w":
                    tablero.mueveArriba();
                    break;
                case "s":
                    tablero.mueveAbajo();
                    break;
                case "a":
                    tablero.mueveIzquierda();
                    break;
                case "d":
                    tablero.mueveDerecha();
                    break;
                case "m":
                    System.out.println("Saliendo al menú...");
                    break;
                default:
                    System.out.println("Opción desconocida");
            }
        } while (!direccion.equals("m") && !tablero.finPartida());
        
        if(tablero.ganador()) {
            tablero.mostrar();
            System.out.println("Enhorabuena! Has ganado!!!");
            partidaEnJuego = false;
            hof.add(nombre, tablero.getPuntuacion());
        } else if(tablero.finPartida()) {
            tablero.mostrar();
            System.out.println("Fin de la partida.");
            partidaEnJuego = false;
            hof.add(nombre, tablero.getPuntuacion());
        }
        
    }
}
