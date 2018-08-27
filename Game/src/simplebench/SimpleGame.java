//package SimpleGame;

/**
 * SimpleGame class - the main method is here
 * I am using it more like for testing the methods of the other classes.
 */

public class SimpleGame {
    public static void main(String[] args){
            
            int numPlayers = 1000;
            
            Server servidor = new Server(numPlayers);
            Client[] clients = new Client[numPlayers]; 
            
            for (int i = 0; i < numPlayers; i++) {
                clients[i] = new Client(servidor);
            }
            //System.out.println(servidor.determinePlayerInitialLocation(15));
            Client ultimoJogador = new Client(servidor);
            System.out.println("ID do ultimoJOgador: " + ultimoJogador.getID());
            System.out.println("localização do ultimoJOgador: " + ultimoJogador.getCurrentLocation());
            for(Client i : clients){
                System.out.println("localização do jogador " + i.getID() + ": " + i.getCurrentLocation());
            }
            
            System.out.println(clients[0].getCurrentLocation());
            int newLocation = clients[0].moveMyPlayer(servidor, 0);
            System.out.println(newLocation);
            System.out.println(clients[0].getCurrentLocation());
            
            newLocation = clients[1].moveMyPlayer(servidor, 1);
            System.out.println(newLocation);
            
            for(int i = 1; i < numPlayers; i++){
                boolean status = clients[i].makeMyPlayerInactive(servidor, clients[i]);
            }
            
            newLocation = clients[0].moveMyPlayer(servidor, 0);
            System.out.println(newLocation);
            System.out.println(clients[0].getCurrentLocation());
          
            servidor.showPlayers();
            return; 
    }
}

/// criar main que instancia varios clientes e um servidor 
// chamar metodo showPlayers do servidor