package av.by;

import java.util.ArrayList;
import java.util.Date;

import av.by.db.DbPostgreSQL;

public class ArrayAvto {
	
	/*public static void main(String [] args) {
		Avto avto = new Avto();
		
		DbAvto dbAvto = new DbAvto();
		dbAvto.addAvtoInArray(avto);
	}*/
	
	private ArrayList<Avto> arrayAvto = new ArrayList<Avto>();
	private ArrayList<Avto> arrayNewAvto = new ArrayList<Avto>();
	DbPostgreSQL dbPostgreSQL = new DbPostgreSQL();
	
	public ArrayList<Avto> addAvtoInArray(Avto avto){
		arrayAvto.add(avto);
	
		return arrayAvto;
	}
	

	public void seachAvtoByDateUpTo3Days(ArrayList<Avto> arrayAvto){
		for(int i=0; i<arrayAvto.size();i++){
			Date dateCreateAvto = arrayAvto.get(i).getCreate();
			
			int differenceDays = compareDateAvto(dateCreateAvto);
			if(compareDateAvto(dateCreateAvto) <= 3) {
				arrayNewAvto.add(arrayAvto.get(i));
			}
		}
		DbPostgreSQL dbPostgreSQL = new DbPostgreSQL();
		dbPostgreSQL.insertNewAvtoInDb(arrayNewAvto);
	}
	
	public void editArrayAvto(ArrayList<Avto> arrayAvto) {
		ArrayList<Avto> arrayAvtoInDb = dbPostgreSQL.selectAvtoInDb();
		
		for(int i=0; i<arrayAvto.size();i++){
			for(int j=0; i<arrayAvtoInDb.size();j++){
				if(arrayAvto.get(i).getUrl().equals(arrayAvtoInDb.get(i).getUrl()) 
						& arrayAvto.get(i).getCreate().equals(arrayAvtoInDb.get(i).getCreate())){
					arrayAvto.remove(i);
				}
				
			}
			
		}
		dbPostgreSQL.insertAvtoInDb(arrayAvto);
	}
	
	public int compareDateAvto(Date dateCompare) { 
	      Date currentDate = new Date();
	          
	      //  оличество дней между датами в миллисекундах
	      long difference = currentDate.getTime() - dateCompare.getTime();
	      // ѕеревод количества дней между датами из миллисекунд в дни
	      int days =  (int)(difference / (24 * 60 * 60 * 1000)); // миллисекунды / (24ч * 60мин * 60сек * 1000мс)
	      // ¬ывод разницы между датами в дн€х на экран
	      System.out.println(days + " дн.");
	      return days;
	}
}
