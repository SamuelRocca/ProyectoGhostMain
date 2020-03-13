
package proyecto_ghost;

public class Fantasmas {
        
        
        Player jugador;
        String tipo;
        int cantidad = 8;
    
        public Fantasmas (int tipo)
        {
            if (tipo == 1)
                this.tipo = "Bueno";
            else
                this.tipo = "Malo";
            
            
        }
        
        public void moverse (int x, int y)
        {
            
        }
}
