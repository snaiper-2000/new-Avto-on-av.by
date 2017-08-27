package av.by.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import av.by.Avto;

public class DbPostgreSQL {
	/*public static void main(String[] args) {
		DbPostgreSQL dbPostgreSQL = new DbPostgreSQL();
		ArrayList<Avto> array = dbPostgreSQL.selectAvtoInDb();
		
		for(int i=0; i<array.size();i++){
			//Avto avto = array.get(i).getCreate();
			System.out.println("Печать массива array "+array.get(i).getCreate());
			System.out.println("Печать массива array "+array.size());
		}
	}*/
	public void dbPostgreSQL(ArrayList<Avto> arrayAvto) {
		if(arrayAvto.size() != 0) {
			if(selectAvtoInDb().size() != 0) {
				
				
				
			}else {insertAvtoInDb(arrayAvto);}
			
		}else {System.out.println("Массив с спаршеными авто пустой!");
		return;}
	}
	/**
	 * 
	 * @return
	 */
	public ArrayList<Avto> selectAvtoInDb() {
		ArrayList<Avto> arrayAvtoInDb = new ArrayList<Avto>();
		
		Connection c = null;
	    Statement stmt = null;
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
	         
	         ResultSet rs = stmt.executeQuery( "SELECT * FROM AVTOTEST;");
	         while ( rs.next() ) {
	        	 Avto avto = new Avto();
	        	 
	        	 //java.sql.Date dateCreateAvtoInDB = rs.getDate("DATE_CREATE_AVTO_IN_DB");
	             avto.setMarka(rs.getString("MARKA")); 
	             avto.setModel(rs.getString("MODEL"));
	             avto.setCenaRub(rs.getInt("CENA_RUB"));
	             avto.setCenaUsd(rs.getInt("CENA_USD"));
	             avto.setGodRelease(rs.getInt("GOD_RELEASE"));
	             avto.setMileage(rs.getString("MILEAGE"));
	             avto.setFuel(rs.getString("FUEL"));
	             avto.setAmount(rs.getString("AMOUNT")); 
	             avto.setColor(rs.getString("COLOR"));
	             avto.setBody(rs.getString("BODY"));
	             avto.setTransmission(rs.getString("TRANSMISSION")); 
	             avto.setName(rs.getString("NAME"));
	             avto.setCity(rs.getString("CITY"));
	             avto.setDescription(rs.getString("DESCRIPTION")); 
	             avto.setUrl(rs.getString("URL"));
	             avto.setCreate(rs.getDate("DATE_CREATE_AVTO"));
	             avto.setUpdate(rs.getDate("DATE_UPDATE_AVTO")); 
	             avto.setPrivod(rs.getString("PRIVOD"));
	             avto.setPhoneNumber(rs.getString("PHONE_NUMBER"));
	              
	             arrayAvtoInDb.add(avto);
	              
	         }
	          stmt.close();
	          c.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
		return arrayAvtoInDb;
	}
	/**
	 * 
	 * @param arrayAvto
	 */
	public void insertAvtoInDb(ArrayList<Avto> arrayAvto) {
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
	          
	          for(int i=0; i<arrayAvto.size();i++){
		          
		          String sqlInsert = "INSERT INTO NEWAVTOTEST (DATE_CREATE_AVTO_IN_DB,MARKA,MODEL,CENA_RUB,CENA_USD,GOD_RELEASE,MILEAGE,FUEL,AMOUNT,COLOR,BODY,TRANSMISSION,"
			          		+ "NAME,CITY,DESCRIPTION,URL,DATE_CREATE_AVTO,DATE_UPDATE_AVTO,PRIVOD,PHONE_NUMBER) "
			                  + "VALUES ('04.08.2017','"+arrayAvto.get(i).getMarka()+"','"+arrayAvto.get(i).getModel()+"','"+arrayAvto.get(i).getCenaRub()+"','"
			          		   +arrayAvto.get(i).getCenaUsd()+"','"+arrayAvto.get(i).getGodRelease()+"','"+arrayAvto.get(i).getMileage()+"','"
			                    +arrayAvto.get(i).getFuel()+"','"+arrayAvto.get(i).getAmount()+"','"+arrayAvto.get(i).getColor()+"','"+arrayAvto.get(i).getBody()+"','"
			          			 +arrayAvto.get(i).getTransmission()+"','"+arrayAvto.get(i).getName()+"','"+arrayAvto.get(i).getCity()+"','"+arrayAvto.get(i).getDescription()+"','"
			          			  +arrayAvto.get(i).getUrl()+"','"+arrayAvto.get(i).getCreate()+"','"+arrayAvto.get(i).getUpdate()+"','"+arrayAvto.get(i).getPrivod()+"','"
			          			   +arrayAvto.get(i).getPhoneNumber()+"');";
	          		
	         	stmt.executeUpdate(sqlInsert);
	          }
	          stmt.close();
	          c.close();
	      } catch (Exception e) {
	         e.printStackTrace();
	         System.err.println(e.getClass().getName()+": "+e.getMessage());
	         System.exit(0);
	      }
	}
	/**
	 * 
	 * @param arrayNewAvto
	 */
    public void insertNewAvtoInDb(ArrayList<Avto> arrayNewAvto) {
    	Connection c = null;
	    Statement stmt = null;
		try {
	         Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://localhost:5432/avto",
	            "postgres", "1");
	         
	         stmt = c.createStatement();
	         
	         String sql = "CREATE TABLE IF NOT EXISTS NEWAVTOTEST " +
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
	          
	         for(int i=0; i<arrayNewAvto.size();i++){
	          
	          String sqlInsert = "INSERT INTO NEWAVTOTEST (DATE_CREATE_AVTO_IN_DB,MARKA,MODEL,CENA_RUB,CENA_USD,GOD_RELEASE,MILEAGE,FUEL,AMOUNT,COLOR,BODY,TRANSMISSION,"
		          		+ "NAME,CITY,DESCRIPTION,URL,DATE_CREATE_AVTO,DATE_UPDATE_AVTO,PRIVOD,PHONE_NUMBER) "
		                  + "VALUES ('04.08.2017','"+arrayNewAvto.get(i).getMarka()+"','"+arrayNewAvto.get(i).getModel()+"','"+arrayNewAvto.get(i).getCenaRub()+"','"
		          		   +arrayNewAvto.get(i).getCenaUsd()+"','"+arrayNewAvto.get(i).getGodRelease()+"','"+arrayNewAvto.get(i).getMileage()+"','"
		                    +arrayNewAvto.get(i).getFuel()+"','"+arrayNewAvto.get(i).getAmount()+"','"+arrayNewAvto.get(i).getColor()+"','"+arrayNewAvto.get(i).getBody()+"','"
		          			 +arrayNewAvto.get(i).getTransmission()+"','"+arrayNewAvto.get(i).getName()+"','"+arrayNewAvto.get(i).getCity()+"','"+arrayNewAvto.get(i).getDescription()+"','"
		          			  +arrayNewAvto.get(i).getUrl()+"','"+arrayNewAvto.get(i).getCreate()+"','"+arrayNewAvto.get(i).getUpdate()+"','"+arrayNewAvto.get(i).getPrivod()+"','"
		          			   +arrayNewAvto.get(i).getPhoneNumber()+"');";
	          		
	         	stmt.executeUpdate(sqlInsert);
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
