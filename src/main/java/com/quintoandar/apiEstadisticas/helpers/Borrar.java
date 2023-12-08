package com.quintoandar.apiEstadisticas.helpers;

import org.springframework.stereotype.Component;


public class Borrar {
    private String saludo;

    public void saludar(String saludo){

        this.saludo = saludo;
        System.out.printf("Hola desde el contructor de borrar " + this.saludo);
    }

    public void saludar(){
        System.out.printf("Hola desde el contructor de borrar " + this.saludo);
    }

}
