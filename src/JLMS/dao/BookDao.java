package JLMS.dao;

import JLMS.model.*;
import java.util.List;

public interface BookDao {

    Book get_by_id (long id) throws Exception;
    List<Book> get_all() throws Exception;
    List<Book> get_by_title (String title) throws Exception;
    List<Book> get_by_author (String author);
    List<Book> get_by_category (String category);
    List<Book> get_by_title_author (String title, String author);
    List<Book> get_by_branch (String branch);
    List<Book> get_by_available (boolean available);
    void addBook(Book book) throws Exception;
    void addBookList(List<Book> bookList) throws Exception;

    void updateBookId(Book book);
    void deleteBook(Book book);
}
