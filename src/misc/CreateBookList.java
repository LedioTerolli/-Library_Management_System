package misc;

import JLMS.model.Book;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CreateBookList {
    public static List<Book> createList(String file) throws IOException {
        String line;
        List<Book> bookList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file + ".txt"))) {
            int random_branch_no;

            while ((line = reader.readLine()) != null) {
                String sub_str = line.substring(1, line.length() - 1);
                String[] book_parts = sub_str.split("\",\"");
                random_branch_no = (int) (Math.random() * 3) + 101;

                Book newBook = new Book(
                        Long.parseLong(book_parts[0]),
                        book_parts[1],
                        book_parts[2],
                        book_parts[3],
                        book_parts[4],
                        random_branch_no,
                        true);

                bookList.add(newBook);
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return bookList;
    }
}
