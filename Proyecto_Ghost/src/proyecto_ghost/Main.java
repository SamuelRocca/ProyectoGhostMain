/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ghost;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author samue
 */
public class Main {
    
    public static AgregarPlayer player = new AgregarPlayer(10);
    public static Tablero tablero = new Tablero();
    public static void main(String[] args) {
            //Constructores
            Scanner leer = new Scanner (System.in).useDelimiter("\n");
            Random r = new Random();
            //Variables
            boolean loop = true;
            int fantasmas = 0;
            int MenuConfig = 0;
            int opcionMenu = 0;
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
                                System.out.println("Nombre del jugador 2:");
                                String usuario2 = leer.next();
                                
                                if (player.buscar(usuario2) != null)
                                {
                                    int tipo = r.nextInt(2);
                                    tablero.CrearTablero();
                                    tablero.DibujarFantasmas(4);
                                }else
                                {
                                    System.out.println("El jugador 2 no existe!");
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
                                logged = false;
                                break;
                        }//Fin del switch menu principal
                    }
                    
                    
                    
            }while(opcionMenu != 3);
            
            
    }
}
