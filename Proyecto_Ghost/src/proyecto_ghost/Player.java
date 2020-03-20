
package proyecto_ghost;

public class Player {
        
    private int  puntos;
    private final String nick;
    private final String contra;
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
    
    public void Imprimir()
    {
        System.out.println("Usuario: " + nick + "\nContraseña: " + contra + "\n");
    }
    
    public void addResultado(String resultado)
    {
        if(Resultados[0] == null)
            {
                Resultados[0] = new Resultados(resultado);
                return;
            }
        System.arraycopy(Resultados, 0, Resultados, 1, Resultados.length);
        Resultados[0]= new Resultados(resultado);
    }
}
