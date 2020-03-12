
package proyecto_ghost;

public class Player {
        
    private int  puntos;
    private String nick, contra;
    private boolean logged;
    
    public Player (String nombre, String contra) //Constructo requiere nickname y contraseña
    {
        this.nick = nombre;
        this.contra = contra;
        puntos = 0;
    }
    public void AgregarPuntos ()
    {
        puntos += 3;
    }
    
    public int getPuntos()
    {
        return puntos;
    }
    
    public String getNick()
    {
        return nick;
    }
    
    public String getContra()
    {
        return contra;
    }
    
    public void Imprimir()
    {
        System.out.println("Usuario: " + nick + "\nContraseña: " + contra + "\n");
    }
    
}
