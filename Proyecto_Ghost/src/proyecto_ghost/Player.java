
package proyecto_ghost;

public class Player {
        
    private int  puntos;
    private String nick, contra;
    public Resultados Resultados[];
    static int maxResults = 10;
    
    public Player (String nombre, String contra) //Constructo requiere nickname y contraseña
    {
        this.nick = nombre;
        this.contra = contra;
        puntos = 0;
        Resultados = new Resultados[maxResults];
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
    
    public void MostrarDatos()
    {
        System.out.println("Usuario: " + nick + "\nContraseña: " + contra + "\n" + "\nPuntos: " + puntos);
    }
    
    public void addResultado(String resultado)
    {
        for(Resultados search : Resultados)
        {
            if(search != null)
            {
                
            }
        }
    }
}
