package Classes;

import javafx.beans.property.SimpleStringProperty;

public class Employee {
	private SimpleStringProperty id;
	private SimpleStringProperty name;
	private SimpleStringProperty job;
	private SimpleStringProperty email;
	private SimpleStringProperty address;
	private SimpleStringProperty phone;
	public Employee(String id,String name,String job,String email,String address,String phone) {
		this.id = new SimpleStringProperty(id);
		this.name = new SimpleStringProperty(name);
		this.job = new SimpleStringProperty(job);
		this.email = new SimpleStringProperty(email);
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

	public String getJob() {
		return job.get();
	}

	public void setJob(String job) {
		this.job = new SimpleStringProperty(job);
	}

	public String getAddress() {
		return address.get();
	}

	public void setAddress(String address) {
		this.address = new SimpleStringProperty(address);
	}

	public String getEmail() {
		return email.get();
	}

	public void setEmail(String email) {
		this.email = new SimpleStringProperty(email);
	}

	public String getPhone() {
		return phone.get();
	}

	public void setPhone(String phone) {
		this.phone = new SimpleStringProperty(phone);
	}
}
