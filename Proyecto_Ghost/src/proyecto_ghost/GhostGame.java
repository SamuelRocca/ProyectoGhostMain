
package proyecto_ghost;

import java.util.Random;

public class GhostGame {
    Random r = new Random();
    public static String casillas[][]= new String[7][7];
    private static int tablero[][] = {{1,2,3,4,5,6,7},{1,0,-1,-2,-3,-4,0},{2,0,-5,-6,-7,-8,0},{3,0,0,0,0,0,0},{4,0,0,0,0,0,0},{5,0,5,6,7,8,0},{6,0,1,2,3,4,0}};
    /*
        arrgelo tablero se utiliza para validar la posicion en la que deben ir los fantasmas y los guiones en las casillas, en los numeros negativos
        iran los fantasmas del jugador 2 en los positivos ira el jugador 1 y los primeros numeros positivos de la fila son para mostrar el numero 
        de la coordenada los 0 son para los guiones
    */
    public static Player players[];
    private Player loggedUser;
    public Fantasmas fantasmas1[];
    public Fantasmas fantasmas2[];
    private String tipos[] = {"Malo","Bueno"};
    private static int fantasmasComidos1 = 0;
    private static int fantasmasComidos2 = 0;

    public GhostGame()
    {
        this.players = new Player[10];
    }
    
    public Player buscar(String nick)
    {
        for (Player jugadorF : players) 
        {
            if (jugadorF != null) //Valida que la posicion del arreglo no este vacia
            {
                if (jugadorF.getNick().equals(nick))
                {
                    return jugadorF; //Si el jugador existe retorna ese jugador, todos sus atributos y metodos
                }
            }
        }

        return null;
    }

    public boolean Agregar(String name, String contra)
    {
        if (buscar(name)==null) //Valida que el nickname este disponible utilizando la funcion de buscar
        {
            for (int p = 0; p <players.length;p++)
            {
                if (players[p] == null)
                {
                    players[p] = new Player(name,contra); //Si la posicion en p esta vacia, crea un nuevo player en esa posicion con ese nickname y contraseña
                    return true;
                }
            }
        }
        return false;
    }

    public boolean VerificarUsuario(String usuario, String contra)
        {
            Player jugadorM = buscar(usuario); //Busca si el usuario existe 
            
            if (jugadorM != null)
            {
                if (jugadorM.getContra().equals(contra)) // Valida que la contraseña ingresada sea igual a la contraseña del usuario
                    return true;
            }
            
            return false;
        }
 
    public Player getUserLogged(){
        return loggedUser;
    }
    
    public void setUserLogged(Player usuario){
        loggedUser = usuario;
    }
    
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
        int malo = 0;//Cuanto cuantos fantasmas de cada tipo se han puesto para no pasar de la mitad
        int bueno = 0;//
        fantasmas1 = new Fantasmas[cantidad];//Le da al arreglo la cantidad ingresada en el parametro que es depende de la dificultad
        fantasmas2 = new Fantasmas[cantidad];
        
        for (int i = 0; i < fantasmas1.length;i++)
        {
            int num = r.nextInt(2);//crea un numero aleatorio
            String tipo = tipos[num];
            if (num == 0)//Si el numero aleatorio es 0 entonces el fantasma sera malo
            {
                malo++;
                if(malo > cantidad/2)//Si la cantidad de fantasmas sobrepasa la mitad se cambia el tipo de fantasma para conseguir la misma cantidad de fantasmas
                {
                    tipo = "Bueno";
                    bueno++;
                }
            }
            else if(num == 1)
            {
                bueno++;
                if(bueno > cantidad/2)
                {
                    tipo = "Malo";
                    malo++;
                }
            }
            
            fantasmas1[i] = new Fantasmas(tipo, 1); //Se crea en esa posicion un nuevo fantasma de ese tipo, el numero 1 es por el turno del jugador
        }
        malo = 0;//Se reincian las variables para contar los fantasmas del jugador 2
        bueno = 0;
        for(int i = 0; i < fantasmas2.length;i++)
        {
            int num = r.nextInt(2);
            String tipo = tipos[num];
            if (num == 0)
            {      
                malo++;
                if(malo > cantidad/2)
                {
                    tipo = "Bueno";
                    bueno++;
                }
            }
            else if(num == 1)
            {
                bueno++;
                if(bueno>cantidad/2)
                {
                    tipo = "Malo";
                    malo++;
                }
            }
            fantasmas2[i] = new Fantasmas(tipo, 2);
        }
        
        
    }
    public void Normal()//Se crea el tablero para la dificultad normal
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
    
    public void Expert()//Se crea el tablero para la dificultad expert
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
    
    public void Genius()//Se crea el tablero para la dificultad genius
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
    
    public void tablero()//Valida cuanto es el tamaño del arreglo para saber que tablero imprimir
    {
        if (fantasmas1.length == 8)
            Normal();
        else if (fantasmas1.length == 4)
            Expert();
        else if (fantasmas1.length == 2)
            Genius();
    }
    
    public void MostrarFantasmas(int turno) //Este metodo sirve para mostrar la cantidad de fantasmas que tiene el jugador en turno
    {
        int bueno = 0;
        int malo = 0;
        if(turno==0)
        {
            for (Fantasmas ghost1 : fantasmas1)
            {
                if(ghost1 != null)
                {
                    if(ghost1.getTipo().equals("Bueno"))
                        bueno++;
                    else
                        malo++;
                }
            }
        }else
        {
            for (Fantasmas ghost2 : fantasmas2)
            {
                if(ghost2 != null)
                {
                    if(ghost2.getTipo().equals("Bueno"))
                        bueno++;
                    else
                        malo++;
                }
            }
        }
        
        System.out.println("\t\tBuenos: " + bueno + "\t\tMalos: " + malo);
    }
    
    public boolean Comer(int turno,int a,int b,int x,int y)
    {
        try{
            if(ValidarMovimiento(x,y,a,b,turno))
            {
                if (turno == 0)//Valida de quien es el turno para saber que pieza tiene que comer
                {
                    if (casillas[a][b].equals("f2"))
                    {
                        casillas[a][b] = "f1";//Si puede comer, cambia esa posicion por el fantasma del jugador y retorna true
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
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return false;
    }
    
    public boolean ValidarMovimiento(int x, int y,int a,int b, int turno)
    {
        try{
            if(b >1 && b <6)
            {
                    if(casillas[x][y].equals("f1") && turno==0)
                    {
                        if((a==x-1 || a ==x) && (b==y+1 || b==y-1 || b==y))
                            return true;
                    }else if(casillas[x][y].equals("f2") && turno==1)
                    {
                        if((a==x+1 || a==x) && (b==y+1 || b==y-1 || b==y))
                            return true;
                    }
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {
            return false;
        }
        return false;
    }
    
    public boolean Mover(int x,int y,int a, int b,int turno,Player jugador)//"x" y "y" son la coordenada de seleccion y "a" y "b" la coordenada de movimiento
    {                                                                                                       //el jugador es el contrario al del turno actual, es usado para imprimir el tipo de fantasma comido
        if(ValidarMovimiento(x,y,a,b,turno) && casillas[a][b].equals("_ "))
        {
            casillas[a][b] = casillas[x][y]; 
            casillas[x][y] = "_ ";
            return true;
        }else if(Comer(turno,a,b,x,y))
        {
            try{
                casillas[x][y] = "_ ";
            }catch(ArrayIndexOutOfBoundsException e)
            {
                return false;
            }
            System.out.println("\nTe has comido un fantasma " + (turno==0 ? fantasmas2[fantasmasComidos2].getTipo() : fantasmas1[fantasmasComidos1].getTipo()) + 
                                                                                                                                                                                                                                            " de " + jugador.getNick());
            if(turno == 0)
            {
                fantasmas2[fantasmasComidos2] = null;
                fantasmasComidos2++;
            }
            else
            {
                fantasmas1[fantasmasComidos1] = null;
                fantasmasComidos1++;
            }
            return true;
        }else
            System.out.println("\t\t\t\tMovimiento no valido!!");
            return false;
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
    
    
    public boolean ValidarVictoria(int turno)
    {
        int buenos=0;
        int malos=0;
        if (turno == 0)
        {
            for(Fantasmas ghost : fantasmas2)
            {
                if (ghost != null)
                {
                    if(ghost.getTipo().equals("Bueno"))
                        buenos++;
                    else
                        malos++;
                }
            }          
        }else
        {
            for(Fantasmas ghost : fantasmas1)
            {
                if (ghost!=null)
                {
                    if(ghost.getTipo().equals("Bueno"))
                        buenos++;
                    else
                        malos++;               
                }
            }
            
            if(buenos == 0)
            {
                System.out.println("Te has comido todos los fantasmas buenos has ganado!");
                return true;
            }
            else if(malos==0)
            {
                System.out.println("Te has comido todos los fantasmas malos has perdido!");
                return true;
            }
        }
        return false;
    }
    
    public boolean contarPlayers(){
        int contar = 0;
        for(Player count : players){
           if(count != null){
               contar++;
           }
       }       return contar>1;
    }
}