package simplebench;



public class Client {
	
	private int playerID;
	private int playerLocation; //considerando que o espaço de jogadores é uma linha
	private static int numberOfPlayers = 0;

	//constructor
	public Client(Server s, Mapa m){ 
		
            
                // ToDo: Delegar ao servidor o trabalho de gerar um ID novo 
                // para este usuário, baseado nos players atualmente ativos no 
                // jogo playerID = server.getNewUserId();
		playerID = ++numberOfPlayers;
		
		//no constructor do jogador(Player) eu utilizo o metodo addNewPlayer da classe Server ou eu faço isso em SimpleGame?
		if(s.addNewPlayer(playerID)) System.out.println("Seu jogador foi criado! Seu numero de identificacao eh: " + playerID);
		else System.out.println("Oops! Houve um erro na criacao do seu jogador...");
		
                // ToDo: Delegar essa determinacao pro servidor
		playerLocation = determinePlayerInitialLocation(m);

	}

	//methods
	public int getID(){
            return playerID;
	}
        
        // ToDo: Implementar um método que muda a posicao do player
        // envia uma solicitacao pro servidor ( left ou right ).
        // o servidor tenta alterar a posicao do cliente para alguma
        // dessas novas posicoes. Se nao houver conflito o servidor 
        // retorna true senão false, ou retorna a nova posicao se sucesso
        // e -1 se teve conflito

	public int determinePlayerInitialLocation(Mapa m){
		if(m.isMapEmpty()) {
                    // ToDo: Usar posicao aleatória em vez de sequencial
                    // posicao aleatorio restringida pelo numero maximo de players
			playerLocation = 0;
			m.updateMap(playerLocation);
		} else {
			playerLocation = m.findFirstEmptySpot();
			if(playerLocation == -1) {
				playerLocation = m.expandMap(); //se m.findFirstEmptySpot não encontrar nenhum lugar vazio, retornará -1 e o mapa tem que ser expandido
												//m.expandMap expande o mapa adicionando o jogador a ele e retornando sua posição 
			}
		}
                return playerLocation;
	}

	public void makeMyPlayerInactive(int myID, Server s){
		
		if(s.removePlayer(myID)) System.out.println("Jogador " + myID + "esta inativo!");
		else System.out.println("Houve algum erro... Esse jogador nao existe."); //Se o jogador não estiver presente na lista, removePlayer retorna falso.

	}

}


// criar classe cliente que interaja com os metodos criados no servidor