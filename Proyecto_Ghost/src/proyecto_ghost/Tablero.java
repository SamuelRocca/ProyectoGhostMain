
package proyecto_ghost;

import java.util.Random;

public class Tablero {
    Random r = new Random();
    public static String casillas[][]= new String[7][7];
    private static int tablero[][] = {{1,2,3,4,5,6,7},{1,0,-1,-2,-3,-4,0},{2,0,-5,-6,-7,-8,0},{3,0,0,0,0,0,0},{4,0,0,0,0,0,0},{5,0,5,6,7,8,0},{6,0,1,2,3,4,0}};
    /*
        arrgelo tablero se utiliza para validar la posicion en la que deben ir los fantasmas y los guiones en las casillas, en los numeros negativos
        iran los fantasmas del jugador 2 en los positivos ira el jugador 1 y los primeros numeros positivos de la fila son para mostrar el numero 
        de la coordenada los 0 son para los guiones
    */
    public Fantasmas fantasmas1[];
    public Fantasmas fantasmas2[];
    private static int fantasmasComidos1 = 0;
    private static int fantasmasComidos2 = 0;

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
        int malo = 0;
        int bueno = 0;
        fantasmas1 = new Fantasmas[cantidad];
        fantasmas2 = new Fantasmas[cantidad];
        
        for (int i = 0; i < fantasmas1.length;i++)
        {
            int tipo = r.nextInt(1);
            if (tipo == 0)
            {
                malo++;
                if(malo > cantidad/2)
                {
                    tipo = 1;
                    bueno++;
                }
            }
            else if(tipo == 1)
            {
                bueno++;
                if(bueno > cantidad/2)
                {
                    tipo = 0;
                    malo++;
                }
            }
            
            fantasmas1[i] = new Fantasmas(tipo, 1);
            System.out.println(tipo);
        }
        malo = 0;
        bueno = 0;
        for(int i = 0; i < fantasmas2.length;i++)
        {
            int tipo = r.nextInt(1);
            if (tipo == 0)
            {      
                if(malo > cantidad/2)
                {
                    tipo = 1;
                    bueno++;
                }else
                    malo++;
            }
            else if(tipo == 1)
            {
                if(bueno > cantidad/2)
                {
                    tipo = 0;
                    malo++;
                }else
                    bueno++;
            }
            fantasmas2[i] = new Fantasmas(tipo, 2);
        }
        
        
    }
    
    
       public void Normal()
    {
        for (int f = 0;f<tablero.length;f++)
        {
            for (int c = 0; c < tablero.length;c++)
            {
                if (tablero[f][c] == 0)
                    casillas[f][c] = "_ ";
                else if(tablero[f][c] < 0)
                    casillas[f][c] = fantasmas2[c].getNum();
                else if(tablero[f][c] > 0 && c != 0 && f != 0)
                    casillas[f][c] = fantasmas1[c].getNum();
                else if (tablero[f][c] > 0 && c != 0 && f != 0)
                    casillas[f][c] = "_ ";
                else if (c==0 && f != 0)
                    casillas[f][c] = Integer.toString(tablero[f][c]);
                else if (c == 0 && f == 0)
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
    }
    
    public void Expert()
    {
        
        int f1 = 0;//Contar cuantos fantasmas se han colocado
        int f2 = 0;
         for (int f = 0;f<tablero.length;f++)
        {
            for (int c = 0; c < tablero.length;c++)
            {
                if (tablero[f][c] == 0)
                    casillas[f][c] = "_ ";
                else if(tablero[f][c] < 0 && tablero[f][c] > -5)
                {
                    casillas[f][c] = fantasmas2[f2].getNum();
                    f2++;
                }
                else if (tablero[f][c] < 0 && tablero[f][c] <= -5)
                     casillas[f][c] = "_ ";
                else if(tablero[f][c] > 0  && c != 0 && f != 0 && tablero[f][c] < 5)
                {
                    casillas[f][c] = fantasmas1[f1].getNum();
                    f1++;
                }
                else if (tablero[f][c] > 0 && c != 0 && f != 0 && tablero[f][c] >= 5)
                    casillas[f][c] = "_ ";
                else if (tablero[f][c] > 0 && c != 0 && f != 0)
                    casillas[f][c] = "_ ";
                else if (c==0 && f != 0)
                    casillas[f][c] = Integer.toString(tablero[f][c]);
                else if (c == 0 && f == 0)
                    casillas[f][c] = " ";
                
                for (int a = 0;a<casillas.length;a++)
                {
                    for (int b = 1;b<casillas.length;b++)
                    {
                        casillas[0][b] = Integer.toString(b) + " ";
                    }
                }
                     
            }//Cierre del segundo for
        }//Cierre del primer for
    }
    
    public void Genius()
    {
        int f1 = 0;//Contar cuantos fantasmas se han colocado
        int f2 = 0;
         for (int f = 0;f<tablero.length;f++)
        {
            for (int c = 0; c < tablero.length;c++)
            {
                if (tablero[f][c] == 0)
                    casillas[f][c] = "_ ";
                else if(tablero[f][c] < 0 && (tablero[f][c] == -2 || tablero[f][c] == -3) )
                {
                    casillas[f][c] = fantasmas2[f2].getNum();
                    f2++;
                }
                else if (tablero[f][c] < 0)
                     casillas[f][c] = "_ ";
                else if(tablero[f][c] > 0  && c != 0 && f != 0 && (tablero[f][c] == 2 || tablero[f][c] == 3))
                {
                    casillas[f][c] = fantasmas1[f1].getNum();
                    f1++;
                }
                else if (tablero[f][c] > 0 && c != 0 && f != 0)
                    casillas[f][c] = "_ ";
                else if (tablero[f][c] > 0 && c != 0 && f != 0)
                    casillas[f][c] = "_ ";
                else if (c==0 && f != 0)
                    casillas[f][c] = Integer.toString(tablero[f][c]);
                else if (c == 0 && f == 0)
                    casillas[f][c] = " ";
                
                for (int a = 0;a<casillas.length;a++)
                {
                    for (int b = 1;b<casillas.length;b++)
                    {
                        casillas[0][b] = Integer.toString(b) + " ";
                    }
                }
                     
            }//Cierre del segundo for
        }//Cierre del primer for
    }
    
    public void tablero()
    {
        if (fantasmas1.length == 8)
            Normal();
        else if (fantasmas1.length == 4)
            Expert();
        else if (fantasmas1.length == 2)
            Genius();
    }
    
    public void MostrarFantasmas()
    {
        int bueno = 0;
        int malo = 0;
        for (Fantasmas ghost1 : fantasmas1)
        {
            if(ghost1.getTipo().equals("Bueno"))
                bueno++;
            else
                malo++;
        }
    }
    
    public boolean Comer(int turno,int a,int b)
    {
        if (turno == 0)
        {
            if (casillas[a][b].equals("f2"))
            {
                casillas[a][b] = "f1";
                return true;
            }
        }else
        {
            if (casillas[a][b].equals("f1"))
            {
                casillas[a][b] = "f2";
                return true;
            }
        }
        return false;
    }
    
    public void Mover(int x,int y,int a, int b,int turno,Player jugador)
    {
        if(casillas[a][b].equals("_ ") && !casillas[x][x].equals("_"))
        {
            casillas[a][b] = casillas[x][y]; 
            casillas[x][y] = "_ ";
        }else
        {
            if (Comer(turno,a,b))
            {
                casillas[x][y] = "_ ";
                System.out.println("\nTe has comido un fantasma " + (turno==0 ? fantasmas2[fantasmasComidos2].getTipo() : fantasmas1[fantasmasComidos1].getTipo()) + 
                                                                                                                                                                                                                                                " de " + jugador.getNick());
                if(turno == 0)
                    fantasmasComidos2++;
                else
                    fantasmasComidos1++;
            }
        }
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
        
         for(int f=0; f<7; f++){
            for(int c=0; c<7; c++){
                System.out.print(casillas[f][c] + " ");
            }
            System.out.println("");
        }      
            
    }
    
}   
