package simplebench;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Vector;

/**
 * SimpleGame class - the main method is here
 * I am using it more like for testing the methods of the other classes.
 */

public class SimpleGame {
    private static Random r = new Random();
    static int conflicts = 0;
    static int RAND_MAX;
    static int numTrocas = 0;
    static int index = 0;
            
    public static void main(String[] args){
        
        if (args.length < 1) {
            System.err.println("Hey, you must set the number of players and movements!");
            System.err.println("Syntax: java simplegame 10000000 5000");
            System.exit(1);
        }
        
        // Recebendo quantidade de jogadores, mais a quantidade de movimento
        // cada jogador irá tentar realizar
        int numPlayers = Integer.parseInt(args[0]);
        int movesPerPlayer = Integer.parseInt(args[1]);
        
        // Definindo a quantidade de threads que o nosso jogo tera para gerenciar
        // os jogadores.
        int numThreads = 8;
        int clientsPerThreads = numPlayers / numThreads;
                         
        Server servidor = new Server(numPlayers);
        Client[] clients = new Client[numPlayers];
        Vector<Thread> threads = new Vector<Thread>();
                       
        
        // Inicializando os jogadores
        for (int i = 0; i < numPlayers; i++) {
            clients[i] = new Client(servidor);
        }
        
        // Criando threads que irão conectar os clientes ao servidor
        // Tarefa: Limitar o uso de threads ao valor de numThreads, colocando
        // uma conjunto de clientes para cada thread, em vez de criar milhares
        // de threads como está aqui.
        for (index = 0; index+1 < numPlayers; index++) {
            final int i = index;
            threads.add(new Thread() {
                    public void run() {
                        clients[i].connect();
                    }
            });
        }
        
        // Inicializa as threads que irão conectar ao servidor, cada uma com
        // id proprio
        for (Thread t : threads) {
            t.start();
        }
        
        // Esperar que as threads terminem a execução.
        try {
            for (Thread t : threads) {
                t.join();
            }
            // verificar se tivemos problemas com os ids;
            Set<Integer> ids = new HashSet<Integer>();
            for (Client c : clients) {
                ids.add(c.getID());
            }
            
            // porque que essa tecnica funciona?
            if (ids.size() < clients.length) {
                System.err.println("Oh, tivemos um problema com os ids, alguns" +
                        " deles são repetidos");                
                
//                for (Client c : clients) {
//                    System.err.print(" " + c.getID());
//                }            
            }
            
            
        } catch (InterruptedException ie) {
            System.err.println("We got an interrupt exception");
        }
        
        // limpando vetor com threads, para reuso
        threads.clear();
       
        // Os players tentarao trocar de posicao, possivelmente ao mesmo tempo
        for (int i = 0; i < numThreads; i++) {
            threads.add(new Thread() {
                public void run() {
                    for (int ins = 0; ins < movesPerPlayer; ins++) {                    
                        int RAND_LIMIT = 1;
                        int movement = (((int)this.getId()) * (ins + 1)) % RAND_LIMIT;
                        
                        RAND_LIMIT = numPlayers;
                        int clientIndex = (((int)this.getId()) * (ins + 1)) % RAND_LIMIT;
      
                        // implement a notion of work here
                        synchronized(servidor) {
                            numTrocas++;
                            int newLocation = clients[clientIndex].moveMyPlayer(servidor, movement);

                            if (newLocation == -1)
                                conflicts++;
                        }
                    }
                }
            });
          }
        
        
        for (Thread t : threads) {
            t.start();
        }
            
        try {
            for (Thread t : threads) {
                t.join();
            }
            System.out.println("Conflitos: " + conflicts + " de " + numPlayers + " trocas de posições");
        } catch (InterruptedException ie) {
            System.err.println("We got an interrupt exception");
        }
            
//            servidor.showPlayers();
            return; 
    }
}