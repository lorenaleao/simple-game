package simplebench;


public class SimpleGame {
	public static void main(String[] args){
            
            int numPlayers = 15;
            
            Server servidor = new Server(numPlayers);
            Mapa mapaDoJogo = new Mapa(numPlayers);
            Client[] clients = new Client[numPlayers]; 
            
            for (int i = 0; i < numPlayers; i++) {
                clients[i] = new Client(servidor, mapaDoJogo);
            }
            
            System.out.println(clients[0].determinePlayerInitialLocation(mapaDoJogo));
            
            servidor.showPlayers();
		
	}
}


/// criar main que instancia varios clientes e um servidor 
// chamar metodo showPlayers do servidor