
package proyecto_ghost;

public class Tablero {
    
    public static String casillas[][]= new String[7][7];
    private int cantidad = 8;
    
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
    }
    
    public void ImprimirTablero(){
        for(int f=0; f<7; f++){
            for(int c=0; c<7; c++){
                System.out.print(casillas[f][c] + " ");
            }
            System.out.println("");
        }      
    }
    
    public void DibujarFantasmas(int cantidad)
    {
        this.cantidad = cantidad;
        
        for (int f = 0; f < casillas.length; f++)
        {
            for (int c = 0; c < casillas.length; c++)
            {
                if (f != 0 && c != 0 && c != 1 &&  c != 5 && c!= 7)
                {
                    casillas[f][c] = "F1";
                }
            }
        }
        
        ImprimirTablero();
    }
    
    public void DibujarFantasma(Player jugador,String num, int tipo)
    {
        Fantasmas fantasma = new Fantasmas(jugador, num, tipo);
        
        for (int a = 0; a < casillas.length;a++)
        {
            for (int b = 0; b < casillas.length; b++)
            {
                if (b != 0 || b != 1 || b != 5 || b != 6)
                    casillas[a][b] = fantasma.getFantasma();
            }
        }
        
    }
    
}   
