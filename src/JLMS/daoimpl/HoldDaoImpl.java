package JLMS.daoimpl;

import JLMS.DBConn;
import JLMS.dao.HoldDao;
import JLMS.model.Hold;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoldDaoImpl implements HoldDao {
    public HoldDaoImpl(){}

    @Override
    public List getAll() throws Exception {


        List<Hold> holdList = new ArrayList<>();
        ResultSet rs;
        Hold currentEmployee;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM hold")
        ) {
            conn.setAutoCommit(false);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found!");
                return holdList;
            } else {
                while (rs.next()) {
                    currentEmployee = extractEmployee(rs);
                    holdList.add(currentEmployee);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holdList;
    }

    @Override
    public Hold getByBookPatron(int book_id, String patron_username) throws Exception {
        return null;
    }

    @Override
    public List<Hold> getByPatron_username(String patron_username) throws Exception {
        return null;
    }

    @Override
    public List<Hold> getByBook_id(int book_id) throws Exception {
        return null;
    }

    @Override
    public void add(Object objHold) throws Exception {

    }

    @Override
    public void update(Object objHold) throws Exception {

    }

    @Override
    public void delete(Object objHold) throws Exception {

    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Hold hold, PreparedStatement statement) throws SQLException {
        statement.setLong(1, hold.getBook_id());
        statement.setString(2, hold.getPatron_username());
        statement.setDate(3, hold.getStart_date());
    }

    private Hold extractHold(ResultSet rs) throws SQLException {
        Hold hold = new Hold(
                rs.getLong("book_id"),
                rs.getString("patron_username"),
                rs.getDate("start_date")
        );

        return hold;
    }
}
