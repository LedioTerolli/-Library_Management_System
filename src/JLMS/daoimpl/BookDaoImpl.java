package JLMS.daoimpl;

import java.sql.*;
import java.util.List;

import JLMS.model.Book;
import JLMS.dao.BookDao;

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
    public void create_book(Book book) {

    }

    @Override
    public void update_book(Book book) {

    }

    @Override
    public void delete_book(Book book) {

    }
}


