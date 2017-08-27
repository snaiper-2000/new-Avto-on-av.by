package av.by;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import av.by.Avto;

public class PostgreSQLAvtoTest {

	public void postgreSQLAvtoTest(Avto avto) {
	
	//public static void main(String [] args){
		
		//Avto avto = new Avto();
	
		  Connection c = null;
	      Statement stmt = null;
	      //int executeCreateTable;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/avto",
	            "postgres", "1");
	         
	         stmt = c.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS AVTOTEST " +
	        		 "(ID                       SERIAL          PRIMARY KEY," +
	                 " DATE_CREATE_AVTO_IN_DB   DATE            NOT NULL, " +
	                 " MARKA                    VARCHAR(40)     NOT NULL, " +
	                 " MODEL                    VARCHAR(40)     NOT NULL, " +
	                 " CENA_RUB                 int             NOT NULL, " + // in Integer
	                 " CENA_USD                 int, " +                      // in Integer
	                 " GOD_RELEASE              int, " +                      // in Integer
	                 " MILEAGE                  VARCHAR(40), " +              
	                 " FUEL                     VARCHAR(40), " +
	                 " AMOUNT                   VARCHAR(40), " +
	                 " COLOR                    VARCHAR(40), " +
	                 " BODY                     VARCHAR(40), " +
	                 " TRANSMISSION             VARCHAR(40), " +
	                 " NAME                     VARCHAR(40), " +
	                 " CITY                     VARCHAR(40), " +
	                 " DESCRIPTION              TEXT, " +
	                 " URL                      VARCHAR(40), " +
	                 " DATE_CREATE_AVTO         DATE, " +  //in date
	                 " DATE_UPDATE_AVTO         DATE, " +  //in date
	                 " PRIVOD                   VARCHAR(40), " +
	                 " PHONE_NUMBER             VARCHAR(40))";
	          stmt.executeUpdate(sql);
	          //System.out.println(executeCreateTable);
	          
	          String sqlInsert = "INSERT INTO AVTOTEST (DATE_CREATE_AVTO_IN_DB,MARKA,MODEL,CENA_RUB,CENA_USD,GOD_RELEASE,MILEAGE,FUEL,AMOUNT,COLOR,BODY,TRANSMISSION,"
		          		+ "NAME,CITY,DESCRIPTION,URL,DATE_CREATE_AVTO,DATE_UPDATE_AVTO,PRIVOD,PHONE_NUMBER) "
		                  + "VALUES ('04.08.2017','"+avto.getMarka()+"','"+avto.getModel()+"','"+avto.getCenaRub()+"','"+avto.getCenaUsd()+"','"
		          			+avto.getGodRelease()+"','"+avto.getMileage()+"','"+avto.getFuel()+"','"+avto.getAmount()+"','"+avto.getColor()+"','"+avto.getBody()+"','"
		          			 +avto.getTransmission()+"','"+avto.getName()+"','"+avto.getCity()+"','"+avto.getDescription()+"','"
		          			  +avto.getUrl()+"','"+avto.getCreate()+"','1970.01.01','"+avto.getPrivod()+"','"
		          			   +avto.getPhoneNumber()+"');";
	          		
	         	stmt.executeUpdate(sqlInsert);
	          
	         	 ResultSet rs = stmt.executeQuery( "SELECT * FROM AVTOTEST;" );
	          while ( rs.next() ) {
	        	  java.sql.Date dateCreateAvtoInDB = rs.getDate("DATE_CREATE_AVTO_IN_DB");
	              String marka = rs.getString("MARKA");
	              String model = rs.getString("MODEL");
	              int cenaRub = rs.getInt("CENA_RUB");
	              int cenaUsd = rs.getInt("CENA_USD");
	              int godRelease = rs.getInt("GOD_RELEASE");
	              String milage = rs.getString("MILEAGE");
	              String fuel = rs.getString("FUEL");
	              String amount = rs.getString("AMOUNT");
	              String color = rs.getString("COLOR");
	              String body = rs.getString("BODY");
	              String transmission = rs.getString("TRANSMISSION");
	              String name = rs.getString("NAME");
	              String city = rs.getString("CITY");
	              String description = rs.getString("DESCRIPTION");
	              String url = rs.getString("URL");
	              java.sql.Date dateCreateAvto = rs.getDate("DATE_CREATE_AVTO");
	              java.sql.Date dateUpdateAvto = rs.getDate("DATE_UPDATE_AVTO");
	              String privod = rs.getString("PRIVOD");
	              String phone_number = rs.getString("PHONE_NUMBER");
	              
	              System.out.println( "dateCreateAvtoInDB = " + dateCreateAvtoInDB );
	              System.out.println( "marka = " + marka );
	              System.out.println( "model = " + model );
	              System.out.println( "cenaRub = " + cenaRub );
	              System.out.println( "cenaUsd = " + cenaUsd );
	              System.out.println( "godRelease = " + godRelease );
	              System.out.println( "milage = " + milage );
	              System.out.println( "fuel = " + fuel );
	              System.out.println( "amount = " + amount );
	              System.out.println( "color = " + color );
	              System.out.println( "body = " + body );
	              System.out.println( "transmission = " + transmission );
	              System.out.println( "name = " + name );
	              System.out.println( "city = " + city );
	              System.out.println( "description = " + description );
	              System.out.println( "url = " + url );
	              System.out.println( "dateCreateAvto = " + dateCreateAvto );
	              System.out.println( "dateUpdateAvto = " + dateUpdateAvto );
	              System.out.println( "privod = " + privod );
	              System.out.println( "phone_number = " + phone_number );
	              
	           }
	          stmt.close();
	          c.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }

	}

}
