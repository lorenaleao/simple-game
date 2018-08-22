package simplebench;

import java.util.ArrayList;
import java.util.List;


// toDo: mover a Classe Mapa pra dentro de servidor, pode ser classe aninhada

public class Server {
	
	private int maxNumberOfPlayers;         //numero maximo de jogadores
	private List<Integer> activePlayers;    //vetor de jogadores ativos
	private static int numberOfPlayers = 0; //quantidade atual de jogadores 
        
	private void log(String msg) {
		System.out.println(msg);
	}
	
	//constructor
	public Server(int maxPlayers){
		maxNumberOfPlayers = maxPlayers;
		activePlayers = new ArrayList<Integer>();    
                Mapa gameMap = new Mapa(maxNumberOfPlayers);
                
                // ToDo: instanciar o mapa aqui, apos colocá-lo como classe aninhada
	}
	
	public Server() {
		this(0);
	}
        
        // ToDo: Implementar método pra alterar a posicao do cliente.
        // recebe o id do cliente + um movimento, left ou right 
        // e tenta alterar a posicao do cliente baseado nisso
	
	//methods
	public int getNewUserID(){
            return ++numberOfPlayers;
        }
        
        public boolean addNewPlayer(int playerID) {
            // toDo: Checar se pode inserir o novo usuário, como
            // temos um número máximo de jogadores, tem um
            // momento que não poderemos inserir novos usuários
            if(numberOfPlayers <= maxNumberOfPlayers)
                return activePlayers.add(playerID); //este método retorna true se tudo ocorreu bem
            else return false; 
	}

	public boolean removePlayer(Integer playerID) {
		return activePlayers.remove(playerID);
	}
	
	public void showPlayers() {
		log("O maximo de jogadores do servidor é: " + maxNumberOfPlayers);
		for(Integer i : activePlayers) {
			log("O jogador " + i + " esta ativo!");
		}
	}
        
        public int determinePlayerInitialLocation(Mapa m){
		
                
                /*if(m.isMapEmpty()) {
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
		}*/
                return playerLocation;
	}
        
        private class Mapa {
	
            private int mapSize; //tamanho do mapa inicial
            // ToDo: Trocar de List pra array simples, como tamanho fixo
            // definido no momento de inicializacao
            private boolean[] line; //este é o mapa/campo do jogo, uma linha.        

            //constructor
            public Mapa(int size){

                    mapSize = size;
                    line = new boolean[mapSize];

                    for(Boolean j : line){
                            j = false;
                    }
            }

            //methods
            public int getCurrentMapSize(){
                    return mapSize;
            }

            /*public boolean isMapEmpty(){
                    return (!line.contains(true));
            }

            public void updateMap(int position){
                    line.add(position, true);
            }

            public int findFirstEmptySpot(){
                    return line.indexOf(false);
            }

            public int expandMap(){
                    line.add(true);
                    return line.lastIndexOf(true);
            }*/
            
        }
        	
}
