
package proyecto_ghost;

public class Tablero {
    public void ImprimirTablero(){
    String casillas[][]= new String[7][7];
        for(int f=1; f<7; f++){
            for(int c=1; c<7; c++){
                casillas[f][c]="- ";
                casillas[0][0]=" ";
                casillas[0][c]=" "+c;
            }
            casillas[f][0]=(f+ " "); 
            System.out.print("\n");
        }
        
        for(int f=0; f<7; f++){
            for(int c=0; c<7; c++){
                if(casillas[f][c]!=null)
                System.out.print(casillas[f][c]);
            }
            System.out.println("");
        }
        
    }
}   
