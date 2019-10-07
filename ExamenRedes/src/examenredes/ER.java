package examenredes;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author isaac
 */
public class ER {

    static char[] cbuf;
    static ArrayList<String> p = new ArrayList();
    static char[][] MatrizR;
    static char[][] MatrizA;
    static Socket cl;
    static int contCasillas = 0;
    static Scanner in;
    static int tam = 0;
    static Cronometro c;

    public static void main(String[] args) throws IOException {

        System.out.println("Introduzca dificultad de juego [1, 2, 3]:");
        in = new Scanner(System.in);
        int t = in.nextInt();
        //reconstruyeMatriz(A, 9, 9);
        if (t == 1) {
            c = new Cronometro(); //Intancio la clase
            c.start(); //Ejecuto el metodo run del Thread        
            tam = 171;
            conexionServidor(t);
            MatrizA = new char[9][9];
            creaMatriz(MatrizA);
            muestraTablero(MatrizA, 9);
            char[][] m = reconstruyeMatriz(cbuf, 9, 9);
            iniJuego(m, 71, 9);

        } else if (t == 2) {
            c = new Cronometro(); //Intancio la clase
            c.start(); //Ejecuto el metodo run del Thread     
            tam = 528;
            conexionServidor(t);
            MatrizA = new char[16][16];
            creaMatriz(MatrizA);
            muestraTablero(MatrizA, 16);
            char[][] m = reconstruyeMatriz(cbuf, 16, 16);
            iniJuego(m, 216, 16);

        } else {
            c = new Cronometro(); //Intancio la clase
            c.start(); //Ejecuto el metodo run del Thread     
            tam = 976;
            conexionServidor(t);
            MatrizA = new char[16][30];
            creaMatriz(MatrizA);
            muestraTablero(MatrizA, 30);
            char[][] m = reconstruyeMatriz(cbuf, 30, 30);
            iniJuego(m, 381, 30);
        }

    }

    static void iniJuego(char[][] m, int fin, int tam) {
        do {
            if (cAbiertas() != fin) {
                System.out.println("Casillas destapadas: " + cAbiertas());
                System.out.println("Ingrese las coordenadas: x | y ");
                int x = in.nextInt();
                int y = in.nextInt();
                System.out.println("Casilla seleccionada: " + MatrizR[x][y]);
                if (MatrizR[x][y] == '0') {
                    destapaCero(m, x, y, 0, 0);
                    //System.out.println(p);
                    modTablero(1, x, y, MatrizA, MatrizR[x][y]);
                    muestraTablero(MatrizA, tam);

                } else if (MatrizR[x][y] == 'B') {
                    modTablero(0, x, y, MatrizA, MatrizR[x][y]);
                    System.out.println("");
                    muestraTablero(MatrizA, tam);
                    System.out.println("\n\nMINA ENCONTRADA, FIN DEL JUEGO\nTIEMPO TRANSCURRIDO: ");
                    System.out.print(c.nuHora + ":" + c.nuMin + ":" + c.nuSeg);
                    break;
                } else {
                    modTablero(0, x, y, MatrizA, MatrizR[x][y]);
                    System.out.println("");
                    muestraTablero(MatrizA, tam);
                }
            } else {
                System.out.println("\n\nGANASTE!!\nTIEMPO TRANSCURRIDO:");
                System.out.print(c.nuHora + ":" + c.nuMin + ":" + c.nuSeg);
                break;
            }
        } while (true);

    }

    static int cAbiertas() {
        int cont = 0;
        for (int i = 0; i < MatrizA.length; i++) {
            for (int j = 0; j < MatrizA[i].length; j++) {
                if (MatrizA[i][j] != '*') {
                    cont++;
                }
            }
        }
        return cont;
    }

    static void conexionServidor(int t) {
        try {
            int pto = 9999;
            String host = "localhost";

            cl = new Socket(host, pto);
            System.out.println("\nConexion establecida...mostrando caja de dialogo");
            System.out.println("Esperando servidor para recibir matriz\n");
            OutputStreamWriter outw = new OutputStreamWriter(cl.getOutputStream(), "UTF8");
            InputStreamReader inw = new InputStreamReader(cl.getInputStream(), "UTF8");
            String ta = Integer.toString(t);

            outw.write(ta.toCharArray());
            outw.flush();
            //System.out.println("Tamaño de arreglo:");

            cbuf = new char[tam];
            inw.read(cbuf);

            System.out.println("Se recibio Matriz");
            System.out.println("\n\n\nCOMIENZA JUEGO...\n\n\n");
            /*for (int i = 0; i < cbuf.length; i++) {
                System.out.print(cbuf[i]);
            }*/
            //System.out.println("\nLongitud de cadena recibida: " + cbuf.length);

            outw.close();
            inw.close();
            cl.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static char[][] reconstruyeMatriz(char[] A, int dimx, int dimy) {
        int ren = 0;
        int col = 0;
        MatrizR = new char[dimy][dimx];
        for (int i = 0; i < A.length; i++) {
            if (A[i] == '/' && i > 0) {
                ren++;
                col = 0;
            }
            if (A[i] != ' ' && A[i] != '/') {
                MatrizR[ren][col] = A[i];
                col++;
            }
        }
        /*for (int x = 0; x < dimx; x++) {
            for (int y = 0; y < dimy; y++) {
                System.out.print(MatrizR[x][y] + " ");
            }
            System.out.println("");
        }*/
        return MatrizR;
    }

    static void destapaCero(char[][] m, int fila, int columna, int i, int j) {
        //System.out.println("Destapo: " + m[fila + i][columna + j] + "Coordenadas: " + (fila + i) + " " + (columna + i));
        if (m[fila + i][columna + j] == '0') {
            if (!p.contains(Integer.toString(fila + i) + "|" + Integer.toString(columna + j))) {
                for (i = -1; i < 2; i++) {
                    for (j = -1; j < 2; j++) {
                        try {
                            if (m[fila + i][columna + j] == '0' && m[fila + i][columna + j] != 'B' && !p.contains(Integer.toString(fila + i) + Integer.toString(columna + j))) {
                                p.add(Integer.toString(fila + i) + "|" + Integer.toString(columna + j));
                                destapaCero(m, fila + i, columna + j, i, j);
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                        }

                    }
                }

            }
        }
    }

    static void recorreMatriz(char[][] m, int fila, int columna) {
        int i, j;
        m[fila][columna] = '0';
        for (i = -1; i < 2; i++) {
            for (j = -1; j < 2; j++) {
                try {
                    if (!(MatrizR[fila + i][columna + j] == (MatrizR[fila][columna]))) {
                        m[fila + i][columna + j] = MatrizR[fila + i][columna + j];
                    }
                } catch (ArrayIndexOutOfBoundsException e) {

                }
            }
        }
    }

    static void modTablero(int band, int x, int y, char[][] A, char valor) {
        if (band == 1) {
            for (int z = 0; z < p.size(); z++) {
                //System.out.println("TAMAÑOOO:" + p.get(z).length());
                int x1 = 0, y1 = 0, z1 = 0, w1 = 0, totx = 0, toty = 0;
                char coor[] = p.get(z).toCharArray();
                switch (p.get(z).length()) {
                    case 3:
                        //System.out.println("ENTRA A TIPO 2 : " + p.get(z));
                        y1 = Character.getNumericValue(coor[0]);
                        x1 = Character.getNumericValue(coor[2]);
                        recorreMatriz(A, y1, x1);
                        //System.out.println("Coordenada: " + y1 + " " + x1);
                        break;
                    case 4:
                        int bandera = 0;
                        for (int i = 0; i < coor.length; i++) {
                            if (coor[i] == '|') {
                                bandera = i;
                            }
                        }
                        if (bandera == 1) {
                            y1 = Character.getNumericValue(coor[0]);
                            x1 = Character.getNumericValue(coor[2]);
                            z1 = Character.getNumericValue(coor[3]);
                            totx = (x1 * 10) + z1;
                            recorreMatriz(A, y1, totx);
                            //System.out.println("Coordenada: " + y1 + " " + totx);
                        } else {
                            y1 = Character.getNumericValue(coor[0]);
                            x1 = Character.getNumericValue(coor[1]);
                            z1 = Character.getNumericValue(coor[3]);
                            toty = (y1 * 10) + x1;
                            recorreMatriz(A, toty, z1);
                            //System.out.println("Coordenada: " + y1 + " " + totx);

                        }
                        break;

                    case 5:
                        //System.out.println("ENTRA A TIPO 4 : " + p.get(z));
                        y1 = Character.getNumericValue(coor[0]);
                        x1 = Character.getNumericValue(coor[1]);
                        z1 = Character.getNumericValue(coor[3]);
                        w1 = Character.getNumericValue(coor[4]);
                        toty = (y1 * 10) + x1;
                        totx = (z1 * 10) + w1;
                        recorreMatriz(A, toty, totx);
                        //System.out.println("Coordenada: " + toty + " " + totx);
                        break;
                    default:
                        break;
                }

            }
        } else {
            A[x][y] = valor;
        }
    }

    static char[][] creaMatriz(char[][] A) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = '*';
            }
        }
        return A;
    }

    static void muestraTablero(char[][] m, int tam) {
        int j = 0;
        int i = 0;
        int cont = 0;
        System.out.print("      ");
        for (int k = 0; k < tam; k++) {
            System.out.print(k + " ");
        }
        System.out.println("");
        for (int k = 0; k < tam*2.8; k++) {
            System.out.print("_");
        }
        System.out.println("");
        for (i = 0; i < m.length; i++) {
            for (j = 0; j < m[i].length; j++) {
                if (j == 0) {
                    if (cont > 9) {
                        System.out.print(cont + " |  ");
                        cont++;
                    } else {
                        System.out.print(cont + "  |  ");
                        cont++;
                    }

                }
                if (j >= 9) {
                    System.out.print(m[i][j] + "  ");
                } else {
                    System.out.print(m[i][j] + " ");
                }
            }
            System.out.println("");
        }
    }

}
