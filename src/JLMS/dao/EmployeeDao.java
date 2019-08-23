package JLMS.dao;

import JLMS.model.Employee;

import java.util.List;

public interface EmployeeDao extends GenericDao {

    Employee getByEmp_id(int emp_id) throws Exception;

    List<Employee> getByFull_name(String first_name, String last_name) throws Exception;

    List<Employee> getBySuper_id(int super_id) throws Exception;

    List<Employee> getByBranch_id(int branch_id) throws Exception;

    int getAge (int emp_id) throws Exception;
}
