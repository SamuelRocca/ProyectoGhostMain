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
            int opcionMenu = 0;
            String usuario, contrasenia;
            
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
                                    //Menu Principal
                                        System.out.println("Bienvenido " + player.buscar(usuario).getNick() + "!");
                                        System.out.print("1. Jugar\t2. Configuracion\t3. Reportes\t4. Mi perfil\t5. Salir\nQue desea hacer?: ");
                                        int opcionMenu2 = leer.nextInt();
                                }else
                                {
                                    System.out.print("Contraseña o usuario incorrectos!");
                                }              
                                
                                usuario = null;
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
                                }else
                                {
                                    System.out.println("Nombre de usuario ya esta registrado!");
                                    break;
                                }
                                
                                usuario = null;
                                contrasenia = null;
                            break;
                    }
                
            }while(opcionMenu != 3);
            
            
    }
}
