package JavaProject.rentcar;

public class Car {
	
	private String car_type;
	private String car_name;
	private int usetime;
	private int price;
	
	public String getCar_type() {
		return car_type;
	}
	public void setCar_type(String car_type) {
		this.car_type = car_type;
	}
	public String getCar_name() {
		return car_name;
	}
	public void setCar_name(String car_name) {
		this.car_name = car_name;
	}
	public int getUsetime() {
		return usetime;
	}
	public void setUsetime(int usetime) {
		this.usetime = usetime;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
