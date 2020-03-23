
package proyecto_ghost;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static GhostGame game = new GhostGame();
    public static Scanner leer = new Scanner (System.in).useDelimiter("\n");
    public static void main(String[] args) {
            //Constructores
            
            Random r = new Random();
            //Variables
            boolean loop = true, victoria = false;
            int fantasmas = 8, turno = 0;
            int MenuConfig = 0;
            int opcionMenu = 0;
            int filas = 0,filas1 = 0,columnas = 0,columnas1=0;
            Player logged = null, jugador1 = null,jugador2=null;
            String usuario = null, contrasenia;
            
            do{
                    do{
                        try{
                            System.out.print("\t\t\t\tGhosts\n1. Login\t2. Crear Player\t\t3. Salir\nIngrese una opcion: ");
                            opcionMenu = leer.nextInt();
                            break;
                        }catch(InputMismatchException ex){
                            leer.next();
                            System.out.println("\nIngreso un caracter invalido. Intente otra vez");
                        }
                    }while(true);
                    switch (opcionMenu)
                    {
                        case 1: //Menu de loggin
                                System.out.print("\nNombre de usuario: ");
                                jugador1 = game.buscar(leer.next());
                                System.out.print("Contraseña: ");
                                contrasenia = leer.next();
                                
                                if(jugador1 != null && game.VerificarUsuario(jugador1.getNick(), contrasenia))
                                {
                                    game.setUserLogged(jugador1); //Se usa para establecer en la clase GhostGame el usuario que esta logged in
                                }else
                                {
                                    System.out.print("Contraseña o usuario incorrectos!\n");
                                }              
                                
                                contrasenia = null;
                            break;
                            
                        case 2: //Menu para crear un nuevo player
                                System.out.print("Ingrese un nombre de usuario: ");
                                usuario = leer.next();
                                System.out.print("Ingrese una contraseña: ");
                                contrasenia = leer.next();
                                
                                if (game.Agregar(usuario, contrasenia))
                                {
                                    System.out.println("Usuario Registrado con exito");
                                    jugador1 = game.buscar(usuario);
                                    game.setUserLogged(jugador1);
                                }else
                                {
                                    System.out.println("Nombre de usuario ya esta registrado!");
                                    break;
                                }
                                
                                contrasenia = null;
                            break;
                        case 3:
                            System.out.println("Saliendo del sistema...");
                            break;
                    }//Fin del switch opciones menu inicio
                
                    while(game.getUserLogged() != null){//Se valida que haya un usario logged in para cargar el menu principal
                        
                        //Menu Principal
                        System.out.println("\nBienvenido " + jugador1.getNick() + "!");
                        System.out.print("1. Jugar\t2. Configuracion\t3. Reportes\t4. Mi perfil\t5. Salir\nQue desea hacer?: ");
                        int opcionMenu2 = leer.nextInt();
                        
                        switch (opcionMenu2)
                        {
                            case 1:
                                if(game.contarPlayers()== false){
                                    System.out.println("No hay suficientes jugadores registrados. Ingrese un nuevo Jugador en Crear Player");
                                    break;
                                }
                                do{
                                System.out.print("Nombre del jugador 2: ");
                                jugador2 = game.buscar(leer.next());
                                if (jugador2 != null && jugador2 != jugador1)
                                {
                                    game.AsignarFantasmas(fantasmas);
                                    game.tablero();
                                    turno = 0;
                                    do{
                                            game.ImprimirTablero();
                                            System.out.println("\n\t\t\tTurno  de: " + (turno==0 ? jugador1.getNick() : jugador2.getNick()));
                                            game.MostrarFantasmas(turno);
                                            do
                                            {
                                                try
                                                {
                                                    System.out.print("Escoge una coordenada de seleccion en filas: ");
                                                    filas = leer.nextInt();
                                                    System.out.print("Escoge una coordenada de seleccion en columnas: ");
                                                    columnas = leer.nextInt();
                                                    System.out.print("Ingrese coordenada de movimiento en filas: ");
                                                    filas1= leer.nextInt();
                                                    System.out.print("Ingrese coordenada de movimiento en columnas: ");
                                                    columnas1 = leer.nextInt();
                                                    loop = true;
                                                }catch(InputMismatchException e)
                                                {
                                                    leer.next();
                                                    System.out.println("\t\t\t\tCoordenada incorrecta vuelva a intentar");
                                                    loop = false;
                                                }
                                                
                                                if(!game.Mover(filas, columnas, filas1, columnas1,turno,turno==0?jugador2:jugador1)&& filas!=-1 && columnas!=-1)
                                                {
                                                    System.out.println("\t\tCoordenada incorrecta porfavor vuelve a intentar!");
                                                    loop = false;
                                                }
                                            }while(!loop);
                                            
                                            victoria = game.ValidarVictoria(turno,filas,columnas,turno==0?jugador1:jugador2,turno==0?jugador2:jugador1);
                                            
                                            if (turno > 0)//Cambia entre 0 y 1 para validar de quien es el turno
                                                turno--;
                                            else
                                                turno++;                                        
                                    }while (!victoria);
                                }else
                                {
                                    System.out.println("Vuelve a intentarlo!");
                                }
                                
                                loop = false;
                                
                                }while(loop);
                                break;
                            case 2:
                                do{
                                    System.out.print("\n1. Dificultad\t2. Modo de Juego\t3. Regresar\nEscoga configuracion: ");
                                    MenuConfig= leer.nextInt();

                                    switch(MenuConfig)
                                    {
                                        case 1:
                                                System.out.print("\n1. NORMAL\t2. EXPERT\t3. GENIUS\nSeleccione modo de juego: ");
                                                int modoJuego = leer.nextInt();

                                                if (modoJuego == 1)
                                                    fantasmas = 8;
                                                if (modoJuego == 2)
                                                    fantasmas = 4;
                                                if(modoJuego == 3)
                                                    fantasmas = 2;

                                            break;
                                        case 2:
                                            break;
                                        case 3:
                                                loop = false;
                                            break;
                                        
                                    }
                                }while(loop);
                                break;
                            case 3:
                                Reportes:do{
                                System.out.print("\n1. Descripción De mis Últimos 10 Juegos\t2. Ranking de Jugadores\t3. Regresar\nEscoga una opcion: ");
                                MenuConfig= leer.nextInt();
                                    switch(MenuConfig){
                                        case 1:
                                                jugador1.printResults();
                                            break;
                                        case 2:
                                                game.Ranking();
                                            break;
                                        case 3:
                                            
                                            break Reportes;
                                        default:
                                            break;
                                    }
                                }while(loop);
                            case 4: //Inicio del menu de mi perfil
                                    System.out.print("\n1. Mostrar Datos\t2. Modificar Datos\t3. Eliminar Cuenta\t4. Regresar\nEscoga una opcion: ");
                                    MenuConfig = leer.nextInt();
                                    
                                    switch(MenuConfig)
                                    {
                                        case 1:
                                                game.getUserLogged().MostrarDatos();
                                            break;
                                        case 2:
                                                System.out.print("\n1. Nickname\t2. Contraseña\nQue desea modificar: ");
                                                int opcion = leer.nextInt();
                                                
                                                switch(opcion)
                                                {
                                                    case 1:
                                                            System.out.print("\nQue nickname desea: ");
                                                            String nuevoNick = leer.next();
                                                            if(game.buscar(nuevoNick) == null)
                                                            {
                                                                System.out.print("\n1. Si\t2. No\nEsta seguro que desea cambiarlo: ");
                                                                game.getUserLogged().setNick(nuevoNick,leer.nextInt());
                                                            }else
                                                                System.out.println("\t\t\tEse nickname ya existe!");
                                                        break;
                                                    case 2:
                                                            System.out.print("\nQue contraseña desea: ");
                                                            String nuevaContra = leer.next();
                                                            System.out.print("\n1. Si\t2.No\nEsta seguro que desea cambiarlo: ");
                                                            game.getUserLogged().setContra(nuevaContra, leer.nextInt());
                                                        break;
                                                }//Fin del switch opcion
                                            break;
                                        case 3:
                                                System.out.print("\n1. Si\t2. No\nEsta seguro que desea eliminar la cuenta?: ");
                                                opcion = leer.nextInt();
                                                game.EliminarCuenta(jugador1, opcion);
                                                game.setUserLogged(opcion==1?null:jugador1);
                                            break;
                                            
                                    }//Fin del switch Menu de Mi Perfil
                                
                                break;
                                
                            case 5:
                                fantasmas = 8;
                                game.setUserLogged(null);
                                break;
                        }//Fin del switch menu principal
                    }
                    
                    
                    
            }while(opcionMenu != 3);
            
            
    }
}
