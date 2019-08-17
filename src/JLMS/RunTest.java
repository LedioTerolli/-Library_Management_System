package JLMS;
import JLMS.dao.BookDao;
import JLMS.daoimpl.*;
import JLMS.model.Book;
import misc.CreateBookList;

import java.util.*;

public class RunTest {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();

        //BookDao dune = new BookDaoImpl();
        //dune.addBook(new Book(44101359, "", "Dune", "Frank Herbert", "Science fiction", 1, true ));

        //List<Book> bookList = CreateBookList.createList("new_book");
        //BookDao listOfBooks = new BookDaoImpl();
        //listOfBooks.addBookList(bookList);

        //BookDao getById = new BookDaoImpl();
        //Book result = getById.get_by_id(72628331);
        //System.out.println(result.toString());

        //Set<Book> bookSet = new HashSet<>(bookList);
        //BookDao getAllBooks = new BookDaoImpl();
        //List<Book> result = getAllBooks.get_all();
        //for (Book e: result) {
        //    if (bookList.contains(e)) System.out.println("not found");
        //}

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }

    public void listStats (List<Book> bookList) {
        int i = 0;
        int max = 0;
        int sum = 0;
        int less = 0;
        int more = 0;

        while (i < bookList.size()) {
            int current = bookList.get(i).getTitle().length();

            if (max <= current) {
                max = current;
            }

            if( current < 50) less++;
            else more++;

            sum += current;
            i++;
        }

        System.out.println(max);
        System.out.println("less: " + less);
        System.out.println("more: " + more);
        System.out.println("average: " + sum*1.0/i);
    }
}
