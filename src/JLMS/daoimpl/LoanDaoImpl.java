package JLMS.daoimpl;

import JLMS.DBConn;
import JLMS.dao.LoanDao;
import JLMS.model.Loan;

import java.sql.*;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.*;

import java.util.ArrayList;
import java.util.List;

public class LoanDaoImpl implements LoanDao {
    public LoanDaoImpl() {
    }

    //----------------------- GET ----------------------------------------------------------------

    @Override
    public List getAll() throws Exception {

        List<Loan> loanList = new ArrayList<>();
        ResultSet rs;
        Loan currentPatron;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM loan")
        ) {
            conn.setAutoCommit(false);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found!");
                return loanList;
            } else {
                while (rs.next()) {
                    currentPatron = extractLoan(rs);
                    loanList.add(currentPatron);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loanList;
    }

    @Override
    public Loan getByBook_id(int book_id) throws Exception {
        ResultSet rs;
        Loan foundLoan = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM LOAN WHERE book_id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setInt(1, book_id);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundLoan;
            } else {
                rs.next();
                foundLoan = extractLoan(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundLoan;
    }

    @Override
    public List<Loan> getByPatron_username(String patron_username) throws Exception {
        ResultSet rs = null;
        Loan currentLoan = null;
        List<Loan> loanList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM LOAN WHERE patron_username = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, patron_username);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentLoan = extractLoan(rs);
                    loanList.add(currentLoan);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return loanList;
    }

    @Override
    public List<Loan> getByDue_date(LocalDate due_date) throws Exception {

        ResultSet rs;
        Loan currentLoan = null;
        List<Loan> loanList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM LOAN WHERE due_date = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setObject(1, due_date);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentLoan = extractLoan(rs);
                    loanList.add(currentLoan);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return loanList;
    }

    //----------------------- ADD ---------------------------------------------------------------

    @Override
    public void add(Object objLoan) throws Exception {
        Loan loan = (Loan) objLoan;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO LOAN VALUES(?,?,?,?,?)")
        ) {
            conn.setAutoCommit(false);
            buildStatement(loan, statement);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------- UPDATE -------------------------------------------------------------

    @Override
    public void update(Object objLoan) throws Exception {
        Loan loan = (Loan) objLoan;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE LOAN SET " +
                             "BOOK_ID = ?, " +
                             "PATRON_USERNAME = ?, " +
                             "START_DATE = ?, " +
                             "DUE_DATE = ?, " +
                             "FINE = ? " +
                             "WHERE BOOK_ID = ? "
             )
        ) {
            conn.setAutoCommit(false);
            buildStatement(loan, statement);
            statement.setInt(6, loan.getBook_id());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateAllFine() throws Exception {
        ResultSet rs;
        double fine = 0;
        try (Connection conn = DBConn.getConnection();
             Statement statementFine = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
        ) {
            conn.setAutoCommit(false);
            rs = statementFine.executeQuery("SELECT * FROM LOAN");
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    LocalDate due_date = rs.getDate("due_date").toLocalDate();
                    LocalDate today = LocalDate.now();
                    fine =  DAYS.between(due_date, today) * 0.1;
                    rs.updateDouble("fine", fine);
                    rs.updateRow();
                }
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------- DELETE -------------------------------------------------------------

    @Override
    public void delete(Object objLoan) throws Exception {
        Loan loan = (Loan) objLoan;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM LOAN WHERE book_id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setInt(1, loan.getBook_id());
            int rows_affected = statement.executeUpdate();
            if (rows_affected == 0) System.out.println("Not found!");
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Loan loan, PreparedStatement statement) throws SQLException {


        statement.setInt(1, loan.getBook_id());
        statement.setString(2, loan.getPatron_username());
        statement.setObject(3, loan.getStart_date());
        statement.setObject(4, loan.getDue_date());
        statement.setDouble(5, loan.getFine());
    }

    private Loan extractLoan(ResultSet rs) throws SQLException {
        Loan loan = new Loan(
                rs.getInt("book_id"),
                rs.getString("patron_username"),
                rs.getDate("start_date").toLocalDate(),
                rs.getDate("due_date").toLocalDate(),
                rs.getInt("fine"));
        return loan;
    }


}
