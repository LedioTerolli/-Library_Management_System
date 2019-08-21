package JLMS.daoimpl;

import JLMS.DBConn;
import JLMS.dao.EmployeeDao;
import JLMS.model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    public EmployeeDaoImpl(){}

    @Override
    public List getAll() throws Exception {
        List<Employee> patronList = new ArrayList<>();
        ResultSet rs;
        Employee currentPatron;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM employee")
        ) {
            conn.setAutoCommit(false);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found!");
                return patronList;
            } else {
                while (rs.next()) {
                    currentPatron = extractEmployee(rs);
                    patronList.add(currentPatron);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return patronList;    }

    @Override
    public void add(Object o) throws Exception {

    }

    @Override
    public void update(Object o) throws Exception {

    }

    @Override
    public void delete(Object o) throws Exception {

    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Employee employee, PreparedStatement statement) throws SQLException {
        statement.setInt(1, employee.getEmp_id());
        statement.setString(2, employee.getFirst_name());
        statement.setString(3, employee.getLast_name());
        statement.setDate(4, employee.getDob());
        statement.setString(5, employee.getSex());
        statement.setInt(6, employee.getSalary());
        statement.setInt(7,employee.getSuper_id());
        statement.setInt(8,employee.getBranch_id());


    }

    private Employee extractEmployee(ResultSet rs) throws SQLException {
        Employee employee = new Employee(
                rs.getInt("emp_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getDate("dob"),
                rs.getString("sex"),
                rs.getInt("salary"),
                rs.getInt("super_id"),
                rs.getInt("branch_id"));
        return employee;
    }

}

