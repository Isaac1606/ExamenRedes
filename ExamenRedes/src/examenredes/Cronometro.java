package examenredes;

/*
Clase que hereda de la clase Thread, usada
*Para pintar un cronometro 
*Autor: LBVP
*Fecha:06-05-2011
*Licencia :GNU
 */
public class Cronometro extends Thread { //una clase que hereda de la clase Thread
    public int nuMin=0; //El Contador de minutos
    public int nuSeg=0; //El Contador de de segundos
    public int nuHora=0; //El Contador de Horas   
    public Cronometro(){// Contructor porque la clase es heredada 
        super();
    }
    public int getMin(){
        return nuMin;
    }
    public int getSeg(){
        return nuSeg;
    }
    public int getHora(){
        return nuHora;
    }
    public void run() {
    
        try {//si ocurre un error al dormir el proceso(sleep(999))
            for (; ;){ //inicio del for infinito           
               if(nuSeg!=59) {//si no es el ultimo segundo
                   nuSeg++; //incremento el numero de segundos                                  
                }else{
                    if(nuMin!=59){//si no es el ultimo minuto
                        nuSeg=0;//pongo en cero los segundos 
                        nuMin++;//incremento el numero de minutos
                    }else{//incremento el numero de horas
                            nuHora++;
                            nuMin=0;//pongo en cero los minutos
                            nuSeg=0;//pongo en cero los segundos           
                    }
                }               
            //System.out.println(nuHora+":"+nuMin+":"+nuSeg);//Muestro en pantalla el cronometro
            sleep(999);//Duermo el hilo durante 999 milisegundos(casi un segundo, quintandole el tiempo de proceso)
            }//Fin del for infinito             
        } catch (Exception ex) {
             System.out.println(ex.getMessage());//Imprima el error
        }                 
 } 
}//Fin Clase