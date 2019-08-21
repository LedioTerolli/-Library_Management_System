package JLMS.daoimpl;

import JLMS.DBConn;
import JLMS.dao.PatronDao;
import JLMS.model.Patron;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatronDaoImpl implements PatronDao {
    public PatronDaoImpl() throws SQLException {
    }


    @Override
    public List getAll() throws Exception {
        List<Patron> patronList = new ArrayList<>();
        ResultSet rs;
        Patron currentPatron;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM patron")
        ) {
            conn.setAutoCommit(false);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found!");
                return patronList;
            } else {
                while (rs.next()) {
                    currentPatron = extractPatron(rs);
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
    public Patron getByUsername(String username) throws Exception {
        ResultSet rs;
        Patron foundPatron = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM PATRON WHERE username = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setString(1, username);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundPatron;
            } else {
                rs.next();
                foundPatron = extractPatron(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundPatron;
    }

    @Override
    public List<Patron> getByFirst_name(String first_name) throws Exception {
        ResultSet rs = null;
        Patron currentPatron = null;
        List<Patron> patronList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATRON WHERE first_name = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, first_name);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentPatron = extractPatron(rs);
                    patronList.add(currentPatron);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return patronList;
    }

    @Override
    public List<Patron> getByLast_name(String last_name) throws Exception {
        ResultSet rs = null;
        Patron currentPatron = null;
        List<Patron> patronList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATRON WHERE last_name = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, last_name);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentPatron = extractPatron(rs);
                    patronList.add(currentPatron);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return patronList;
    }

    @Override
    public List<Patron> getByEmail(String email) throws Exception {
        ResultSet rs = null;
        Patron currentPatron = null;
        List<Patron> patronList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATRON WHERE email = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, email);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentPatron = extractPatron(rs);
                    patronList.add(currentPatron);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return patronList;
    }

    @Override
    public List<Patron> getByDob(Date dob) throws Exception {
        ResultSet rs = null;
        Patron currentPatron = null;
        List<Patron> patronList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM PATRON WHERE dob = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setDate(1, dob);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentPatron = extractPatron(rs);
                    patronList.add(currentPatron);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return patronList;
    }

    //----------------------- ADD ---------------------------------------------------------------

    @Override
    public void add(Object objPatron) throws Exception {
        Patron patron = (Patron) objPatron;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO PATRON VALUES(?,?,?,?,?,?)")
        ) {
            conn.setAutoCommit(false);
            buildStatement(patron, statement);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- UPDATE -------------------------------------------------------------

    @Override
    public void update(Object objPatron) throws Exception {

        Patron patron = (Patron) objPatron;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE PATRON SET " +
                             "USERNAME = ?, " +
                             "FIRST_NAME = ?, " +
                             "LAST_NAME = ?, " +
                             "EMAIL = ?, " +
                             "PASSWORD = ?, " +
                             "DOB = ? " +
                             "WHERE USERNAME = ? "
             )
        ) {
            conn.setAutoCommit(false);
            buildStatement(patron, statement);
            statement.setString(7, patron.getUsername());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //----------------------- DELETE -------------------------------------------------------------

    @Override
    public void delete(Object objPatron) throws Exception {
        Patron patron = (Patron) objPatron;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM PATRON WHERE USERNAME = ?")
        ) {
            statement.setString(1, patron.getUsername());
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Patron patron, PreparedStatement statement) throws SQLException {
        statement.setString(1, patron.getUsername());
        statement.setString(2, patron.getFirst_name());
        statement.setString(3, patron.getLast_name());
        statement.setString(4, patron.getEmail());
        statement.setString(5, patron.getPassword());
        statement.setDate(6, patron.getDob());

    }

    private Patron extractPatron(ResultSet rs) throws SQLException {
        Patron patron = new Patron(
                rs.getString("username"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("password"),
                rs.getDate("dob"));
        return patron;
    }

}
