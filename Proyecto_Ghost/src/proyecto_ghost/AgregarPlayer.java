
package proyecto_ghost;

public class AgregarPlayer{
        private Player players[];

         public AgregarPlayer (int tamanio)
            {
                players = new Player[tamanio];
                }
     
       public Player buscar(String nick)
        {
            for (Player jugadorF : players) 
            {
                if (jugadorF != null) //Valida que la posicion del arreglo no este vacia
                {
                    if (jugadorF.getNick().equals(nick))
                    {
                        return jugadorF; //Si el jugador existe retorna ese jugador, todos sus atributos y metodos
                    }
                }
            }

            return null;
        }
       
        public boolean Agregar(String name, String contra)
        {
            if (buscar(name)==null) //Valida que el nickname este disponible utilizando la funcion de buscar
            {
                for (int p = 0; p < players.length;p++)
                {
                    if (players[p] == null)
                    {
                        players[p] = new Player(name,contra); //Si la posicion en p esta vacia, crea un nuevo player en esa posicion con ese nickname y contraseña
                        return true;
                    }
                }
            }
            return false;
        }
        
        public boolean VerificarUsuario(String usuario, String contra)
        {
            Player jugadorM = buscar(usuario); //Busca si el usuario existe 
            
            if (jugadorM != null)
            {
                if (jugadorM.getContra().equals(contra)) // Valida que la contraseña ingresada sea igual a la contraseña del usuario
                    return true;
            }
            
            return false;
        }
}
