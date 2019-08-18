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

    @Override
    public Book get_by_id(long id) throws Exception {
        ResultSet rs;
        Book result = null;

        try (Connection conn = DBConn.getConnection();
             PreparedStatement statement = conn.prepareStatement("SELECT * FROM book WHERE id = ?")
        ) {
            conn.setAutoCommit(false);
            statement.setLong(1, id);
            statement.execute();
            rs = statement.getResultSet();
            if (!rs.isBeforeFirst()) {
                System.out.println("Book not found in database!");
                return result;
            } else {
                rs.next();
                result = new Book(
                        rs.getLong("id"),
                        rs.getString("cover_url"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("category"),
                        rs.getInt("branch_id"),
                        rs.getBoolean("available"));
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Book> get_all() throws Exception {
        List<Book> result = new ArrayList<>();
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
                return result;
            } else {
                while (rs.next()) {
                    currentBook = new Book(
                            rs.getLong("id"),
                            rs.getString("cover_url"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("branch_id"),
                            rs.getBoolean("available"));
                    result.add(currentBook);
                }
            }
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Book> get_by_title(String title) throws Exception {
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

            if(!rs.isBeforeFirst()) {
                System.out.println("No results");
            } else {
                while (rs.next()) {
                    currentBook = new Book(
                            rs.getLong("id"),
                            rs.getString("cover_url"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getString("category"),
                            rs.getInt("branch_id"),
                            rs.getBoolean("available")
                    );

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
            statement.setLong(1, book.getId());
            statement.setString(2, book.getCover_url());
            statement.setString(3, book.getTitle());
            statement.setString(4, book.getAuthor());
            statement.setString(5, book.getCategory());
            statement.setInt(6, book.getBranch_id());
            statement.setBoolean(7, book.isAvailable());
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

            int i = 1;

            for (Book book : bookList) {
                //System.out.println(book.toString());
                System.out.println(i + "/" + bookList.size());
                statement.setLong(1, book.getId());
                statement.setString(2, book.getCover_url());
                statement.setString(3, book.getTitle());
                statement.setString(4, book.getAuthor());
                statement.setString(5, book.getCategory());
                statement.setInt(6, book.getBranch_id());
                statement.setBoolean(7, book.isAvailable());
                statement.executeUpdate();
                conn.commit();
                i++;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    @Override
    public void updateBookId(Book book) {


    }

    @Override
    public void deleteBook(Book book) {

    }
}


