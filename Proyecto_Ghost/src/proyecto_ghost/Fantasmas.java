
package proyecto_ghost;

public class Fantasmas {
        
        
        Player jugador;
        String fantasmaJugador;
        String tipo;
        int cantidad = 8;
    
        public Fantasmas (Player jugador, String numJugador, String tipo)
        {
            this.tipo = tipo;
            this.jugador = jugador;
            fantasmaJugador = "F" + numJugador;
        }
        
        public void moverse (int x, int y)
        {
            
        }
}
