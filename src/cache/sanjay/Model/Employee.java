package cache.sanjay.Model;

public class Employee {
	long id;
	String firstName;
	String lastName;
	String company;
	String emplNumber;
	double salary;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getEmplNumber() {
		return emplNumber;
	}
	public void setEmplNumber(String emplNumber) {
		this.emplNumber = emplNumber;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", company=" + company
				+ ", emplNumber=" + emplNumber + ", salary=" + salary + "]";
	}
		

}
