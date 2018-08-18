
public class Client {
	
	private int playerID;
	private int playerLocation; //considerando que o espaço de jogadores é uma linha
	private static int numberOfPlayers = 0;

	//constructor
	public Client(Server s, Map m){ 
		
		playerID = ++numberOfPlayers;
		
		//no constructor do jogador(Player) eu utilizo o metodo addNewPlayer da classe Server ou eu faço isso em SimpleGame?
		if(s.addNewPlayer(playerID)) System.out.println("Seu jogador foi criado! Seu numero de identificacao eh: " + playerID);
		else System.out.println("Oops! Houve um erro na criacao do seu jogador...");
		
		playerLocation = determinePlayerInitialLocation(m);

	}

	//methods
	public int getID(){
		return playerID;
	}

	public int determinePlayerInitialLocation(Map m){
		if(m.isMapEmpty) {
			playerLocation = 0;
			m.updateMap(playerLocation);
		} else {
			playerLocation = m.findFirstEmptySpot();
			if(playerLocation == -1) {
				playerLocation = m.expandMap(); //se m.findFirstEmptySpot não encontrar nenhum lugar vazio, retornará -1 e o mapa tem que ser expandido
												//m.expandMap expande o mapa adicionando o jogador a ele e retornando sua posição 
			}
		}
	}

	public void makeMyPlayerInactive(int myID, Server s){
		
		if(s.removePlayer(myID)) System.out.println("Jogador " + myID + "esta inativo!");
		else System.out.println("Houve algum erro... Esse jogador nao existe."); //Se o jogador não estiver presente na lista, removePlayer retorna falso.

	}

}


// criar classe cliente que interaja com os metodos criados no servidor