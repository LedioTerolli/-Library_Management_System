package JLMS.dao;

import JLMS.model.Loan;

import java.time.LocalDate;
import java.util.List;

public interface LoanDao extends GenericDao {
    Loan getByBook_id(int book_id) throws Exception;

    List<Loan> getByPatron_username(String patron_username) throws Exception;

    List<Loan> getByDue_date(LocalDate due_date) throws Exception;

    void updateAllFine() throws Exception;
}
