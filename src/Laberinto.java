import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Laberinto implements  Runnable
{
        Thread t = new Thread(this);
	final static char PARED = '*';
	final static char VISITADO = '-';
	final static char INCORRECTO = 'x';
        char m[][]=obtener_matriz();
	int FILAS=m.length;
	int COLUMNAS=m[0].length;
        int inicodellaberinto = obtener_comienzo();
	int entrada[] = {inicodellaberinto,0};

	int col_salida = m.length-1;
        int salidafilas = obtener_salida();
        int fila_salida = salidafilas;

	static char lab[][]= obtener_matriz();
        
        static long counter = 0;


   
    Personaje per;
	public Laberinto()
	{
		per = new Personaje(1, 0);
                t.start();
	}
	public void mover(int f, int c)
	{
		per.setLocation(c*50, f*50+40);
	}
	public  void run()
	{
          
		boolean b = recorrer(entrada[0],entrada[1]);
		if(b==true){
                          System.out.println("         CAMINO ENCONTRADO          ");
                          System.out.println("////////////////////////////////////");
                }else{
                        System.out.println("         CAMINO SIN SALIDA          ");
                        System.out.println("////////////////////////////////////");
                 }
}
	public boolean recorrer(int f, int c)
	{
		       
            boolean salida=false;
		lab[f][c]=VISITADO;


		if(f==fila_salida && c==col_salida)
		{
			per.setVisible(false);
		   	return true;
		}

		try
		{
			if(!salida && estaDentro(f,c-1))
			{
				mover(f, c-1);
				t.sleep(500);
		    	salida=recorrer(f,c-1);
			}
			if(!salida && estaDentro(f,c+1))
			{
				mover(f, c+1);
				t.sleep(500);
		    	salida=recorrer(f,c+1);
			}
			if(!salida && estaDentro(f-1,c))
			{
				mover(f-1, c);
				t.sleep(500);
			    salida=recorrer(f-1,c);
			}
			if(!salida && estaDentro(f+1,c))
			{
				mover(f+1, c);
				t.sleep(500);
			    salida=recorrer(f+1,c);
			}
			if(!salida)
			{
				lab[f][c]='x';
				if(lab[f][c]=='x')
				{
					mover(f, c);
					t.sleep(500);
				}

	   		}
	   	}catch(Exception e){}

		return salida;
	}
	public boolean estaDentro(int f, int c)
	{
           
		if(f<0 || f>=FILAS || c<0 || c>=COLUMNAS)
			return false;
		if(lab[f][c]==VISITADO || lab[f][c]==PARED)
			return false;
                construirLaberinto();
		return true;
	}
	public void construirLaberinto()
	{
            
		int x = 0;
		int y = 40;

		for(int i=0; i<FILAS; i++)
		{
			for(int j=0; j<COLUMNAS; j++)
			{
				if(lab[i][j]==PARED)
				{
                                                                      
					
					x+=50;
				}
				else
					x+=50;
			}
			x=0;
			y+=50;
		}
                String hh = "";
                for (int k = 0; k < FILAS; k++) {
                    for (int l = 0; l < COLUMNAS-1; l++) {
                         hh = hh+lab[k][l];
                             }
                     System.out.println(hh);
                hh="";
               }
                System.out.println("///////////////////////////////////////////");
	}
	public static  void main(String args[])
	{
               System.out.println("////////////////////////////////////////////////");
               System.out.println("           BIENVENIDO AL LABERINTO              ");
               System.out.println("////////////////////////////////////////////////");
               
               System.out.println("el archivo de texto se encuentara en el proyecto");
               System.out.println("Presione Cualquier tecla para Empezar");
               Scanner escaner = new Scanner(System.in);
               String teclado = escaner.nextLine();
               counter = System.nanoTime();
	       Laberinto lab = new Laberinto();
           
	}   
 public static char[][] obtener_matriz() {
     int x=0;
       File archivo = new File("Laberinto.txt");
        char[][] matriz =new char[1000][1000];
       // 
        FileReader lectura;
            try {
                lectura = new FileReader(archivo);
                 BufferedReader bufer = new BufferedReader(lectura);
                 String linea;int cont = 0;int fila =1;
                while((linea=bufer.readLine())!=null){
                    char[] caracteres = linea.toCharArray();
                   x=caracteres.length;
                       
                        fila =fila+1;
                       
                   
                     for (int i = 0; i < x; i++) {
                         matriz[cont][i] = caracteres[i];
                         }
                        cont = cont +1;
                     }
                //////////////////////////
                matriz =new char[cont][x];
                  lectura = new FileReader(archivo);
                 BufferedReader buferi = new BufferedReader(lectura);
                 String lineai;int conti = 0;int filai =1;
                while((lineai=buferi.readLine())!=null){
                    char[] caracteres = lineai.toCharArray();
                   x=caracteres.length;
                       
                        filai =filai+1;
                       
                   
                     for (int i = 0; i < x; i++) {
                         matriz[conti][i] = caracteres[i];
                         }
                        conti = conti +1;
                     }
   /////////////////////////////////////
        for (int i = 0; i < cont; i++) {
            for (int j = 0; j < (x-1); j++) {
                if ( matriz[i][j] == '0'  ) {
                    matriz[i][j] = '*';
                }
                if (matriz[i][j] == '1') {
                    matriz[i][j]= ' ';
                }
            }
        }
       return matriz;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Laberinto.class.getName()).log(Level.SEVERE, null, ex);
            }
            return null;
       
    }
 public static int obtener_comienzo(){
     char[][] matriz = obtener_matriz();
     
     for (int i = 0; i < matriz.length; i++) {
         if (matriz[i][0] == ' ') {
             return i;             
         }
         
     }
     return 0;
 }
 public static int obtener_salida(){
      char[][] matriz = obtener_matriz();
     for (int i = 0; i < matriz.length; i++) {
         if (matriz[i][20] == ' ') {
             return i;             
         }
         
     }
     return 0;
 }

}