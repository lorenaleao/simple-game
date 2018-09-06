package simplebench;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Server class
 *      The Server controls the maximum number of players (maxNumberOfPlayers variable),
 * which players are active at the moment (activePlayers list), the creation of ID 
 * numbers through the numberOfPlayers variable and instantiates the Mapa class 
 * (the game map). 
 *      Other server responsibilities are: add and remove players, list the active players 
 * when requested, determine the initial position of each one, verify if it is possible 
 * to move the player when the player asks and move it if so, update the game map. 
 *
 */

public class Server {
    private final int maxNumberOfPlayers;   //numero maximo de jogadores
    private List<Integer> activePlayers;    //vetor de jogadores ativos
    private static int numberOfPlayers = 0; //quantidade de jogadores criados até o momento, podem estar ativos ou não (controla os numeros de ID)
    private Mapa gameMap;                   //mapa do jogo
    
    //constructor
    public Server(int maxPlayers){
        maxNumberOfPlayers = maxPlayers;
        activePlayers = new ArrayList<Integer>();    
        gameMap = new Mapa(maxNumberOfPlayers);
                
        // ToDo: instanciar o mapa aqui, apos colocá-lo como classe aninhada
    }
    
    public Server() {
        this(0);
    }
        
    //methods
    public synchronized int createNewUserID(){
        return ++numberOfPlayers;
    }
        
    public boolean addNewPlayer(int playerID) {
        // toDo: Checar se pode inserir o novo usuário, como
        // temos um número máximo de jogadores, tem um
        // momento que não poderemos inserir novos usuários
        if(activePlayers.size() < maxNumberOfPlayers){

            if(activePlayers.add(playerID)) {
                //System.out.println("Seu jogador foi criado! Seu numero de identificacao é: " + playerID);
                return true;
            } else {
                System.err.println("Oops! Houve um erro na criacao do seu jogador...");
                return false;
            }

        } else {
            System.err.println("Eita! O mapa atingiu sua capacidade máxima.");
            return false;
        }
    }

    public boolean removePlayer(Client player) {
            
        Integer playerID = player.getID();
        int playerCurrentLocation = player.getCurrentLocation();

        if(activePlayers.remove(playerID)) {
            System.out.println("Jogador " + player.getID() + " esta inativo!");
            gameMap.updateMap(playerCurrentLocation, 0);
            return true;
        } else {
            System.out.println("Houve algum erro... Esse jogador nao existe."); 
            return false; //Se o jogador não estiver presente na lista, removePlayer retorna falso.
        } 
    }
    
    public void showPlayers() {
        System.out.println("O maximo de jogadores suportado pelo servidor é: " + maxNumberOfPlayers);
        for(Integer i : activePlayers) {
            System.out.println("O jogador " + i + " esta ativo!");
        }
    }
       
    public int determinePlayerInitialLocation(int playerID){
        if(playerID <= maxNumberOfPlayers){
                    
            int playerLocation;
            Random startingPosition = new Random();
            do{
                playerLocation = startingPosition.nextInt(maxNumberOfPlayers);
            } while(gameMap.line[playerLocation]!=0);
            gameMap.updateMap(playerLocation, playerID);
                    
            return playerLocation;
                    
        } else return -1;
    }
        
    public int searchPlayerPosition(int playerID){
        int position = 0;
        while(gameMap.line[position]!= playerID)
            position++;
        return position;
    }
        
    public boolean isItPossibleToMoveToRight(int currentPlayerLocation){
        return ((currentPlayerLocation+1)<maxNumberOfPlayers && gameMap.line[currentPlayerLocation+1] == 0);
    }
        
    public boolean isItPossibleToMoveToLeft(int currentPlayerLocation){
        return ((currentPlayerLocation-1)>= 0 && gameMap.line[currentPlayerLocation-1] == 0);
    }
       
        // ToDo: Implementar método pra alterar a posicao do cliente.
        // recebe o id do cliente + um movimento, left ou right 
        // e tenta alterar a posicao do cliente baseado nisso
    public int movePlayerToRight(int playerID){
        int currentPosition = searchPlayerPosition(playerID); //procurando a localização atual do jogador, precisa ser mais eficiente?
        if(isItPossibleToMoveToRight(currentPosition)){
            int newPosition = currentPosition + 1;
            gameMap.line[newPosition] = playerID; //jogador foi movido para a direita
            gameMap.line[currentPosition] = 0; //a posição antiga do jogador fica vazia
            return newPosition;
        } else 
            return -1;
    }
            
    public int movePlayerToLeft(int playerID){
        int currentPosition = searchPlayerPosition(playerID); //procurando a localização atual do jogador, precisa ser mais eficiente?
        if(isItPossibleToMoveToLeft(currentPosition)){
            int newPosition = currentPosition - 1;
            gameMap.line[newPosition] = playerID; //jogador foi movido para a esquerda
            gameMap.line[currentPosition] = 0; //a posição antiga do jogador fica vazia
            return newPosition;
        } else 
            return -1;
    }
        
    private class Mapa {
    
        private int mapSize; //tamanho do mapa inicial
        
        // ToDo: Trocar de List pra array simples, como tamanho fixo
        // definido no momento de inicializacao
        private int[] line; //este é o mapa/campo do jogo, uma linha.        

        //constructor
        public Mapa(int size){

            mapSize = size;
            line = new int[mapSize];

//            for(int j : line){
//                j = 0;
//            }
        }

        //methods
        public int getMapSize(){
            return mapSize;
        }

        public void updateMap(int position, int id){
            line[position] = id;
        }
            
    }
}