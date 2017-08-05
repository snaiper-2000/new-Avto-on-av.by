package av.by;
/**
 * Класс авто, содержит все атрибуты автомобиля, которые есть сайте
 *
 */
public class Avto {
	//Марка
	private String marka = null;
	//Модель
	private String model = null;
	// Цена авто в рублях
	private int cenaRub = 0;
	// Цена авто в долларах
	private int cenaUsd = 0;
	// Год выпуска
	private int godRelease = 0;
	// Пробег
	private String mileage = null;
	// Топливо
	private String fuel = null;
	// Объем
	private String amount = null;
	// Цвет
	private String color = null;
	// Тип кузова
	private String body = null;
	// Трансмиссия
	private String transmission = null;
	// Продавец
	private String name = null;
	// Город
	private String city = null;
	// Описание
	private String description=null;
	// Ссылка на авто
	private String url = null;
	// Дата подачи объявления
	private java.sql.Date create = null;
	// Дата обновления объявления
	private java.sql.Date update = null;
	// Привод
	private String privod = null;
	//Номер телефона
	private String phoneNumber = null;
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getMarka() {
		return marka;
	}
	public void setMarka(String marka) {
		this.marka = marka;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public int getCenaRub() {
		return cenaRub;
	}
	public void setCenaRub(int cenaRub) {
		this.cenaRub = cenaRub;
	}
	public int getCenaUsd() {
		return cenaUsd;
	}
	public void setCenaUsd(int cenaUsd) {
		this.cenaUsd = cenaUsd;
	}
	public int getGodRelease() {
		return godRelease;
	}
	public void setGodRelease(int godRelease) {
		this.godRelease = godRelease;
	}
	public String getMileage() {
		return mileage;
	}
	public void setMileage(String mileage) {
		this.mileage = mileage;
	}
	public String getFuel() {
		return fuel;
	}
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTransmission() {
		return transmission;
	}
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public java.sql.Date getCreate() {
		return create;
	}
	public void setCreate(java.sql.Date create) {
		this.create = create;
	}
	public java.sql.Date getUpdate() {
		return update;
	}
	public void setUpdate(java.sql.Date update) {
		this.update = update;
	}
	public String getPrivod() {
		return privod;
	}
	public void setPrivod(String privod) {
		this.privod = privod;
	}
	
	
}
