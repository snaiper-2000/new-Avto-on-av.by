package av.by;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class WriteTxt {

	public void writeTxtInfaAvto(String marka, String model, int cenaRub, int cenaUsd, int godRelease,
			String mileage, String fuel, String amount, String color, String privod, String body, String transmission,
			String url, String name, String city, String description, java.sql.Date create, java.sql.Date update, String phoneNumber) {

		File file = new File(""+marka+" "+model+".txt");

		try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));)

		{
			//printWriter.println(" ");

			//printWriter.println("| | " + dateWrite + "|");

			printWriter.println("Марка: "+marka+"  Модель: "+model+" Цена руб.: " +cenaRub+" Цена usd: "+cenaUsd+
					" Год выпуска: "+godRelease+" Пробег: "+mileage+" Топливо: "+fuel+" Объем: "+amount+" Цвет: "+color+
					" Привод: "+privod+" Тип кузова: "+body+" Трансмиссия: "+transmission+" url: "+url+" Имя: "+name+
					" Город: "+city+" Описание: "+description+" Создание: "+create+" Обновление: "+update+" Номер телефона: "+phoneNumber);

			printWriter.flush();
			printWriter.close();
		} catch (IOException ex) {

			System.out.println(ex.getMessage());
		}
	}

}
