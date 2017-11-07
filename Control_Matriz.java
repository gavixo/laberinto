/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Familia
 */
public class Control_Matriz {
    
    public static void control_matriz(char[] array) throws Errores{
        if (array.length != 21) {
            throw new Errores("el numero de columnas es menor o mayor a 21");
        }
        
        
    }
    
    public static void Control_Filas(int comprobador) throws Errores{
        if (comprobador != 11) {
            throw new Errores("El numero de filas es Menor o mayor a 11");
            
        }
        
    }
    
    
}
