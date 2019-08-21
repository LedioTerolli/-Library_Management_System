package JLMS.dao;

import JLMS.model.Patron;

import java.sql.Date;
import java.util.List;

public interface PatronDao extends GenericDao {

    Patron getByUsername(String username) throws Exception;

    List<Patron> getByFirst_name(String first_name) throws Exception;

    List<Patron> getByLast_name(String last_name) throws Exception;

    List<Patron> getByEmail(String email) throws Exception;

    List<Patron> getByDob(Date dob) throws Exception;

}