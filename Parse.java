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
		
		//печать array
		dbAvto.printArrayAvto();
		//закрывает окно браузера
		driver.quit();
		
		// вернуться на предыдущую страницу
		/*driver.navigate().back();
		System.out.println("back "+driver.getCurrentUrl());
		// парсинг первого авто
		try{
		driver.findElement(By.className("listing-item")).click();
		System.out.println("парсинг первого авто "+driver.getCurrentUrl());
		}catch(NoSuchElementException e){
			System.out.println("Ошибка парсинга первого авто");
			e.printStackTrace();
		}*/
		//aloneAvto();
		
		//saveImgAvto();

		// вернуться на предыдущую страницу
		// driver.navigate().back();

	}
	/**
	 * Метод переходит на 3-ю и далее страницу со списком автомобилей(25 штук)
	 */
	public void transitionPage3ToEnd(){
		while (true) {
			bustAvto();
			try {
				driver.findElements(By.className("pages-arrows-item")).get(1).click();
			} catch (NoSuchElementException e) {
				e.printStackTrace();
			} catch(IndexOutOfBoundsException e1) {
				System.out.println("Не найдено больше страниц со списком автомобилей(25 штук)");
				break;
			}
		}
		}
	/**
	 * Метод переходит на вторую страницу со списком автомобилей(25 штук)
	 */
	private void transitionPage2(){
		try {
			driver.findElements(By.className("pages-arrows-item")).get(0).click();
		} catch (NoSuchElementException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Метод перебирает все авто на данной странице. Открывает страницу авто, переход назад.
	 */
	private void bustAvto() {
		final int avtoOnPage = 25;//количество авто на странице
		for(int i=0; i<avtoOnPage;i++){
			try{
				driver.findElements(By.className("listing-item-image")).get(i).click();
				aloneAvto();
			}catch(NoSuchElementException e){
				System.out.println("Не существует 26 элемента");
				e.printStackTrace();
				//break;
			}catch(IndexOutOfBoundsException e1){
				System.out.println("Не найдено больше авто на странице");
				//e1.printStackTrace();
				break;
			}
			System.out.println("парсинг "+i+"_"+driver.getCurrentUrl());
			
			// вернуться на предыдущую страницу
			driver.navigate().back();
		}
	}
	/**
	 * Метод парсит параметры авто и записывает их в txt файл
	 */
	public void aloneAvto() {
		Avto avto = new Avto();
		
		//click для открытия контактного номера телефона
		driver.findElement(By.className("card-contacts-phones")).click();
		
		//Марка
		avto.setMarka(marka);
		//Модель
		avto.setModel(model);
		// Цена авто в рублях
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
			System.out.println("Ошибка, цена авто в рублях | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Цена авто в долларах
		//String cenaUsd = null;
		try {
			//avto.setCenaUsd(driver.findElement(By.className("card-price-approx")).getText());
			avto.setCenaUsd(Integer.parseInt(driver.findElement(By.className("card-price-approx")).getText().replaceAll(" ", "")));
			//cenaUsd = driver.findElement(By.className("card-price-approx")).getText();
		} catch (NoSuchElementException e) {
			//cenaUsd = null;
			System.out.println("Ошибка, цена авто в рублях | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Год выпуска
		//String godRelease = null;
		try {
			avto.setGodRelease(Integer.parseInt(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[1]/dl/dd")).getText()));
			/*godRelease = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[1]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Год выпуска | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Пробег
		//String mileage = null;
		try {
			avto.setMileage(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[3]/dl/dd")).getText());
			/*mileage = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[3]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Пробег | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Топливо
		//String fuel = null;
		try {
			avto.setFuel(driver.findElement(By.className("card-info")).findElement(By.tagName("ul")).findElement(By.xpath("li[4]")).findElement(By.xpath("dl")).findElement(By.xpath("dd")).getText());
			//fuel = driver.findElement(By.className("card-info")).findElement(By.tagName("ul")).findElement(By.xpath("li[4]")).findElement(By.xpath("dl")).findElement(By.xpath("dd")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Топливо | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Объем
		//String amount = null;
		try {
			avto.setAmount(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[5]/dl/dd")).getText());
			/*amount = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[5]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Объем | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Цвет
		//String color = null;
		try {
			avto.setColor(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[6]/dl/dd")).getText());
			/*color = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[6]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Цвет | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Тип кузова
		//String body = null;
		try {
			avto.setBody(driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[7]/dl/dd")).getText());
			/*body = driver.findElement(By.className("card-info")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[7]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Тип кузова | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Трансмиссия
		//String transmission = null;
		try {
			avto.setTransmission(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[8]/dl/dd")).getText());
			/*transmission = driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[8]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Трансмиссия | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Продавец
		//String name = null;
		try {
			avto.setName(driver.findElement(By.className("card-contacts-name")).getText());
			//name = driver.findElement(By.className("card-contacts-name")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Продавец | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Город
		//String city = null;
		try {
			avto.setCity(driver.findElement(By.className("card-contacts-city")).getText());
			//city = driver.findElement(By.className("card-contacts-city")).getText();
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Город | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Описание
		//String description=null;
		try{
			avto.setDescription(driver.findElement(By.className("card")).findElement(By.xpath("div[4]")).getText());
			//description = driver.findElement(By.className("card")).findElement(By.xpath("div[4]")).getText();
		}catch(NoSuchElementException e){
			System.out.println("Ошибка, Описание | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Ссылка на авто
		avto.setUrl(driver.getCurrentUrl());
		//String url = driver.getCurrentUrl();
		
		// Дата подачи объявления
		java.util.Date dateCreateParse = new java.util.Date();
		try {
			//создали экземляр, для выбора формата даты
			SimpleDateFormat sdfCreateDate = new SimpleDateFormat("dd.MM.yyyy");
			//парсим дату и проводим к установленнному формату даты
			dateCreateParse = sdfCreateDate.parse(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
						.findElement(By.xpath("//li[4]/dl/dd")).getText());
			
			if (dateCreateParse == null){
				//устанавливаем значение 	
				dateCreateParse = sdfCreateDate.parse("1900.01.01");
			}
			
			//передаем дату с помощью метода getTime() в экземляр sql.Date
			java.sql.Date dateCreateSql = new java.sql.Date(dateCreateParse.getTime());
			//устанавливаем значение 	
			avto.setCreate(dateCreateSql);
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Дата подачи объявления | Parse/aloneAvto()");
			//e.printStackTrace();
		} catch (Exception e1){
			System.out.println("Ошибка, Дата подачи объявления | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Дата обновления объявления
		java.util.Date dateUpdateParse = new java.util.Date();
		try {
			//создали экземляр, для выбора формата даты
			SimpleDateFormat sdfUpdateDate = new SimpleDateFormat("dd.MM.yyyy");
			//парсим дату и проводим к установленнному формату даты
			dateUpdateParse =  sdfUpdateDate.parse(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("li[4]")).findElement(By.xpath("dl[2]")).findElement(By.xpath("dd")).getText());
			
			java.sql.Date dateUpdateSql;
			
			if (dateUpdateParse == null){
				//передаем дату с помощью метода getTime() в экземляр sql.Date
				dateUpdateSql = new java.sql.Date(sdfUpdateDate.parse("1900.01.01").getTime());
			}else{
				//передаем дату с помощью метода getTime() в экземляр sql.Date
				dateUpdateSql = new java.sql.Date(dateUpdateParse.getTime());
			}
			//устанавливаем значение 	
			avto.setUpdate(dateUpdateSql);
			
		
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Дата обновления объявления | Parse/aloneAvto()");
			//e.printStackTrace();
		}catch (Exception e1){
			System.out.println("Ошибка, Дата обновления объявления | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		// Привод
		//String privod = null;
		try {
			avto.setPrivod(driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[9]/dl/dd")).getText());
			/*privod = driver.findElement(By.className("card-header")).findElement(By.tagName("ul"))
					.findElement(By.xpath("//li[9]/dl/dd")).getText();*/
		} catch (NoSuchElementException e) {
			System.out.println("Ошибка, Привод | Parse/aloneAvto()");
			//e.printStackTrace();
		}
		//номер телефона
		try{
			avto.setPhoneNumber(driver.findElement(By.className("card-contacts-phones-in")).findElement(By.tagName("li")).getText());
		}catch (NoSuchElementException e){
			System.out.println("Ошибка, номер телефона | Parse/aloneAvto()");
		}
		
		dbAvto.addAvtoInArray(avto);
		
		System.out.println("Цена в рублях: "+avto.getCenaRub());
		System.out.println("Цена в долларах: "+avto.getCenaUsd());
		System.out.println("Год выпуска: "+avto.getGodRelease());
		System.out.println("Пробег: "+avto.getMileage());
		System.out.println("Топливо: "+avto.getFuel());
		System.out.println("Объем: "+avto.getAmount());
		System.out.println("Цвет: "+avto.getColor());
		System.out.println("Тип кузова: "+avto.getBody());
		System.out.println("Трансмиссия: "+avto.getTransmission());
		System.out.println("Имя: "+avto.getName());
		System.out.println("Город: "+avto.getCity());
		System.out.println("Дата подачи объявления: "+avto.getCreate());
		System.out.println("Дата обновлния объявления: "+avto.getUpdate());
		System.out.println("Привод: "+avto.getPrivod());
		System.out.println("Марка: "+avto.getMarka()+" "+avto.getModel());
		System.out.println("url: "+avto.getUrl());
		System.out.println("Описание: "+avto.getDescription());
		System.out.println("Номер телефона: "+avto.getPhoneNumber());
		
		//печать в файл txt
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
		//Получение первого изображения
		driver.findElement(By.className("fotorama__stage__frame fotorama__loaded fotorama__loaded--img fotorama__active"));
		System.out.println(driver.findElement(By.className("fotorama__stage__frame fotorama__loaded fotorama__loaded--img fotorama__active")));
	}

}
