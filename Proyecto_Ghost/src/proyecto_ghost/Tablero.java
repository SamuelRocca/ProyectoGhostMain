
package proyecto_ghost;

import java.util.Random;

public class Tablero {
    Random r = new Random();
    public static String casillas[][]= new String[7][7];
    private int tablero[][] = {{1,2,3,4,5,6,7},{1,0,-1,-2,-3,-4,0},{2,0,-5,-6,-7,-8,0},{3,0,0,0,0,0,0},{4,0,0,0,0,0,0},{5,0,5,6,7,8,0},{6,0,1,2,3,4,0}};
    /*
        arrgelo tablero se utiliza para validar la posicion en la que deben ir los fantasmas y los guiones en las casillas, en los numeros negativos
        iran los fantasmas del jugador 2 en los positivos ira el jugador 1 y los primeros numeros positivos de la fila son para mostrar el numero 
        de la coordenada los 0 son para los guiones
    */
    public Fantasmas fantasmas1[];
    public Fantasmas fantasmas2[];

       public void ImprimirTablero(){
        for(int f=0; f<7; f++){
            for(int c=0; c<7; c++){
                System.out.print(casillas[f][c] + " ");
            }
            System.out.println("");
        }      
    }
    
    
    public void AsignarFantasmas(int cantidad)
    {
        fantasmas1 = new Fantasmas[cantidad];
        fantasmas2 = new Fantasmas[cantidad];
        
        for (int i = 0; i < fantasmas1.length;i++)
        {
            int tipo = r.nextInt(2);
            fantasmas1[i] = new Fantasmas(tipo, 1);
        }
        
        for(int i = 0; i < fantasmas2.length;i++)
        {
            int tipo = r.nextInt(2);
            fantasmas2[i] = new Fantasmas(tipo, 2);
        }
        
        
    }
    
    
       public void ImprimirCasillas()
    {
        
        for (int f = 0;f<casillas.length;f++)
        {
            for (int c = 0; c < casillas.length;c++)
            {
                if (tablero[f][c] == 0)
                    casillas[f][c] = "_ ";
                if(tablero[f][c] < 0)
                    casillas[f][c] = fantasmas2[c].getNum();
                if(tablero[f][c] > 0 && c != 0 && f != 0)
                    casillas[f][c] = fantasmas1[c].getNum();
                if (c==0)
                    casillas[f][c] = Integer.toString(tablero[f][c]);
                if (c == 0 && f == 0)
                    casillas[f][c] = " ";
                
                for (int a = 0;a<casillas.length;a++)
                {
                    for (int b = 1;b<casillas.length;b++)
                    {
                        casillas[0][b] = Integer.toString(b) + " ";
                    }
                }
                     
                
            }
        }
        ImprimirTablero();
    }
    
    
    
    public void CrearTablero()
    {
            for(int f=1; f<7; f++){
            for(int c=1; c<7; c++){
                casillas[f][c]="- ";
                casillas[0][0]=" ";
                casillas[0][c]=" "+c;
            }
            casillas[f][0]=(f+ " "); 
            System.out.print("\n");
        }
            
        for (int f = 1; f <= fantasmas1.length;f++)
        {
            int casilla = 1;
            for (int c = 1; c <= fantasmas1.length;c++)
            {
                if ((f == 6 || f == 5) && (c == 2 || c == 3 || c == 4 || c  == 5))
                {
                    casillas[f][c] = Integer.toString(casilla);
                    casilla++;
                }
            }
        }
        
        ImprimirTablero();
            
    }
    
}   
