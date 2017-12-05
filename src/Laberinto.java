
import util.matriz;

public class Laberinto {

    matriz mat;

    final static char PARED = '0';
    final static char VISITADO = '3';
    final static char INCORRECTO = 'x';
    /**
     * para medir el tiempo
     */
    static long counter = 0;

    //boolean encontroSalida = false;   //para parar la recursividad

    Personaje per;  //este deberia ser un hilo

    /**
     * Constructor. La clase laberinto debe cargar el mapa y tener las
     * estructuras de control necesarias para poder ejecutar el programa. Lista
     * de posiciones recorridas mapa del laberinto, etc.
     */
    public Laberinto(String direccion) {
        //Cargar el mapa y datos sobre el mapa

        mat = new matriz(direccion);
        System.out.println(mat.Filas + " c " + mat.Columnas + "d " + mat.matrix);

        per = new Personaje(1, 0);
        //t.start();
    }

    private void correr() {  // antes era run
        boolean b = recorrer(mat.ini.x, mat.ini.y);
        if (b == true) {
            System.out.println("         CAMINO ENCONTRADO          \n////////////////////////////////////");
        } else {
            System.out.println("         CAMINO SIN SALIDA          \n////////////////////////////////////");
        }
    }

    public void mover(int f, int c) {
        per.setLocation(c * 50, f * 50 + 40);
    }

    public int examinar(int f, int c) {
        int tmp = 0;
        System.out.println("pos" + f + " " + c);
        mat.matrix[f][c] = VISITADO;
        if (estaDentroyFactible(f, c - 1)) { //Izquierda
            System.out.println("iz");
            tmp += 8;
            //salida = recorrer(f, c - 1);
        }
        if (estaDentroyFactible(f, c + 1)) { //Derecha
            System.out.println("Der");
            tmp += 4;
            //salida = recorrer(f, c + 1);
        }
        if (estaDentroyFactible(f - 1, c)) { //Arriba
            System.out.println("Up");
            tmp += 2;//salida = recorrer(f - 1, c);
        }
        if (estaDentroyFactible(f + 1, c)) { //Abajo
            System.out.println("Dw");
            tmp += 1;//salida = recorrer(f + 1, c);
        }
        /*if (!salida) {
            mat.matrix[f][c] = 'x';
            if (mat.matrix[f][c] == 'x') {
                mover(f, c);
                //t.sleep(500);
            }
        }*/
        System.out.println(tmp);
        return tmp;
    }

    public boolean recorrer(int f, int c) {
        //if (encontroSalida == false) {
            boolean salida = false;
            mat.matrix[f][c] = VISITADO; //pasado a examin

            if (f == mat.fin.x && c == mat.fin.y) { //llegó al final del lab
                per.setVisible(false);
                return true;
            }
            try {
                if (!salida && estaDentroyFactible(f, c - 1)) { //Izquierda
                    mover(f, c - 1);
                    //t.sleep(500);
                    salida = recorrer(f, c - 1);
                }
                if (!salida && estaDentroyFactible(f, c + 1)) { //Derecha
                    mover(f, c + 1);
                    //t.sleep(500);
                    salida = recorrer(f, c + 1);
                }
                if (!salida && estaDentroyFactible(f - 1, c)) { //Arriba
                    mover(f - 1, c);
                    //t.sleep(500);
                    salida = recorrer(f - 1, c);
                }
                if (!salida && estaDentroyFactible(f + 1, c)) { //Abajo
                    mover(f + 1, c);
                    //t.sleep(500);
                    salida = recorrer(f + 1, c);
                }
                if (!salida) {
                    mat.matrix[f][c] = 'x';
                    if (mat.matrix[f][c] == 'x') {
                        mover(f, c);
                        //t.sleep(500);
                    }

                }
            } catch (Exception e) {
            }
            return salida;
        //} else {
        //    return true;
        //}
    }

    public boolean estaDentroyFactible(int f, int c) {
        if (f < 0 || f >= mat.Filas || c < 0 || c >= mat.Columnas) { //fuera de la caja
            return false;
        }
        if (mat.matrix[f][c] == VISITADO || mat.matrix[f][c] == PARED) {
            return false;
        }
        construirLaberinto();
        return true;
    }

    public void construirLaberinto() {

        int x = 0;
        int y = 40;

        for (int i = 0; i < mat.Filas; i++) {
            for (int j = 0; j < mat.Columnas; j++) {
                if (mat.matrix[i][j] == PARED) {

                    x += 50;
                } else {
                    x += 50;
                }
            }
            x = 0;
            y += 50;
        }
        String hh = "";
        for (int k = 0; k < mat.Filas; k++) {
            for (int l = 0; l < mat.Columnas - 1; l++) {
                hh = hh + mat.matrix[k][l];
            }
            System.out.println(hh);
            hh = "";
        }
        System.out.println("///////////////////////////////////////////");
    }

    public static void main(String args[]) {
        System.out.println("           BIENVENIDO AL LABERINTO              ");

        System.out.println("el archivo de texto se encuentara en el proyecto");
        System.out.println("Presione Cualquier tecla para Empezar");
        //Scanner escaner = new Scanner(System.in);
        //String teclado = escaner.nextLine();
        String adireccion = "/home/gavixo/NetBeansProjects/laberinto/src/Laberinto.txt";
        Laberinto lab = new Laberinto(adireccion);  //variables y estructuras

        counter = System.nanoTime();  //inicio de cronometro
        lab.correr();
        System.out.println("\nTiempo requerido (en nano-segundos): " + (System.nanoTime() - counter)); //final de cronómetro

    }

}
