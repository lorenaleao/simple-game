package simplebench;

/**
 * Client class
 *      Every client/player has two characteristics: a unique ID number (playerID variable)
 * and his current position on the game map (playerLocation variable). 
 *      When they are created the Server has the job of generate a new ID number for  each 
 * one based on the number of active players at the moment, add them on the active players 
 * list and determine their initial location on the map, which, in this case, is a simple line. 
 *      If it is not possible to create a player, a error message appears to the client and 
 * his playerLocation is an invalid number (-1).
 *
 */

public class Client {
    private int playerID;
    private int playerLocation; //considerando que o espaço de jogadores é uma linha, a localização de um jogador é apenas um número
    private Server s;

    public Client(Server _s) {
        s = _s;
    }
    
    public void connect() {
                 // ToDo: Delegar ao servidor o trabalho de gerar um ID novo 
                // para este usuário, baseado nos players atualmente ativos no 
                // jogo playerID = server.getNewUserId();
        
        
        playerID = s.createNewUserID();
        if(s.addNewPlayer(playerID))
            playerLocation = s.determinePlayerInitialLocation(playerID);
        else 
            playerLocation = -1;
    }

    /**
    * methods
    *
    * public int getID();
    * parameters: none.
    * returns: an int, the player ID number.
    *
    */

    public int getID(){
        return playerID;
    }

    /**
    * public int getCurrentLocation();
    * parameters: none.
    * returns: an int, the current player location.
    *
    */
        
    public int getCurrentLocation(){
        return playerLocation;
    }
            // ToDo: Implementar um método que muda a posicao do player
        // envia uma solicitacao pro servidor ( left ou right ).
        // o servidor tenta alterar a posicao do cliente para alguma
        // dessas novas posicoes. Se nao houver conflito o servidor 
        // retorna true senão false, ou retorna a nova posicao se sucesso
        // e -1 se teve conflito
    
    /**
    * public int moveMyPlayer(Server s, int movement);
    * parameters: an Object - Server, an int that indicates to where the player wants to 
    * move. 0 represents moving to the right, 1 to the left.
    * returns: an int. If the requested position is available, the new position of the 
    * player on the game map. Otherwise, an invalid number (-1 or -2 (if the movement is 
    * invalid)). 
    * 
    * With this function, Client can ask the server to change its position on the map. 
    * The playerLocation is updated if the player succeeds in moving.
    *
    */

    public int moveMyPlayer(Server s, int movement){
        //0 == RIGHT
        //1 == LEFT
        int playerNewPosition;
            
        switch(movement){
            case 0: 
                playerNewPosition = s.movePlayerToRight(playerID);
                break;
            case 1: 
                playerNewPosition = s.movePlayerToLeft(playerID);
                break;
            default: 
                playerNewPosition = -2;
                System.out.println("Não é um movimento valido.");
            }
            
        if(playerNewPosition >= 0)
            playerLocation = playerNewPosition;

        return playerNewPosition;
    }

    /**
    * public boolean makeMyPlayerInactive(Server s, Client X);
    * parameters: two Objects - a Server and a Client. 
    * returns: an boolean indicating if the removal was successful.
    * 
    * With this function, Client can ask the server to make his player inactive.
    */

    public boolean makeMyPlayerInactive(Server s, Client X){
            return s.removePlayer(X);
    }
}
