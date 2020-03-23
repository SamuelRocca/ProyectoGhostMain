package proyecto_ghost;

/**
 *
 * @author molin
 */
public class Resultados {
    private String resultado;
    
    public Resultados(String resultado)
    {
        this.resultado = resultado;
    }
    
    public String getResult()
    {
        return resultado;
    }
    
    public void Imprimir (){
        System.out.println("\t\t\t" + resultado);
    }
}
