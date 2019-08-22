package JLMS.dao;

import JLMS.model.Loan;

import java.sql.Date;
import java.util.List;

public interface LoanDao extends GenericDao {
    Loan getByBook_id(int book_id) throws Exception;

    List<Loan> getByPatron_username(String patron_username) throws Exception;

    List<Loan> getByDue_date(Date due_date) throws Exception;
}
