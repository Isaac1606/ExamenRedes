
package examenredes;

/**
 *
 * @author isaac
 */
public class ExamenRedes {

    public static void main(String[] args) {
        int i,j;
        
        String[][] matriz2 = creaMatriz(10,10);
        
        imprimeMatriz(matriz2);
        
        System.out.println("\n");
        for(i=0;i<10;i++){
            for(j=0;j<10;j++){
                recorreMatriz(matriz2,i,j);
            }
        }
    }
    
    static void recorreMatriz(String [][] m,int fila,int columna){
        int i,j;
        for(i=-1; i<2; i++){
            for(j=-1;j<2;j++){
                try{
                if(!m[fila+i][columna+j].equals(m[fila][columna])){
                    System.out.print(m[fila+i][columna+j]+" ");
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }
        System.out.println("estan alrededor de: "+m[fila][columna]);
    }
    static String[][] creaMatriz(int filas,int columnas){
        String[][] matriz= new String[filas][columnas];
        int i,j,a;
        for(i=0;i<10;i++){
            a = (10*i)+1;
            for(j=0;j<10;j++){
                matriz[i][j] = Integer.toString(a);
                a++;
            }
        }
        return matriz;
    }
    
    static void muestraTablero(String[][] m){
        int i,j;
        for(i=0;i<m.length;i++){
            
        }
    }
    
    static void imprimeMatriz(String[][] m){
        int i,j;
        for(i=0;i<m.length;i++){
            System.out.println("");
            for(j=0;j<m[i].length;j++){
                System.out.printf("%3s ",m[i][j]);
            }
        }
    }
}