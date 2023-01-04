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
    
    private String nombres[];
    private int puntuaciones[];
    private int elementos;
    
        
    public HallOfFame() {
        elementos = 0;
        nombres = new String[10];
        puntuaciones = new int[10];
    }
    
    public void add(String nombre, int puntuacion) {
        if(puntuacion>=puntuaciones[9]) {
            puntuaciones[9] = puntuacion;
            nombres[9] = nombre;
        }
        
        int puntAux;
        String nombreAux;
        int i=8;
        while(puntuaciones[i]<puntuaciones[i+1] && i>0) {
            puntAux = puntuaciones[i];
            nombreAux = nombres[i];
            puntuaciones[i]=puntuaciones[i+1];
            nombres[i]=nombres[i+1];
            puntuaciones[i+1]=puntAux;
            nombres[i+1]=nombreAux;
            i--;
        }
    }
    
    public void mostrar() {
        System.out.println("HALL OF FAME");
        for(int i=0;i<10;i++) {
            if(puntuaciones[i]>0) 
                System.out.println(nombres[i]+" -> "+puntuaciones[i]);
        }
    }
}
