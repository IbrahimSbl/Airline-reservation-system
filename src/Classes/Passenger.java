package Classes;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Passenger {
	private SimpleStringProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty birth;
	private SimpleStringProperty address;
	private SimpleStringProperty phone;
	public Passenger(String id,String name,String birth,String address,String phone) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.birth = new SimpleStringProperty(birth);
		this.address = new SimpleStringProperty(address);
		this.phone = new SimpleStringProperty(phone);
	}
	public String getId() {
		return id.get();
	}

	public void setId(String id) {
		this.id = new SimpleStringProperty(id);
	}

	public String getName() {
		return name.get();
	}

	public void setName(String name) {
		this.name = new SimpleStringProperty(name);
	}


	public void setBirth(String birth) {
		this.birth = new SimpleStringProperty(birth);
	}

	public String getBirth() {
		return birth.get();
	}

	public String getAddress() {
		return address.get();
	}
	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
}