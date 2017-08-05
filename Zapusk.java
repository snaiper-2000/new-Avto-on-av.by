package av.by;

public class Zapusk {

	public static void main(String[] args) {
		String url = "https://cars.av.by/infiniti/fx35";
		String marka="Infinity";
		String model ="FX35";
		Parse parse = new Parse(url, marka, model);
        
		/*Avto avto = new Avto();
		PostgreSQLAvtoTest postgreSQLAvtoTest = new PostgreSQLAvtoTest();
		postgreSQLAvtoTest.postgreSQLAvtoTest(avto);
		*/
	}

}
