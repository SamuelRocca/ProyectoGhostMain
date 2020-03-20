
package proyecto_ghost;

public class Fantasmas {
        
        
        Player jugador;
        private String tipo, numJugador = "f";
        int cantidad = 8;
    
        public Fantasmas (String tipo, int numJugador)
        {
            this.tipo = tipo;
            
            this.numJugador += Integer.toString(numJugador);
            
        }
          
        public String getTipo()
        {
            return tipo;
        }
        
        public String getNum()
        {
            return numJugador;
        }
}
