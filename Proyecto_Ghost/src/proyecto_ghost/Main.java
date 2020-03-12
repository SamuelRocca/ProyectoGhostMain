/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_ghost;

import java.util.Scanner;

/**
 *
 * @author samue
 */
public class Main {
    
    public static AgregarPlayer player = new AgregarPlayer(10);
    
    public static void main(String[] args) {
            //Constructores
            Scanner leer = new Scanner (System.in).useDelimiter("\n");
            
            //Variables
            int fantasmas = 0;
            int opcionMenu = 0;
            boolean logged = false;
            String usuario = null, contrasenia;
            
            do{
                
                    System.out.print("\t\t\t\tGhosts\n1. Login\t2. Crear Player\t\t3. Salir\nIngrese una opcion: ");
                    opcionMenu = leer.nextInt();
                    
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
                
                    if (logged == true)
                    {
                        //Menu Principal
                        System.out.println("Bienvenido " + player.buscar(usuario).getNick() + "!");
                        System.out.print("1. Jugar\t2. Configuracion\t3. Reportes\t4. Mi perfil\t5. Salir\nQue desea hacer?: ");
                        int opcionMenu2 = leer.nextInt();
                        
                        switch (opcionMenu2)
                        {
                            case 1:
                                //Aqui va la logica del juego
                                break;
                            case 2:
                                System.out.print("1. Dificultad\t2. Modo de Juego\t3. Regresar\nEscoga configuracion: ");
                                int MenuConfig= leer.nextInt();
                                
                                switch(MenuConfig)
                                {
                                    case 1:
                                            System.out.print("1. NORMAL\t2. EXPERT\t3. GENIUS");
                                            int modoJuego = leer.nextInt();
                                            
                                            if (modoJuego == 1)
                                                fantasmas = 8;
                                            if (modoJuego == 2)
                                                fantasmas = 4;
                                            if(modoJuego == 3)
                                                fantasmas = 2;
                                            
                                            
                                        break;
                                }
                                    
                                
                                
                                break;
                        }//Fin del switch menu principal
                    }
                    
                    
                    
            }while(opcionMenu != 3);
            
            
    }
}
