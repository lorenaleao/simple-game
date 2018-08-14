import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private int players;
	private List<Integer> activePlayers;
	
	private void log(String msg) {
		System.out.println(msg);
	}
	
	public Server(int maxPlayers){
		players = maxPlayers;
		activePlayers = new ArrayList<Integer>();
	}
	
	public Server() {
		this(0);
	}
	
	public boolean newPlayer(int idPlayer) {
		return activePlayers.add(idPlayer);
	}
	
	public void showPlayers() {
		log("O maximo de playeres do servidor é: " + players);
		for(Integer i : activePlayers) {
			log("O player " + i + "esta ativo!");
		}
	}
	
}
