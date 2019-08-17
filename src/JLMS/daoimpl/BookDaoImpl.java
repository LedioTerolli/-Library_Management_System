package JLMS.daoimpl;

import java.sql.*;
import java.util.List;

import JLMS.model.Book;
import JLMS.dao.BookDao;
import JLMS.DBConn;

public class BookDaoImpl implements BookDao {
    public BookDaoImpl() throws SQLException {
    }

    @Override
    public List<Book> get_all() {
        return null;
    }

    @Override
    public Book get_by_id(int id) {
        return null;
    }

    @Override
    public List<Book> get_by_title(String title) {
        return null;
    }

    @Override
    public List<Book> get_by_author(String author) {
        return null;
    }

    @Override
    public List<Book> get_by_category(String category) {
        return null;
    }

    @Override
    public List<Book> get_by_title_author(String title, String author) {
        return null;
    }

    @Override
    public List<Book> get_by_branch(String branch) {
        return null;
    }

    @Override
    public List<Book> get_by_available(boolean available) {
        return null;
    }

    @Override
    public void addBook(Book book) throws Exception {
        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?)")
        ) {
            statement.setLong(1,book.getId());
            statement.setString(2,book.getCover_url());
            statement.setString(3,book.getTitle());
            statement.setString(4,book.getAuthor());
            statement.setString(5,book.getCategory());
            statement.setInt(6,book.getBranch_id());
            statement.setBoolean(7,book.isAvailable());
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

            for (Book book: bookList) {
                System.out.println(book.toString());
                statement.setLong(1,book.getId());
                statement.setString(2,book.getCover_url());
                statement.setString(3,book.getTitle());
                statement.setString(4,book.getAuthor());
                statement.setString(5,book.getCategory());
                statement.setInt(6,book.getBranch_id());
                statement.setBoolean(7,book.isAvailable());
                statement.executeUpdate();
                conn.commit();
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        
    }

    @Override
    public void updateBook(Book book) {


    }

    @Override
    public void deleteBook(Book book) {

    }
}


