
package proyecto_ghost;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    
    public static AgregarPlayer player = new AgregarPlayer(10);
    public static Tablero tablero = new Tablero();
    public static void main(String[] args) {
            //Constructores
            Scanner leer = new Scanner (System.in).useDelimiter("\n");
            Random r = new Random();
            //Variables
            boolean loop = true, victoria = false;
            int fantasmas = 8, turno = 0;
            int MenuConfig = 0;
            int opcionMenu = 0;
            int filas = 0,filas1 = 0,columnas = 0,columnas1=0;
            boolean logged = false;
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
                        case 1:
                                System.out.print("\nNombre de usuario: ");
                                usuario = leer.next();
                                System.out.print("Contraseña: ");
                                contrasenia = leer.next();
                                
                                if(player.VerificarUsuario(usuario, contrasenia))
                                {
                                    logged = true;
                                }else
                                {
                                    System.out.print("Contraseña o usuario incorrectos!");
                                }              
                                
                                contrasenia = null;
                            break;
                            
                        case 2:
                                System.out.print("Ingrese un nombre de usuario: ");
                                usuario = leer.next();
                                System.out.print("Ingrese una contraseña: ");
                                contrasenia = leer.next();
                                
                                if (player.Agregar(usuario, contrasenia))
                                {
                                    System.out.println("Usuario Registrado con exito");
                                    logged = true;
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
                
                    while(logged){
                        
                        //Menu Principal
                        System.out.println("\nBienvenido " + player.buscar(usuario).getNick() + "!");
                        System.out.print("1. Jugar\t2. Configuracion\t3. Reportes\t4. Mi perfil\t5. Salir\nQue desea hacer?: ");
                        int opcionMenu2 = leer.nextInt();
                        
                        switch (opcionMenu2)
                        {
                            case 1:
                                do{
                                System.out.print("Nombre del jugador 2: ");
                                Player jugador2 = player.buscar(leer.next());
                                Player jugador1 = player.buscar(usuario);
                                if (jugador2 != null && jugador2 != jugador1)
                                {
                                    tablero.AsignarFantasmas(fantasmas);
                                    tablero.tablero();
                                    do{
                                            tablero.ImprimirTablero();
                                            System.out.println("\n\t\t\tTurno  de: " + (turno==0 ? jugador1.getNick() : jugador2.getNick()));
                                            tablero.MostrarFantasmas(turno);
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
                                                
                                                if(!tablero.Mover(filas, columnas, filas1, columnas1,turno,turno==0?jugador2:jugador1))
                                                {
                                                    System.out.println("\t\tCoordenada incorrecta porfavor vuelve a intentar!");
                                                    loop = false;
                                                }
                                            }while(!loop);
                                            
                                            
                                            
                                            if (turno > 0)//Cambia entre 0 y 1 para validar de quien es el turno
                                                turno--;
                                            else
                                                turno++;                                        
                                    }while (!victoria);
                                }else
                                {
                                    System.out.println("Vuelve a intentarlo!");
                                }
                                
                                
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
                                            
                                            break;
                                        case 2:
                                            
                                            break;
                                        case 3:
                                            
                                            break Reportes;
                                        default:
                                            break;
                                    }
                                }while(loop);
                            case 4:
                                
                                break;
                                
                            case 5:
                                fantasmas = 8;
                                logged = false;
                                break;
                        }//Fin del switch menu principal
                    }
                    
                    
                    
            }while(opcionMenu != 3);
            
            
    }
}
