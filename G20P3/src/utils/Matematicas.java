package utils;

/**
 * Clase de utilidades matematicas.
 * 
 * @author Grupo20.
 */
public class Matematicas {

    /**
     * Calcula el logaritmo en base 2. 
     *
     * @param x Valor del que calcular dicho logaritmo.
     *
     * @return El logaritmo en base 2 de x.
     */
    public static double log2(final double x){
        
        return Math.log(x) / Math.log( 2 );
    }
}
