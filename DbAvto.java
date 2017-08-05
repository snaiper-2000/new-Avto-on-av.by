package av.by;

import java.util.ArrayList;

public class DbAvto {
	
	private ArrayList<Avto> array = new ArrayList<Avto>();
	
	public void addAvtoInArray(Avto avto){
		array.add(avto);
	}

	public void printArrayAvto(){
		for(int i=0; i<array.size();i++){
			Avto avto = array.get(i);
			System.out.println("Печать массива array "+i+" "+avto);
		}
	}
}
