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
    public HoldDaoImpl() {
    }

    @Override
    public List getAll() throws Exception {


        List<Hold> holdList = new ArrayList<>();
        ResultSet rs;
        Hold currentHold;

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
                    currentHold = extractHold(rs);
                    holdList.add(currentHold);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return holdList;
    }

    @Override
    public Hold getByBookPatron(long book_id, String patron_username) throws Exception {
        ResultSet rs;
        Hold foundHold = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM HOLD WHERE book_id = ? AND patron_username = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setLong(1, book_id);
            statement.setString(2, patron_username);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundHold;
            } else {
                rs.next();
                foundHold = extractHold(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundHold;
    }

    @Override
    public List<Hold> getByPatron_username(String patron_username) throws Exception {
        ResultSet rs = null;
        Hold currentHold = null;
        List<Hold> holdList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM HOLD WHERE patron_username = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, patron_username);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentHold = extractHold(rs);
                    holdList.add(currentHold);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return holdList;

    }

    @Override
    public List<Hold> getByBook_id(long book_id) throws Exception {
        ResultSet rs = null;
        Hold currentHold = null;
        List<Hold> holdList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM HOLD WHERE book_id = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setLong(1, book_id);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentHold = extractHold(rs);
                    holdList.add(currentHold);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return holdList;
    }

    //----------------------- ADD ---------------------------------------------------------------

    @Override
    public void add(Object objHold) throws Exception {
        Hold hold = (Hold) objHold;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO HOLD VALUES(?,?,?)")
        ) {
            conn.setAutoCommit(false);
            buildStatement(hold, statement);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- UPDATE -------------------------------------------------------------

    @Override
    public void update(Object objHold) throws Exception {
        Hold hold = (Hold) objHold;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE EMPLOYEE SET " +
                             "BOOK_ID = ?, " +
                             "PATRON_USERNAME = ?, " +
                             "START_DATE = ?, " +
                             "WHERE BOOK_ID = ? "
             )
        ) {
            conn.setAutoCommit(false);
            buildStatement(hold, statement);
            statement.setLong(4, hold.getBook_id());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------- DELETE -------------------------------------------------------------

    @Override
    public void delete(Object objHold) throws Exception {
        Hold hold = (Hold) objHold;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM HOLD WHERE book_id = ? and patron_username = ? ")
        ) {
            statement.setLong(1, hold.getBook_id());
            statement.setString(2, hold.getPatron_username());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
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
