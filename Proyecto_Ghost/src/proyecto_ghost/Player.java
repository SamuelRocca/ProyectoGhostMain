
package proyecto_ghost;

public class Player {
    private GhostGame game;
    private int  puntos;
    private  String nick;
    private  String contra;
    public Resultados Resultados[];
    public Resultados Results;
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
    
    public void setNick(String nick,int opcion)
    {
        if(opcion == 1)
            this.nick = nick;
    }
    
    public String getContra()
    {
        return contra;
    }
    
    public void setContra(String contra,int opcion)
    {
        if(opcion==1)
            this.contra = contra;
    }
    
    public void MostrarDatos()
    {
        System.out.println("\nUsuario: \t" + nick + "\nContraseña: \t" + contra +"\nPuntos: \t" + puntos);
    }
    
    public void addResultado(String resultado)
    {
        if(Resultados[9]!= null)
            Resultados[9] = null;
        else if(Resultados[0] == null)
        {
            Resultados[0] = new Resultados(resultado);
        }else
        {
        System.arraycopy(Resultados, 0, Resultados, 1, Resultados.length-1);
        Resultados[0]= new Resultados(resultado);
        }
    }
    public void printResults()
    {
        for(Resultados print : Resultados)
        {
            if(print != null)
            {
                print.Imprimir();
            }
        }

    }
}
