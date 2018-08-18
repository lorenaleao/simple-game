import java.util.ArrayList;
import java.util.List;

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
	}
	
	public Server() {
		this(0);
	}
	
	//methods
	public boolean addNewPlayer(int playerID) {
		return activePlayers.add(playerID);
	}

	public boolean removePlayer(Integer playerID) {
		return activePlayers.remove(playerID);
	}
	
	public void showPlayers() {
		log("O maximo de jogadores do servidor é: " + players);
		for(Integer i : activePlayers) {
			log("O jogador " + i + "esta ativo!");
		}
	}
	
}