package application.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import application.util.DBUtil;
 
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO {
	//*******************************
    //SELECT an Employee
    //*******************************
    public static Customer searchEmployee (String empId) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
    	
//--        String selectStmt = "SELECT * FROM employees WHERE employee_id="+empId;
    	String selectStmt = "SELECT * FROM customer WHERE "+empId;
    	
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmp = DBUtil.dbExecuteQuery(selectStmt);
 
            //Send ResultSet to the getEmployeeFromResultSet method and get employee object
            Customer employee = getEmployeeFromResultSet(rsEmp);
 
            //Return employee object
            return employee;
        } catch (SQLException e) {
            System.out.println("While searching an employee with " + empId + " id, an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
 
    //Use ResultSet from DB as parameter and set Employee Object's attributes and return employee object.
    private static Customer getEmployeeFromResultSet(ResultSet rs) throws SQLException
    {
    	Customer emp = null;
        if (rs.next()) {
            emp = new Customer();
            emp.setCusId(rs.getString("cusid"));
            emp.setCusName(rs.getString("cusname"));
            emp.setAge(rs.getInt("age"));
            emp.setRank(rs.getString("rank"));
            emp.setJob(rs.getString("job"));
            emp.setReserve(rs.getInt("reserve"));

           // empList.add(emp);
            //여기에는 List.add 가 없넹..?
        }
        return emp;
    }
 
    //*******************************
    //SELECT Employees
    //*******************************
    //-- Controller와 연결되어 있고,  SELECT-FROM문을 담당.
    public static ObservableList<Customer> searchEmployees (String sql) throws SQLException, ClassNotFoundException {
        //Declare a SELECT statement
        if(!sql.equals(""))
            sql = "WHERE " + sql;
        String selectStmt = "SELECT * FROM customer " + sql;

        System.out.println(sql);

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsEmps = DBUtil.dbExecuteQuery(selectStmt);
 
           //Send ResultSet to the getEmployeeList method and get employee object
            ObservableList<Customer> empList = getEmployeeList(rsEmps);
 
            //Return employee object
            return empList;
        } catch (SQLException e) {
            System.out.println("SQL select operation has been failed: " + e);
            //Return exception
            throw e;
        }
    }

    // -- 위의 searchEmployees 에 속해있음.
    //Select * from employees operation
    private static ObservableList<Customer> getEmployeeList(ResultSet rs) throws SQLException, ClassNotFoundException {
        //Declare a observable List which comprises of Employee objects
        ObservableList<Customer> empList = FXCollections.observableArrayList();
 
        while (rs.next()) {
        	Customer emp = new Customer();
            emp.setCusId(rs.getString("cusid"));
            emp.setCusName(rs.getString("cusname"));
            emp.setAge(rs.getInt("age"));
            emp.setRank(rs.getString("rank"));
            emp.setJob(rs.getString("job"));
            emp.setReserve(rs.getInt("reserve"));

            //아래는 콘솔창에만 나옴.
            System.out.println(rs.getString("cusid") + "  " + rs.getString("cusname"));
            //Add employee to the ObservableList
            empList.add(emp);
        }
        //아래 db연결을 끊으면 에러남. dbExecuteQuery 왜일까...?
      //  DBUtil.dbDisconnect();      //이것도 여기에만 있다.
        //return empList (ObservableList of Employees)
        return empList;
    }
 
    //*************************************
    //UPDATE an employee's email address
    //*************************************
    public static void updateEmpEmail (String empId, String empEmail) throws SQLException, ClassNotFoundException {
        //Declare a UPDATE statement
        String updateStmt =
                "BEGIN\n" +
                        "   UPDATE employees\n" +
                        "      SET EMAIL = '" + empEmail + "'\n" +
                        "    WHERE EMPLOYEE_ID = " + empId + ";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
 
    //*************************************
    //DELETE an employee
    //*************************************
    public static void deleteEmpWithId (String empId) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "   DELETE FROM employees\n" +
                        "         WHERE employee_id ="+ empId +";\n" +
                        "   COMMIT;\n" +
                        "END;";
 
        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
 
    //*************************************
    //INSERT an employee
    //*************************************
    public static void insertEmp (String name, String lastname, String email) throws SQLException, ClassNotFoundException {
        //Declare a DELETE statement
        String updateStmt =
                "BEGIN\n" +
                        "INSERT INTO employees\n" +
                        "(EMPLOYEE_ID, FIRST_NAME, LAST_NAME, EMAIL, HIRE_DATE, JOB_ID)\n" +
                        "VALUES\n" +
                        "(sequence_employee.nextval, '"+name+"', '"+lastname+"','"+email+"', SYSDATE, 'IT_PROG');\n" +
                        "END;";
 
        //Execute DELETE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
