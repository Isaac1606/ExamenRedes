/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenredes;

/**
 *
 * @author isaac
 */
public class ExamenRedes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[][] matriz= new int[10][10];
        int i,j,a;
        for(i=0;i<10;i++){
            a = (10*i)+1;
            System.out.println("");
            for(j=0;j<10;j++){
                matriz[i][j] = a;
                a++;
                System.out.printf("%3d ",matriz[i][j]);
            }
        }
        System.out.println("\n");
        for(i=0;i<10;i++){
            for(j=0;j<10;j++)
                recorreMatriz(matriz,i,j);
        }
    }
    
    static void recorreMatriz(int [][] m,int fila,int columna){
        int i,j;
        for(i=-1; i<2; i++){
            for(j=-1;j<2;j++){
                try{
                if(m[fila+i][columna+j]!=m[fila][columna]){
                    System.out.print(m[fila+i][columna+j]+" ");
                }
                }catch(ArrayIndexOutOfBoundsException e){
                    
                }
            }
        }
        System.out.println("estan alrededor de: "+m[fila][columna]);
    }
}