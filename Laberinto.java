import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Laberinto implements  Runnable
{
         Thread t = new Thread(this);
	final static char PARED = '*';
	final static char VISITADO = '-';
	final static char INCORRECTO = 'x';

	int FILAS=10;
	int COLUMNAS=21;
        int inicodellaberinto = obtener_comienzo();
	int entrada[] = {inicodellaberinto,0};

	int col_salida = 20;
        int salidafilas = obtener_salida();
        int fila_salida = salidafilas;

	static char lab[][]= obtener_matriz();
        
        private int Counter = 0;


   
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
                System.out.println("////////////////////////////////////\n         CAMINO ENCONTRADO          \n////////////////////////////////////");
            }else{
                System.out.println("////////////////////////////////////\n         CAMINO SIN SALIDA          \n////////////////////////////////////");
            }
}

	public boolean recorrer(int f, int c)
	{
            System.out.println("Move " + Counter);
            Counter++;
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
		    	salida=recorrer(f,c-1);
			}
			if(!salida && estaDentro(f,c+1))
			{
				mover(f, c+1);
		    	salida=recorrer(f,c+1);
			}
			if(!salida && estaDentro(f-1,c))
			{
				mover(f-1, c);
			    salida=recorrer(f-1,c);
			}
			if(!salida && estaDentro(f+1,c))
			{
				mover(f+1, c);
			    salida=recorrer(f+1,c);
			}
			if(!salida)
			{
				lab[f][c]='x';
				if(lab[f][c]=='x')
				{
					mover(f, c);
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
                        x+=50;
                    else
			x+=50;
		}
                x=0;
                y+=50;
            }
	}


	public static  void main(String args[])
	{
               System.out.println("////////////////////////////////////////////////");
               System.out.println("           BIENVENIDO AL LABERINTO              ");
               System.out.println(" UNIVERSIDAD DE CUENCA    ");
               System.out.println(" FACULTAD DE INGENIERIA    ");
               System.out.println(" INTEGRANTS:    ");
               System.out.println("            SANTIAGO ALULEMA    ");
               System.out.println("            TATIANA SANGUÑA    ");
               System.out.println("            RAUL SUQUINAGUA    ");
               System.out.println(" INGENIERO:    ");
               System.out.println("         GABRIEL BARROS    ");
               System.out.println("////////////////////////////////////////////////\n");
	       Laberinto lab = new Laberinto();
           
	}
        
        
        
        
        
        
 public static char[][] obtener_matriz() {
       File archivo = new File("src\\Laberinto.txt");
        char[][] matriz =new char[10][21];
        FileReader lectura;
            try {
                lectura = new FileReader(archivo);
                 BufferedReader bufer = new BufferedReader(lectura);
                 String linea;int cont = 0;int fila =1;
                while((linea=bufer.readLine())!=null){
                    char[] caracteres = linea.toCharArray();
                   
                       
                        fila =fila+1;
                       
                   
                     for (int i = 0; i < 21; i++) {
                         matriz[cont][i] = caracteres[i];
                         }
                        cont = cont +1;
                     }
   
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
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
     for (int i = 0; i < 10; i++) {
         if (matriz[i][0] == ' ') {
             return i;             
         }
         
     }
     return 0;
 }
 
 public static int obtener_salida(){
      char[][] matriz = obtener_matriz();
     for (int i = 0; i < 10; i++) {
         if (matriz[i][20] == ' ') {
             return i;             
         }
         
     }
     return 0;
 }

}