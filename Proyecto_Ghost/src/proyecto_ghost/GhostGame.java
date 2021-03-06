
package proyecto_ghost;

import java.util.Random;

public class GhostGame {
    Random r = new Random();
    Main main;
    public static String casillas[][]= new String[7][7];
    private static int tablero[][] = {{1,2,3,4,5,6,7},{1,0,-1,-2,-3,-4,0},{2,0,-5,-6,-7,-8,0},{3,0,0,0,0,0,0},{4,0,0,0,0,0,0},{5,0,5,6,7,8,0},{6,0,1,2,3,4,0}};
    /*
        arrgelo tablero se utiliza para validar la posicion en la que deben ir los fantasmas y los guiones en las casillas, en los numeros negativos
        iran los fantasmas del jugador 2 en los positivos ira el jugador 1 y los primeros numeros positivos de la fila son para mostrar el numero 
        de la coordenada los 0 son para los guiones
    */
    public static Player players[];
    public static Player Stats;
    private Player loggedUser;
    private String tipos[] = {"Malo","Bueno"};
    public Fantasmas fantasmas1[];
    public Fantasmas fantasmas2[];
    private static int fantasmasComidos1 = 0;
    private static int fantasmasComidos2 = 0;

    public GhostGame()
    {
        GhostGame.players = new Player[10];
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
    
    public void EliminarCuenta(Player jugador, int opcion)
    {
        if(opcion == 1)
        {
            for (int i = 0; i < players.length;i++)
            {
                if(players[i] != null)
                {
                    if(players[i].getNick().equals(jugador.getNick()))
                    {
                        players[i] = null;
                        System.out.println("La cuenta se elimino con exito");
                    }
                }
            }
        }
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
    
    public void Ranking()
    {
        int mayorPuntaje = 0;
        for(Player jugador : players)
        {
            if (jugador != null)
            {
                if (jugador.getPuntos() > mayorPuntaje)
                    mayorPuntaje = jugador.getPuntos();
            }
        }
        
        
        for (int i = mayorPuntaje;i>=0;i--)
        {
            for(Player jugador : players)
            {
                if(jugador != null)
                {
                    if(jugador.getPuntos()==i)
                    jugador.MostrarDatos();
                }
            } 
        }
    }
    public void AsignarFantasmasManual(int cantidad){
        int malo = 0;//Cuanto cuantos fantasmas de cada tipo se han puesto para no pasar de la mitad
        int bueno = 0;//
        fantasmas1 = new Fantasmas[cantidad];//Le da al arreglo la cantidad ingresada en el parametro que es depende de la dificultad
        fantasmas2 = new Fantasmas[cantidad];
        
        for (int i = 0; i < fantasmas1.length;i++)
        {
            int num = 0;//Si es bueno o malo
            String tipo = tipos[num];
            if (num == 0)//Si el numero aleatorio es 0 entonces el fantasma sera malo
            {
                malo++;
                if(malo > cantidad/2)//Si la cantidad de fantasmas sobrepasa la mitad se cambia el tipo de fantasma para conseguir la misma cantidad de fantasmas
                {
                    tipo = "Bueno";
                    bueno++;
                }
                System.out.print("Escoge una coordenada de seleccion en filas: ");
                int filas = Main.leer.nextInt();
                fantasmas1[filas] = new Fantasmas(tipo, 1); //Se crea en esa posicion un nuevo fantasma de ese tipo, el numero 1 es por el turno del jugador
                num++;
            }
            if(num == 1)
            {
                System.out.print("Escoge una coordenada de seleccion en filas: ");
                int filas = Main.leer.nextInt();
                
                fantasmas1[filas] = new Fantasmas(tipo, 1); //Se crea en esa posicion un nuevo fantasma de ese tipo, el numero 1 es por el turno del jugador
                bueno++;
                if(bueno > cantidad/2)
                {
                    tipo = "Malo";
                    malo++;
                }
            }
            
        }
        malo = 0;//Se reincian las variables para contar los fantasmas del jugador 2
        bueno = 0;
        for(int i = 0; i < fantasmas2.length;i++)
        {
            int num = 0;
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
        switch (fantasmas1.length) {
            case 8:
                Normal();
                break;
            case 4:
                Expert();
                break;
            case 2:
                Genius();
                break;
            default:
                break;
        }
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
    
    public boolean Rendirse(int x,int y,Player player,Player player2)
    {
        if (x == -1 || y==-1)
        {
            System.out.print("\n1. Si\t2. No\nSeguro que quieres retirarte?: ");
            int opcion = Main.leer.nextInt();
            if (opcion == 1)
            {
                String resultado = player.getNick() + " se retiro del juego";
                System.out.println(resultado);
                player.addResultado(resultado);
                player2.AgregarPuntos();
                return true;
            }
            
        }
        return false;
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
                        if((a==x-1 && b==y) || ((a==x) && (b==y+1 || b==y-1)))
                            return true;
                    }else if(casillas[x][y].equals("f2") && turno==1)
                    {
                        if((a==x+1 && b==y) || ((a==x) && (b==y+1 || b==y-1)))
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
    
    
    public boolean ValidarVictoria(int turno, int x,int y,Player player,Player player2)//Las variables x y y son la coordenada de seleccion usada para validar si el usuario quiere rendirse
    {                                                                                                           //player es el jugador que esta en el turno actual y player 2 es el contrario
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
          
            for(int c = 0; c<7;c++)
            {
                if(casillas[1][c].equals("f1") && fantasmas1[fantasmasComidos1].equals("Bueno"))
                {
                    String resultado = "Llevaste un fantasma Bueno a la salida!!";
                    System.out.println("\t\t" + resultado);
                    player.addResultado(resultado);
                    player.AgregarPuntos();
                    return true;
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
            for(int c = 0; c<7;c++)
            {
                if(casillas[6][c].equals("f1") && fantasmas2[fantasmasComidos2].equals("Bueno"))
                {
                    String resultado = "Llevaste un fantasma Bueno a la salida!!";
                    System.out.println("\t\t" + resultado);
                    player.addResultado(resultado);
                    player2.addResultado(resultado);
                    player.AgregarPuntos();
                    return true;
                }
            }
        }
        
        if(buenos == 0)
            {
                String resultado = player.getNick() + " se comio todos los fantasmas buenos de "+ player2.getNick() +" y ha ganado!";
                System.out.println("\t\t" + resultado);
                player.addResultado(resultado);
                player2.addResultado(resultado);
                player.AgregarPuntos();
                return true;
            }
            else if(malos==0)
            {
                String resultado = player.getNick() + "se comio todos los fantasmas malos de " + player2.getNick() + " y ha perdido!";
                System.out.println("\t\t" + resultado);
                player.addResultado(resultado);
                player2.addResultado(resultado);
                player2.AgregarPuntos();
                return true;
            }
        
        return Rendirse(x,y,player,player2);
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