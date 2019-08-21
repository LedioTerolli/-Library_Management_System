package JLMS.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import JLMS.model.Book;
import JLMS.dao.BookDao;
import JLMS.DBConn;

public class BookDaoImpl implements BookDao {
    public BookDaoImpl() throws SQLException {
    }

    //----------------------- GET ----------------------------------------------------------------

    @Override
    public List getAll() throws Exception {
        List<Book> bookList = new ArrayList<>();
        ResultSet rs;
        Book currentBook;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM book")
        ) {
            conn.setAutoCommit(false);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No data found!");
                return bookList;
            } else {
                while (rs.next()) {
                    currentBook = extractBook(rs);
                    bookList.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public Book getByID(long id) throws Exception {
        ResultSet rs;
        Book foundBook = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM book WHERE id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setLong(1, id);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
                return foundBook;
            } else {
                rs.next();
                foundBook = extractBook(rs);
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return foundBook;
    }


    @Override
    public List<Book> getByTitle(String title) throws Exception {
        ResultSet rs = null;
        Book currentBook = null;
        List<Book> bookList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE title = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, title);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentBook = extractBook(rs);
                    bookList.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;
    }

    @Override
    public List<Book> getByAuthor(String author) throws Exception {
        ResultSet rs;
        Book currentBook;
        List<Book> bookList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE author = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, author);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentBook = extractBook(rs);
                    bookList.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;
    }

    @Override
    public List<Book> getByCategory(String category) throws Exception {
        ResultSet rs;
        Book currentBook;
        List<Book> bookList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE category = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, category);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentBook = extractBook(rs);
                    bookList.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;
    }

    @Override
    public List<Book> getByBranch(String branch) throws Exception {
        ResultSet rs;
        Book currentBook;
        List<Book> bookList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE branch_id = ?")
        ) {
            conn.setAutoCommit(false);
            ps.setString(1, branch);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentBook = extractBook(rs);
                    bookList.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;
    }

    @Override
    public List<Book> getByAvailable() throws Exception {
        ResultSet rs;
        Book currentBook;
        List<Book> bookList = new ArrayList<>();
        try (
                Connection conn = DBConn.getConnection();
                PreparedStatement ps = conn.prepareStatement("SELECT * FROM book WHERE available = true")
        ) {
            conn.setAutoCommit(false);
            ps.execute();
            rs = ps.getResultSet();

            if (!rs.isBeforeFirst()) {
                System.out.println("No results.");
            } else {
                while (rs.next()) {
                    currentBook = extractBook(rs);
                    bookList.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return bookList;
    }


    //----------------------- ADD ---------------------------------------------------------------

    @Override
    public void add(Object objBook) throws Exception {
        Book book = (Book) objBook;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?)")
        ) {
            conn.setAutoCommit(false);
            buildStatement(book, statement);
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void addBookList(List<Book> bookList) throws Exception {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?)")
        ) {
            conn.setAutoCommit(false);
            int counter = 1;
            int bookListSize = bookList.size();

            for (Book book : bookList) {
                System.out.println(counter + "/" + bookListSize);
                buildStatement(book, statement);
                statement.executeUpdate();
                conn.commit();
                counter++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    //----------------------- UPDATE -------------------------------------------------------------

    @Override
    public void update(Object objBook) throws Exception {
        Book book = (Book) objBook;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement(
                     "UPDATE BOOK SET " +
                             "ID = ?, " +
                             "COVER_URL = ?, " +
                             "TITLE = ?, " +
                             "AUTHOR = ?, " +
                             "CATEGORY = ?, " +
                             "BRANCH_ID = ?, " +
                             "AVAILABLE = ? " +
                             "WHERE ID = ? "
             )
        ) {
            conn.setAutoCommit(false);
            buildStatement(book, statement);
            statement.setLong(8, book.getId());
            statement.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //----------------------- DELETE -------------------------------------------------------------

    @Override
    public void delete(Object objBook) throws Exception {
        Book book = (Book) objBook;
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("DELETE FROM BOOK WHERE ID = ?")
        ) {
            statement.setLong(1, book.getId());
            conn.commit();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //----------------------- HELPER METHODS -----------------------------------------------------

    private void buildStatement(Book book, PreparedStatement statement) throws SQLException {
        statement.setLong(1, book.getId());
        statement.setString(2, book.getCover_url());
        statement.setString(3, book.getTitle());
        statement.setString(4, book.getAuthor());
        statement.setString(5, book.getCategory());
        statement.setInt(6, book.getBranch_id());
        statement.setBoolean(7, book.isAvailable());
    }

    private Book extractBook(ResultSet rs) throws SQLException {
        Book book = new Book(
                rs.getLong("id"),
                rs.getString("cover_url"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getString("category"),
                rs.getInt("branch_id"),
                rs.getBoolean("available"));
        return book;
    }
}


