package av.by;

import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Parse {

	public Parse(String url, String marka, String model) {
		connectBrowser(url, marka, model);
	}
	String marka;
	String model;
	WebDriver driver;
	DbAvto dbAvto = new DbAvto();

	public void connectBrowser(String url, String marka1, String model1) {
		marka = marka1;
		this.model = model1;

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\snaip\\Desktop\\chromedriver.exe");

		// Initialize browser
		driver = new ChromeDriver();

		// Open url
		driver.get(url);
		
		
		bustAvto();
		transitionPage2();
		transitionPage3ToEnd();
		
		//������ array
		dbAvto.printArrayAvto();
		//��������� ���� ��������
		driver.quit();
		
		// ��������� �� ���������� ��������
		/*driver.navigate().back();
		System.out.println("back "+driver.getCurrentUrl());
		// ������� ������� ����
		try{
		driver.findElement(By.className("listing-item")).click();
		System.out.println("������� ������� ���� "+driver.getCurrentUrl());
		}catch(NoSuchElementException e){
			System.out.println("������ �������� ������� ����");
			e.printStackTrace();
		}*/
		//aloneAvto();
		
		//saveImgAvto();

		// ��������� �� ���������� ��������
		// driver.navigate().back();

	}
	/**
	 * ����� ��������� �� 3-� � ����� �������� �� ������� �����������(25 ����)
	 */
	public void transitionPage3ToEnd(){
		while (true) {
			bustAvto();
			try {
				driver.findElements(By.className("pages-arrows-item")).get(1).click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} catch(IndexOutOfBoundsException e1) {
				System.out.println("�� ������� ������ ������� �� ������� �����������(25 ����)");
				break;
			}
		}
		}
	/**
	 * ����� ��������� �� ������ �������� �� ������� �����������(25 ����)
	 */
	private void transitionPage2(){
		try {
			driver.findElements(By.className("pages-arrows-item")).get(0).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	/**
	 * ����� ���������� ��� ���� �� ������ ��������. ��������� �������� ����, ������� �����.
	 */
	private void bustAvto() {
		final int avtoOnPage = 25;//���������� ���� �� ��������
		for(int i=0; i<avtoOnPage;i++){
			try{
				driver.findElements(By.className("listing-item-image")).get(i).click();
				aloneAvto();
			}catch(NoSuchElementException e){
				System.out.println("�� ���������� 26 ��������");
				e.printStackTrace();
				//break;
			}catch(IndexOutOfBoundsException e1){
				System.out.println("�� ������� ������ ���� �� ��������");
				//e1.printStackTrace();
				break;
			}
			System.out.println("������� "+i+"_"+driver.getCurrentUrl());
			
			// ��������� �� ���������� ��������
			driver.navigate().back();
		}
	}
	/**
	 * ����� ������ ��������� ���� � ���������� �� � txt ����
	 */
	public void aloneAvto() {
		Avto avto = new Avto();
		
		//click ��� �������� ����������� ������ ��������
		driver.findElement(By.className("card-contacts-phones")).click();
		
		//�����
		avto.setMarka(marka);
		//������
		avto.setModel(model);
		// ���� ���� � ������
		//String cenaRub = null;
		try {
			Pattern pat=Pattern.compile("\\d+/*\\s\\d+");
			Matcher matcher=pat.matcher(driver.findElement(By.className("card-price-main")).findElement(By.tagName("span")).getText());
			while (matcher.find()) {
				avto.setCenaRub(Integer.parseInt(matcher.group().replaceAll(" ", "")));
			};
			//avto.setCenaRub(driver.findElement(By.className("card-price-main")).findElement(By.tagName("span")).getText());
			//cenaRub = driver.findElement(By.className("card-price-main")).findElement(By.tagName("span")).getText();
		} catch (NoSuchElementException e) {
			//cenaRub = null;
			System.out.println("������, ���� ���� � ������ | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ���� ���� � ��������
		//String cenaUsd = null;
		try {
			//avto.setCenaUsd(driver.findElement(By.className("card-price-approx")).getText());
			avto.setCenaUsd(Integer.parseInt(driver.findElement(By.className("card-price-approx")).getText().replaceAll(" ", "")));
			//cenaUsd = driver.findElement(By.className("card-price-approx")).getText();
		} catch (NoSuchElementException e) {
			//cenaUsd = null;
			System.out.println("������, ���� ���� � ������ | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ��� �������
		//String godRelease = null;
		try {
			avto.setGodRelease(Integer.parseInt(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[1]/dl/dd")).getText()));
			/*godRelease = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[1]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ��� ������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ������
		//String mileage = null;
		try {
			avto.setMileage(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[3]/dl/dd")).getText());
			/*mileage = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[3]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ������ | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// �������
		//String fuel = null;
		try {
			avto.setFuel(driver.findElement(By.className("card-info")).findElement(By.tagName("ul")).findElement(By.xpath("li[4]")).findElement(By.xpath("dl")).findElement(By.xpath("dd")).getText());
			//fuel = driver.findElement(By.className("card-info")).findElement(By.tagName("ul")).findElement(By.xpath("li[4]")).findElement(By.xpath("dl")).findElement(By.xpath("dd")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("������, ������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// �����
		//String amount = null;
		try {
			avto.setAmount(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[5]/dl/dd")).getText());
			/*amount = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[5]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ����� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ����
		//String color = null;
		try {
			avto.setColor(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[6]/dl/dd")).getText());
			/*color = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[6]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ���� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ��� ������
		//String body = null;
		try {
			avto.setBody(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[7]/dl/dd")).getText());
			/*body = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[7]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ��� ������ | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// �����������
		//String transmission = null;
		try {
			avto.setTransmission(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[8]/dl/dd")).getText());
			/*transmission = driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[8]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ����������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ��������
		//String name = null;
		try {
			avto.setName(driver.findElement(By.className("card-contacts-name")).getText());
			//name = driver.findElement(By.className("card-contacts-name")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("������, �������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// �����
		//String city = null;
		try {
			avto.setCity(driver.findElement(By.className("card-contacts-city")).getText());
			//city = driver.findElement(By.className("card-contacts-city")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("������, ����� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ��������
		//String description=null;
		try{
			avto.setDescription(driver.findElement(By.className("card")).findElement(By.xpath("div[4]")).getText());
			//description = driver.findElement(By.className("card")).findElement(By.xpath("div[4]")).getText();
		}catch(NoSuchElementException e){
			System.out.println("������, �������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ������ �� ����
		avto.setUrl(driver.getCurrentUrl());
		//String url = driver.getCurrentUrl();
		
		// ���� ������ ����������
		java.util.Date dateCreateParse = new java.util.Date();
		try {
			//������� ��������, ��� ������ ������� ����
			SimpleDateFormat sdfCreateDate = new SimpleDateFormat("dd.MM.yyyy");
			//������ ���� � �������� � ��������������� ������� ����
			dateCreateParse = sdfCreateDate.parse(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
						.findElement(By.xpath("//li[4]/dl/dd")).getText());
			
			if (dateCreateParse == null){
				//������������� �������� 	
				dateCreateParse = sdfCreateDate.parse("1900.01.01");
			}
			
			//�������� ���� � ������� ������ getTime() � �������� sql.Date
			java.sql.Date dateCreateSql = new java.sql.Date(dateCreateParse.getTime());
			//������������� �������� 	
			avto.setCreate(dateCreateSql);
		} catch (NoSuchElementException e) {
			System.out.println("������, ���� ������ ���������� | Parse/aloneAvto()");
			//e.printStackTrace();
		} catch (Exception e1){
			System.out.println("������, ���� ������ ���������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ���� ���������� ����������
		java.util.Date dateUpdateParse = new java.util.Date();
		try {
			//������� ��������, ��� ������ ������� ����
			SimpleDateFormat sdfUpdateDate = new SimpleDateFormat("dd.MM.yyyy");
			//������ ���� � �������� � ��������������� ������� ����
			dateUpdateParse =  sdfUpdateDate.parse(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("li[4]")).findElement(By.xpath("dl[2]")).findElement(By.xpath("dd")).getText());
			
			java.sql.Date dateUpdateSql;
			
			if (dateUpdateParse == null){
				//�������� ���� � ������� ������ getTime() � �������� sql.Date
				dateUpdateSql = new java.sql.Date(sdfUpdateDate.parse("1900.01.01").getTime());
			}else{
				//�������� ���� � ������� ������ getTime() � �������� sql.Date
				dateUpdateSql = new java.sql.Date(dateUpdateParse.getTime());
			}
			//������������� �������� 	
			avto.setUpdate(dateUpdateSql);
			
		
		} catch (NoSuchElementException e) {
			System.out.println("������, ���� ���������� ���������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}catch (Exception e1){
			System.out.println("������, ���� ���������� ���������� | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// ������
		//String privod = null;
		try {
			avto.setPrivod(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[9]/dl/dd")).getText());
			/*privod = driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[9]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("������, ������ | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		//����� ��������
		try{
			avto.setPhoneNumber(driver.findElement(By.className("card-contacts-phones-in")).findElement(By.tagName("li")).getText());
		}catch (NoSuchElementException e){
			System.out.println("������, ����� �������� | Parse/aloneAvto()");
		}
		
		dbAvto.addAvtoInArray(avto);
		
		System.out.println("���� � ������: "+avto.getCenaRub());
		System.out.println("���� � ��������: "+avto.getCenaUsd());
		System.out.println("��� �������: "+avto.getGodRelease());
		System.out.println("������: "+avto.getMileage());
		System.out.println("�������: "+avto.getFuel());
		System.out.println("�����: "+avto.getAmount());
		System.out.println("����: "+avto.getColor());
		System.out.println("��� ������: "+avto.getBody());
		System.out.println("�����������: "+avto.getTransmission());
		System.out.println("���: "+avto.getName());
		System.out.println("�����: "+avto.getCity());
		System.out.println("���� ������ ����������: "+avto.getCreate());
		System.out.println("���� ��������� ����������: "+avto.getUpdate());
		System.out.println("������: "+avto.getPrivod());
		System.out.println("�����: "+avto.getMarka()+" "+avto.getModel());
		System.out.println("url: "+avto.getUrl());
		System.out.println("��������: "+avto.getDescription());
		System.out.println("����� ��������: "+avto.getPhoneNumber());
		
		//������ � ���� txt
		WriteTxt writeTxt = new WriteTxt();
		writeTxt.writeTxtInfaAvto(avto.getMarka(), avto.getModel(), avto.getCenaRub(), avto.getCenaUsd(), avto.getGodRelease(), 
				avto.getMileage(), avto.getFuel(), avto.getAmount(), avto.getColor(), avto.getPrivod(), avto.getBody(), 
				avto.getTransmission(), avto.getUrl(), avto.getName(), avto.getCity(), avto.getDescription(), avto.getCreate(),
				avto.getUpdate(), avto.getPhoneNumber());
		
		//write in db
		PostgreSQLAvtoTest postgreSQLAvtoTest = new PostgreSQLAvtoTest();
		postgreSQLAvtoTest.postgreSQLAvtoTest(avto);
	}
	
	public void saveImgAvto(){
		//��������� ������� �����������
		driver.findElement(By.className("fotorama__stage__frame fotorama__loaded fotorama__loaded--img fotorama__active"));
		System.out.println(driver.findElement(By.className("fotorama__stage__frame fotorama__loaded fotorama__loaded--img fotorama__active")));
	}

}
