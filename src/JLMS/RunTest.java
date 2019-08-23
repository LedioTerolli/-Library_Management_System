package JLMS;

import JLMS.dao.BookDao;
import JLMS.dao.EmployeeDao;
import JLMS.dao.PatronDao;
import JLMS.daoimpl.*;
import JLMS.model.Book;
import JLMS.model.Employee;
import JLMS.model.Patron;
import misc.CreateBookList;

import java.text.SimpleDateFormat;
import java.util.*;
import java.time.*;

public class RunTest {
    public static void main(String[] args) throws Exception {
        long startTime = System.nanoTime();
/*
        ------------------------ADD BOOK
        BookDao dune = new BookDaoImpl();
        dune.add(new Book(44101359, "", "Dune", "Frank Herbert", "Science fiction", 1, true ));

        ------------------------ADD BOOK LIST
        List<Book> bookList = CreateBookList.createList("new_book");
        BookDao listOfBooks = new BookDaoImpl();
        listOfBooks.addBookList(bookList);

        -------------------------GET BY ID
        BookDao getById = new BookDaoImpl();
        Book result = getById.get_by_id(72628335);
        System.out.println(result.toString());

        ------------------------GET ALL BOOKS
        Set<Book> bookSet = new HashSet<>(bookList);
        BookDao getAllBooks = new BookDaoImpl();
        List<Book> result = getAllBooks.get_all();
        for (Book e: result) {
            if (bookList.contains(e)) System.out.println("not found");
        }

        -------------------------GET BY TITLE
        BookDao getTitle = new BookDaoImpl();
        for (Book e: getTitle.get_by_title("Seeing")) {
            System.out.println(e.toString());
        }

        -------------------------ADD UPDATE DELETE
        BookDao getAvailable = new BookDaoImpl();
        getAvailable.update(new Book(44101359, "", "Dune", "Frank Herbert", "Science fiction", 2, true));
        System.out.println(getAvailable.getByID(44101359).toString());

        -------------------------GET ALL PATRON

        PatronDao getall = new PatronDaoImpl();
        List<Patron> result = getall.getAll();
        for (Patron e: result) {
            System.out.println(e);
        }


        PatronDao patronDao = new PatronDaoImpl();
        List<Patron> result = patronDao.getByFirst_name("Ashley");
        for (Patron e: result) {
            System.out.println(e);
        }


        List<Employee> list =  new EmployeeDaoImpl().getBySuper_id(2);
        for (Employee e: list) System.out.println(e);




        LocalDate date2 = LocalDate.of(1998, 7, 30);
        //new EmployeeDaoImpl().add(new Employee(11,"Jim", "Halpert", date2, "M", 80000, 2,101));

        new EmployeeDaoImpl().delete(new Employee(11,"Jim", "Halpert", date2, "M", 80000, 2,101));
*/

        System.out.println(new EmployeeDaoImpl().getAge(1));

        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        double ftime = totalTime/1000000000.0;
        System.out.println(ftime + "sec.");

    }

    public void listStats(List<Book> bookList) {
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

            if (current < 50) less++;
            else more++;

            sum += current;
            i++;
        }

        System.out.println(max);
        System.out.println("less: " + less);
        System.out.println("more: " + more);
        System.out.println("average: " + sum * 1.0 / i);
    }
}
