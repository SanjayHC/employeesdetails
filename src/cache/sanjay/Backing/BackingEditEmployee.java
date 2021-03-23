package cache.sanjay.Backing;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import cache.sanjay.BO.EmployeesBO;
import cache.sanjay.Model.Employee;

@ManagedBean(name="backingEditEmployee", eager=true)
@SessionScoped
public class BackingEditEmployee {
	private Employee employee = new Employee();
	private EmployeesBO employeesBO = new EmployeesBO();
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public String updateEmployee() {
		employeesBO.updateEmployee(employee);		
		return "employees";		
	}
}
