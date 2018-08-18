
public class Client {
	
	public int playerID;
	public int playerLocation; //considerando que o espaço de jogadores é uma linha

	//constructor
	public Client(int numberOfIdentification){ //constructor não definido ainda corretamente
		playerID = numberOfIdentification;
		//playerLocation = ?
	}

	//methods
	public void createMyPlayer(Server s){ //é assim mesmo que se utiliza um método de outra classe? eu preciso fazer isso? 
		
		playerID = s.setPlayerID;
		
		if(s.createNewPlayer(playerID)) System.out.println("Seu jogador foi criado! :)");
		else System.out.println("Oops! Houve um erro na criação do seu jogador...");

	}
	
	public int determinePlayerInitialLocation(Map m){
		//if(m.isMapEmpty) 
	}
	
}


// criar classe cliente que interaja com os metodos criados no servidor