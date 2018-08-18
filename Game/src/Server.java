import java.util.ArrayList;
import java.util.List;

public class Server {
	
	private static int playerNumber; //variavel controle de IDs dos jogadores
	private int players; //numero maximo de jogadores
	private List<Integer> activePlayers; //vetor de jogadores ativos
	
	private void log(String msg) {
		System.out.println(msg);
	}
	
	//constructor
	public Server(int maxPlayers){
		playerNumber = 0;
		players = maxPlayers;
		activePlayers = new ArrayList<Integer>();
	}
	
	public Server() {
		this(0);
	}
	
	//methods
	public int setPlayerID(){
		playerNumber++;
		return playerNumber; //eu quero que essa variável seja manipulada como uma referencia, pois toda vez que eu criar um jogador, eu vou incrementa-la
	}

	public boolean createNewPlayer(int playerID) {
		return activePlayers.add(playerID);
	}
	
	public void showPlayers() {
		log("O maximo de jogadores do servidor é: " + players);
		for(Integer i : activePlayers) {
			log("O jogador " + i + "esta ativo!");
		}
	}
	
}