
package examenredes;

/**
 *
 * @author isaac
 */
public class ExamenRedes {

    public static void main(String[] args) {
        String[][] matriz= new String[10][10];
        int i,j,a;
        for(i=0;i<10;i++){
            a = (10*i)+1;
            System.out.println("");
            for(j=0;j<10;j++){
                matriz[i][j] = Integer.toString(a);
                a++;
                System.out.printf("%3s ",matriz[i][j]);
            }
        }
        System.out.println("\n");
        for(i=0;i<10;i++){
            for(j=0;j<10;j++)
                recorreMatriz(matriz,i,j);
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
    static void creaMatriz(int filas,int columnas){
        
    }
}