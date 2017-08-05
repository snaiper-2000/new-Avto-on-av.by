package av.by;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import av.by.Avto;

public class PostgreSQLAvtoTest {

	public void postgreSQLAvtoTest(Avto avto) {
	
	//public static void main(String [] args){
		
		//Avto avto = new Avto();
	
		  Connection c = null;
	      Statement stmt = null;
	      int executeCreateTable;
	      try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/avto",
	            "postgres", "1");
	         
	         stmt = c.createStatement();
	        String sql = "CREATE TABLE IF NOT EXISTS AVTOTEST " +
	                 "(ID            SERIAL          PRIMARY KEY," +
	                 " MARKA         VARCHAR(40)     NOT NULL, " +
	                 " MODEL         VARCHAR(40)     NOT NULL, " +
	                 " CENA_RUB      int         NOT NULL, " + // in Integer
	                 " CENA_USD      int, " +                  // in Integer
	                 " GOD_RELEASE   int, " +                  // in Integer
	                 " MILEAGE       VARCHAR(40), " +              
	                 " FUEL          VARCHAR(40), " +
	                 " AMOUNT        VARCHAR(40), " +
	                 " COLOR         VARCHAR(40), " +
	                 " BODY          VARCHAR(40), " +
	                 " TRANSMISSION  VARCHAR(40), " +
	                 " NAME          VARCHAR(40), " +
	                 " CITY          VARCHAR(40), " +
	                 " DESCRIPTION   TEXT, " +
	                 " URL           TEXT, " +
	                 " CREATE_AVTO   DATE, " +  //in date
	                 " UPDATE_AVTO   DATE, " +  //in date
	                 " PRIVOD        TEXT, " +
	                 " PHONE_NUMBER  TEXT)";
	          executeCreateTable = stmt.executeUpdate(sql);
	          System.out.println(executeCreateTable);
	          
	         String sqlInsert = "INSERT INTO AVTOTEST (MARKA,MODEL,CENA_RUB,CENA_USD,GOD_RELEASE,MILEAGE,FUEL,AMOUNT,COLOR,BODY,TRANSMISSION,"
	          		+ "NAME,CITY,DESCRIPTION,URL,CREATE_AVTO,UPDATE_AVTO,PRIVOD,PHONE_NUMBER) "
	                  + "VALUES ('"+avto.getMarka()+"','"+avto.getModel()+"','"+avto.getCenaRub()+"','"+avto.getCenaUsd()+"','"
	          			+avto.getGodRelease()+"','"+avto.getMileage()+"','"+avto.getFuel()+"','"+avto.getAmount()+"','"+avto.getColor()+"','"+avto.getBody()+"','"
	          			 +avto.getTransmission()+"','"+avto.getName()+"','"+avto.getCity()+"','"+avto.getDescription()+"','"
	          			  +avto.getUrl()+"','"+avto.getCreate()+"','"+avto.getUpdate()+"','"+avto.getPrivod()+"','"
	          			   +avto.getPhoneNumber()+"');";
	          		//,'1234$','300000','oil','3500','black','кепка','4x4','Dima','Moscow',"
	                  //+ "'Option','av.by','2017-04-28','no','1x1','45484468468');";
	                  
	          /*String sqlInsert = "INSERT INTO AVTOTEST (MARKA,MODEL,CENA_RUB,CENA_USD,GOD_RELEASE,MILEAGE,FUEL,AMOUNT,COLOR,BODY,TRANSMISSION,"
		          		+ "NAME,CITY,DESCRIPTION,URL,CREATE_AVTO,UPDATE_AVTO,PRIVOD,PHONE_NUMBER) "
		                  + "VALUES('marka','model',222,333,2000,1991,'mili','toplovo','litrov','color','кузов')"; 
	         	*/
	         	stmt.executeUpdate(sqlInsert);
	          
	          ResultSet rs = stmt.executeQuery( "SELECT MARKA,MODEL,PHONE_NUMBER,CREATE_AVTO FROM AVTOTEST;" );
	          while ( rs.next() ) {
	              String marka = rs.getString("MARKA");
	              String  model = rs.getString("MODEL");
	              String phone_number = rs.getString("PHONE_NUMBER");
	              String create_avto = rs.getString("CREATE_AVTO");
	              System.out.println( "marka = " + marka );
	              System.out.println( "model = " + model );
	              System.out.println( "phone_number = " + phone_number );
	              System.out.println( "create_avto = " + create_avto );
	              
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
