package JLMS.dao;

import JLMS.model.*;
import java.util.List;

public interface BookDao {
    List<Book> get_all();
    Book get_by_id (int id);
    List<Book> get_by_title (String title);
    List<Book> get_by_author (String author);
    List<Book> get_by_category (String category);
    List<Book> get_by_title_author (String title, String author);
    List<Book> get_by_branch (String branch);
    List<Book> get_by_available (boolean available);
    void addBook(Book book) throws Exception;
    void addBookList(List<Book> bookList) throws Exception;
    void updateBook(Book book);
    void deleteBook(Book book);
}
