
package proyecto_ghost;

public class Fantasmas {
        
        
        Player jugador;
        String tipo;
        int cantidad = 8;
    
        public Fantasmas (int tipo, int cantidad)
        {
            if (tipo == 1)
                this.tipo = "Bueno";
            else
                this.tipo = "Malo";
            
            this.cantidad = cantidad;
            
        }
        
        public void moverse (int x, int y)
        {
            
        }
}
