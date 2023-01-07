/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.jmrh.juego2048;

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

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        menuPrincipal();
    }
    
    
    public static void menuPrincipal() {
        int opcion;
        do {
            System.out.println("\nMenú Principal\n");
            System.out.println("1. Iniciar nueva Partida");
            System.out.println("2. Continuar partida actual");
            System.out.println("3. Salir");
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
                    System.out.println("Fin del juego");
                    break;
                default:
                    System.out.println("Opción desconocida");
            }
        } while (opcion != 3);

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
        
        if(tablero.ganador() || tablero.finPartida()) {
            tablero.mostrar();
            partidaEnJuego = false;
            String mensaje = tablero.ganador()? "Enhorabuena! Has ganado!!!":"Fin de la partida.";
            System.out.println(mensaje);
        } 
                
    }
}
