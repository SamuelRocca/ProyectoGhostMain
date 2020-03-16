
package proyecto_ghost;

public class Fantasmas {
        
        
        Player jugador;
        private String tipo, numJugador = "f";
        int cantidad = 8;
    
        public Fantasmas (int tipo, int numJugador)
        {
            if (tipo == 1)
                this.tipo = "Bueno";
            else if (tipo == 0)
                this.tipo = "Malo";
            
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
