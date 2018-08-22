package simplebench;



public class Client {
	
	private int playerID;
	private int playerLocation; //considerando que o espaço de jogadores é uma linha, a localização de um jogador é apenas um número

	//constructor
	public Client(Server s, Mapa m){ 
		
                // ToDo: Delegar ao servidor o trabalho de gerar um ID novo 
                // para este usuário, baseado nos players atualmente ativos no 
                // jogo playerID = server.getNewUserId();
		
		playerID = s.getNewUserID();
		if(s.addNewPlayer(playerID)) {
                    System.out.println("Seu jogador foi criado! Seu numero de identificacao eh: " + playerID);
                    playerLocation = s.determinePlayerInitialLocation(m);
                } else System.out.println("Oops! Houve um erro na criacao do seu jogador ou o mapa atingiu sua capacidade máxima...");
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
        
        public int moveMyPlayer(int movement){
            
            //0 == RIGHT
            //1 == LEFT
            
            switch(movement){
                case 0:
                case 1:
            }
        }

	public void makeMyPlayerInactive(int myID, Server s){
		
		if(s.removePlayer(myID)) System.out.println("Jogador " + myID + "esta inativo!");
		else System.out.println("Houve algum erro... Esse jogador nao existe."); //Se o jogador não estiver presente na lista, removePlayer retorna falso.

	}

}


// criar classe cliente que interaja com os metodos criados no servidor
