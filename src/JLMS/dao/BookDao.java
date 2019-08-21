package JLMS.dao;

import JLMS.model.Book;

import java.util.List;

public interface BookDao extends GenericDao {

    Book getByID(long id) throws Exception;

    List<Book> getByTitle(String title) throws Exception;

    List<Book> getByAuthor(String author) throws Exception;

    List<Book> getByCategory(String category) throws Exception;

    List<Book> getByBranch(String branch) throws Exception;

    List<Book> getByAvailable() throws Exception;

    void addBookList(List<Book> bookList) throws Exception;

}
