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
    public EmployeeDaoImpl() {
    }

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
        return patronList;
    }

    @Override
    public Employee getByEmp_id(int emp_id) throws Exception {
        ResultSet rs;
        Employee foundEmployee = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE emp_id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setInt(1, emp_id);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundEmployee;
            } else {
                rs.next();
                foundEmployee = extractEmployee(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundEmployee;
    }

    @Override
    public List<Employee> getByFull_name(String first_name, String last_name) throws Exception {
        ResultSet rs = null;
        Employee currentEmployee = null;
        List<Employee> employeeList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE first_name = ? AND last_name = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, first_name);
            ps.setString(2, last_name);

            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentEmployee = extractEmployee(rs);
                    employeeList.add(currentEmployee);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return employeeList;
    }

    @Override
    public List<Employee> getBySuper_id(int super_id) throws Exception {
        ResultSet rs = null;
        Employee currentEmployee = null;
        List<Employee> employeeList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE super_id = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setInt(1, super_id);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentEmployee = extractEmployee(rs);
                    employeeList.add(currentEmployee);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return employeeList;
    }

    @Override
    public List<Employee> getByBranch_id(int branch_id) throws Exception {
        ResultSet rs = null;
        Employee currentEmployee = null;
        List<Employee> employeeList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM EMPLOYEE WHERE branch_id = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setInt(1, branch_id);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentEmployee = extractEmployee(rs);
                    employeeList.add(currentEmployee);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return employeeList;    }


    //----------------------- ADD ---------------------------------------------------------------

    @Override
    public void add(Object objEmployee) throws Exception {
        Employee employee = (Employee) objEmployee;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO EMPLOYEE VALUES(?,?,?,?,?,?,?,?)")
        ) {
            conn.setAutoCommit(false);
            buildStatement(employee, statement);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- UPDATE -------------------------------------------------------------

    @Override
    public void update(Object objEmployee) throws Exception {
        Employee employee = (Employee) objEmployee;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE EMPLOYEE SET " +
                             "EMP_ID = ?, " +
                             "FIRST_NAME = ?, " +
                             "LAST_NAME = ?, " +
                             "DOB = ?, " +
                             "SEX = ?, " +
                             "SALARY = ?, " +
                             "SUPER_ID = ?, " +
                             "BRANCH_ID = ? " +
                             "WHERE EMP_ID = ? "
             )
        ) {
            conn.setAutoCommit(false);
            buildStatement(employee, statement);
            statement.setInt(9, employee.getEmp_id());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------- DELETE -------------------------------------------------------------

    @Override
    public void delete(Object objEmployee) throws Exception {
        Employee employee = (Employee) objEmployee;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE emp_id = ?")
        ) {
            statement.setInt(1, employee.getEmp_id());
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Employee employee, PreparedStatement statement) throws SQLException {
        statement.setInt(1, employee.getEmp_id());
        statement.setString(2, employee.getFirst_name());
        statement.setString(3, employee.getLast_name());
        statement.setDate(4, employee.getDob());
        statement.setString(5, employee.getSex());
        statement.setInt(6, employee.getSalary());
        statement.setInt(7, employee.getSuper_id());
        statement.setInt(8, employee.getBranch_id());


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

