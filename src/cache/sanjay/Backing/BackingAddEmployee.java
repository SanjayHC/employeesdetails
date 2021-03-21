package cache.sanjay.Backing;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import cache.sanjay.BO.EmployeesBO;
import cache.sanjay.Model.Employee;

@ManagedBean(name="backingAddEmployee")
@ViewScoped
public class BackingAddEmployee {
	private Employee employee = new Employee();
	private EmployeesBO employeebo = new EmployeesBO();
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String saveEmployee() {
		employeebo.insertEmployee(employee);
		return "employee";	
	}
}
