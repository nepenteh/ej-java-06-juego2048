/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.jmrh.juego2048;

import java.io.Serializable;

/**
 *
 * @author josep
 */
public class HallOfFame implements Serializable {

    public static final int TAMANIO = 10;

    private final String nombres[];
    private final int puntuaciones[];

    public HallOfFame() {
        nombres = new String[TAMANIO];
        puntuaciones = new int[TAMANIO];
    }

    public void add(String nombre, int puntuacion) {
        if (puntuacion >= puntuaciones[TAMANIO - 1]) {
            puntuaciones[TAMANIO - 1] = puntuacion;
            nombres[TAMANIO - 1] = nombre;

            int puntAux;
            String nombreAux;
            int i = TAMANIO-2;
            while (i >= 0 && puntuaciones[i] < puntuaciones[i + 1]) {
                puntAux = puntuaciones[i];
                nombreAux = nombres[i];
                puntuaciones[i] = puntuaciones[i + 1];
                nombres[i] = nombres[i + 1];
                puntuaciones[i + 1] = puntAux;
                nombres[i + 1] = nombreAux;
                i--;
            }
        }
    }

    public void mostrar() {
        System.out.println("HALL OF FAME");
        for (int i = 0; i < TAMANIO; i++) {
            if (puntuaciones[i] > 0) {
                System.out.println(nombres[i] + " -> " + puntuaciones[i]);
            }
        }
    }
}
