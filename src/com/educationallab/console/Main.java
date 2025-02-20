package com.educationallab.console;
import com.educationallab.console.Facultad;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       Profesor ing1 = new Profesor(1.0,"Diana","Cardona","email@unillanos.edu.co","TF") ;
       Facultad facultad = new Facultad("FCBI",ing1);
       String info=facultad.toString();
        System.out.println(" Datos creados "+ing1);

    }
}