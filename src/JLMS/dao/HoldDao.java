package JLMS.dao;

import JLMS.model.Hold;

import java.sql.Date;
import java.util.List;

public interface HoldDao extends GenericDao  {
    Hold getByBookPatron(long book_id, String patron_username) throws Exception;

    List<Hold> getByPatron_username(String patron_username) throws Exception;

    List<Hold> getByBook_id(long book_id) throws Exception;
}
