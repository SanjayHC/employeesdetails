package cache.sanjay.Backing;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import cache.sanjay.BO.EmployeesBO;
import cache.sanjay.Model.Employee;

@ManagedBean(name="backingEmployees")
@SessionScoped
public class BackingEmployees {
	
	EmployeesBO employeesBO = new EmployeesBO();	
	
	@ManagedProperty(value="#{backingEditEmployee}")
	private BackingEditEmployee backingEditEmployee;

	public BackingEditEmployee getBackingEditEmployee() {
		return backingEditEmployee;
	}

	public void setBackingEditEmployee(BackingEditEmployee backingEditEmployee) {
		this.backingEditEmployee = backingEditEmployee;
	}

	public List<Employee>findAllEmployees(){		
		return employeesBO.findAllEmployees();
	}	
	
	public void delete(Employee employee) {
		employeesBO.deleteEmployee(employee);
	}
	
	public String edit(Employee employee) {
		backingEditEmployee.setEmployee(employee);
		return "update-employee";
	}
}
