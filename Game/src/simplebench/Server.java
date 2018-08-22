package simplebench;

import java.util.ArrayList;
import java.util.List;


// toDo: mover a Classe Mapa pra dentro de servidor, pode ser classe aninhada

public class Server {
	
	private int players; //numero maximo de jogadores
	private List<Integer> activePlayers; //vetor de jogadores ativos
	
	private void log(String msg) {
		System.out.println(msg);
	}
	
	//constructor
	public Server(int maxPlayers){
		players = maxPlayers;
		activePlayers = new ArrayList<Integer>();      
                
                // ToDo: instanciar o mapa aqui, apos colocá-lo como classe aninhada
	}
	
	public Server() {
		this(0);
	}
        
        // ToDo: Implementar método pra alterar a posicao do cliente.
        // recebe o id do cliente + um movimento, left ou right 
        // e tenta alterar a posicao do cliente baseado nisso
	
	//methods
	public boolean addNewPlayer(int playerID) {
            // toDo: Checar se pode inserir o novo usuário, como
            // temos um número máximo de jogadores, tem um
            // momento que não poderemos inserir novos usuários
            return activePlayers.add(playerID);
	}

	public boolean removePlayer(Integer playerID) {
		return activePlayers.remove(playerID);
	}
	
	public void showPlayers() {
		log("O maximo de jogadores do servidor é: " + players);
		for(Integer i : activePlayers) {
			log("O jogador " + i + " esta ativo!");
		}
	}
        	
}
