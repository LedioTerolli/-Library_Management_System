package JLMS.dao;

import JLMS.model.Hold;

import java.sql.Date;
import java.util.List;

public interface HoldDao extends GenericDao  {
    Hold getByBookPatron(int book_id, String patron_username) throws Exception;

    List<Hold> getByPatron_username(String patron_username) throws Exception;

    List<Hold> getByBook_id(int book_id) throws Exception;
}
