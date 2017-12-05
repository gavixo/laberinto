/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Point;

/**
 *
 * @author gavixo
 */
public class matriz {

    public int Filas = 0, Columnas = 0;
    public char[][] matrix;
    public Point ini , fin ;
    private Vector<char[]> lineas = new Vector();

    /**
     * Constructor. Esta clase obtiene parámetros del archivo de laberinto. los
     * atributos son n filas m columnas matriz con el contenido del archivo
     *
     * @param direccion path del archivo a analizar.
     *  
     */
    public matriz(String direccion) {
        int columnas = 0;

        File archivo = new File(direccion);
        FileReader lector;
        try {
            lector = new FileReader(archivo);
            BufferedReader bufer = new BufferedReader(lector);
            String linea;
            while ((linea = bufer.readLine()) != null) {
                char[] caracteres = linea.toCharArray();
                lineas.add(caracteres);
            }
            char[] carac = lineas.elementAt(0);
            Columnas = carac.length;    //se supone el archivo esta bien formado
            Filas = lineas.size();      //ahora ya conocemos las dimensiones del archivo en memoria

            char[][] matrizTmp = new char[Filas][Columnas]; //para uso en el prog 

            for (int i = 0; i < (Filas); i++) {
                char[] caracter1 = lineas.elementAt(i); //recupera fila
                for (int j = 0; j < (Columnas); j++) {
                    matrizTmp[i][j] = caracter1[j];
                }
            }
            matrix = (char[][]) matrizTmp.clone();

            ini = new Point(getComienzo() , 0);
            fin = new Point(getSalida() , Columnas-1);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(matriz.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(matriz.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private  int getComienzo() {

        for (int i = 0; i < Filas; i++) {
            if (matrix[i][0] == '1') {
                return i;
            }
        }
        return -1;
    }

    private int getSalida() {
        for (int i = 0; i < Filas; i++) {
            if (matrix[i][Columnas-1] == '1') {
                return i;
            }

        }
        return 0;
    }

}
