package cache.sanjay.BO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import cache.sanjay.Model.Employee;

public class EmployeesBO {
	public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;

    public List<Employee> findAllEmployees(){
		List<Employee> employeeList = new ArrayList<Employee>();
	
		Statement stmt = null;
		String sql;
		
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");     
	            String db_url ="jdbc:mysql://localhost:3306/employee?useSSL=false",
	                    db_userName = "root",
	                    db_password = "rootsan";
	            connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
			stmt = connObj.createStatement();
			
			sql = "SELECT * FROM employee_record;";
			ResultSet rs = stmt.executeQuery(sql);

			while(rs.next()){
			   Employee currentEmployee = new Employee();
			 
			   currentEmployee.setId(rs.getInt("employee_id"));
			   currentEmployee.setFirstName(rs.getString("employee_firstname"));
			   currentEmployee.setLastName(rs.getString("employee_lastname"));
			   currentEmployee.setCompany(rs.getString("employee_company"));
			   currentEmployee.setEmplNumber(rs.getString("employee_emplNumber"));
			   currentEmployee.setSalary(rs.getDouble("employee_salary"));

			   employeeList.add(currentEmployee);
			}
			rs.close();
			
			stmt.close();
			connObj.close();
			return employeeList;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error reading EMPLOYEES table!!");
			return employeeList;
		} finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      } catch(SQLException se2){
	      }
	      try{
	         if(connObj!=null)
	            connObj.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }	      
		
	}

    public String createEmployeeTable() {
    	Statement stmt = null;
		String sql;
		String result;
	      try {
		
	    	  Class.forName("com.mysql.jdbc.Driver");     
	            String db_url ="jdbc:mysql://localhost:3306/employee?useSSL=false",
	                    db_userName = "root",
	                    db_password = "rootsan";
	            connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
			
			System.out.println("Creating statement...");
			stmt = connObj.createStatement();
			
			DatabaseMetaData dbm = connObj.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "employee_record", null);
			
			if (tables.next()) {
				System.out.println("EMPLOYEES table already exists!!");
				result = "Existing";
			}
			else { 
			  
				System.out.println("Creating table ...");
						
				sql = "CREATE TABLE employee.employee_record " +
			    "(employee_id int NOT NULL AUTO_INCREMENT PRIMARY KEY)," + 
			    "employee_firstname VARCHAR(100)," + 
			    "employee_lastname VARCHAR(100)," + 
			    "employee_company VARCHAR(100)," +
			    "employee_emplNumber VARCHAR(100)," + 
			    "employee_salary)";	
				stmt.executeUpdate(sql);
				
				System.out.println("EMPLOYEES table created!!");
				result = "Created";
			}
			
			stmt.close();
			connObj.close();
			return result;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating EMPLOYEES table!!");
			return "Error";
		} finally{
	      try{
	         if(stmt!=null)
	            stmt.close();
	      } catch(SQLException se2){
	      }
	      try{
	         if(connObj!=null)
	            connObj.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }	      
	}
    
    public long insertEmployee(Employee employee){
		PreparedStatement preparedStatement = null;
		String sql;
		
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");     
	            String db_url ="jdbc:mysql://localhost:3306/employee?useSSL=false",
	                    db_userName = "root",
	                    db_password = "rootsan";
	            connObj = DriverManager.getConnection(db_url,db_userName,db_password); 
	            
			sql = "INSERT INTO EMPLOYEE.employee_record"
					+ "(employee_firstname,employee_lastname,employee_company,employee_emplNumber,employee_salary) VALUES"
					+ "(?,?,?,?,?)";
			
			System.out.println("Creating prepared statement...");
			preparedStatement = connObj.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getCompany());
			preparedStatement.setString(4, employee.getEmplNumber());
			preparedStatement.setDouble(5, employee.getSalary());

			Integer affectedRows = preparedStatement.executeUpdate();		
		
			Long idNewRow;
	        if (affectedRows == 0) {
	            throw new SQLException("Creating row failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	idNewRow = generatedKeys.getLong(1);
	            	System.out.println("Id of new object: " + idNewRow);
	            }
	            else {
	                throw new SQLException("Creating row failed, no ID obtained.");
	            }
	        }			
				
			preparedStatement.close();
			connObj.close();
			return idNewRow;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating row in EMPLOYEES table!!");
			return 0;
		} finally{
	      
	      try{
	         if(preparedStatement!=null)
	        	 preparedStatement.close();
	      } catch(SQLException se2){
	      }
	      try{
	         if(connObj!=null)
	            connObj.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }	      	
	}
    public long deleteEmployee(Employee employee){
    	
		PreparedStatement preparedStatement = null;
		String sql;
		
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");     
	            String db_url ="jdbc:mysql://localhost:3306/employee?useSSL=false",
	                    db_userName = "root",
	                    db_password = "rootsan";
	            connObj = DriverManager.getConnection(db_url,db_userName,db_password);

			sql = "DELETE FROM EMPLOYEE.employee_record WHERE employee_id = ?";
			
			System.out.println("Creating prepared statement...");
			preparedStatement = connObj.prepareStatement(sql);

			preparedStatement.setLong(1, employee.getId());
			
			Integer affectedRows = preparedStatement.executeUpdate();		
		
			
	        if (affectedRows == 0) {
	            throw new SQLException("Deleting row failed, no rows affected.");
	        } else {
	        	System.out.println("Object deleted!!");
	        }
			preparedStatement.close();
			connObj.close();
			return affectedRows;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error creating row in EMPLOYEES table!!");
			return 0;
		} finally{
	      try{
	         if(preparedStatement!=null)
	        	 preparedStatement.close();
	      } catch(SQLException se2){
	      }
	      try{
	         if(connObj!=null)
	            connObj.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }      
	}	
    
    public long updateEmployee(Employee employee){
		PreparedStatement preparedStatement = null;
		String sql;
		
	      try {
	    	  Class.forName("com.mysql.jdbc.Driver");     
	            String db_url ="jdbc:mysql://localhost:3306/employee?useSSL=false",
	                    db_userName = "root",
	                    db_password = "rootsan";
	            connObj = DriverManager.getConnection(db_url,db_userName,db_password);
			
			sql = "UPDATE EMPLOYEE.employee_record SET "
					+ "employee_firstname=?, employee_lastname=?, employee_company=?, employee_emplnumber=?, employee_salary=?"
					+ "WHERE employee_id=?";
			
			System.out.println("Creating prepared statement...");
			preparedStatement = connObj.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, employee.getFirstName());
			preparedStatement.setString(2, employee.getLastName());
			preparedStatement.setString(3, employee.getCompany());
			preparedStatement.setString(4, employee.getEmplNumber());
			preparedStatement.setDouble(5, employee.getSalary());
			preparedStatement.setLong(6, employee.getId());

			Integer affectedRows = preparedStatement.executeUpdate();		
		
			Long idNewRow;
	        if (affectedRows == 0) {
	            throw new SQLException("Updating row failed, no rows affected.");
	        }

	        try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	idNewRow = generatedKeys.getLong(1);
	            	System.out.println("Id of new object: " + idNewRow);
	            }
	            else {
	                throw new SQLException("Updating row failed, no ID obtained.");
	            }
	        }			
				
			preparedStatement.close();
			connObj.close();
			return idNewRow;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("Error updating row in EMPLOYEES table!!");
			return 0;
		} finally{
	      try{
	         if(preparedStatement!=null)
	        	 preparedStatement.close();
	      } catch(SQLException se2){
	      }
	      try{
	         if(connObj!=null)
	            connObj.close();
	      }catch(SQLException se){
	         se.printStackTrace();
	      }
	   }	      
		
	}
}

	

