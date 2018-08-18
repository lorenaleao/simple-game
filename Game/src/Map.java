import java.util.ArrayList;

public class Map {
	
	private int mapSize; //tamanho do mapa inicial
	private ArrayList<Boolean> line; //este é o mapa/campo do jogo, uma linha.

	//constructor
	public Map(int size){
		
		mapSize = size;
		line = new ArrayList<Boolean>(mapSize);
		
		for(Boolean j : line){
			i = false;
		}
	}

	//methods
	public int getCurrentMapSize(){
		return line.size;
	}

	public void updateMap(){

	}

	public boolean isMapEmpty(){
		return (!line.contains(true));
	}
}