package simplebench;

import java.util.ArrayList;
import java.util.List;

public class Mapa {
	
	private int mapSize; //tamanho do mapa inicial
        // ToDo: Trocar de List pra array simples, como tamanho fixo
        // definido no momento de inicializacao
	private List<Boolean> line; //este é o mapa/campo do jogo, uma linha.        

	//constructor
	public Mapa(int size){
		
		mapSize = size;
		line = new ArrayList<Boolean>(mapSize);
		
		for(Boolean j : line){
			j = false;
		}
	}

	//methods
	public int getCurrentMapSize(){
		return line.size();
	}

	public boolean isMapEmpty(){
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
	}
}