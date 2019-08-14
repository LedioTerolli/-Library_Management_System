import com.sun.xml.internal.ws.api.ha.StickyFeature;

import java.io.*;
import java.util.Arrays;

public class Change_book_file {
    public static void main(String[] args) {
        change_file("old_book", "new_book");
    }

    public static void change_file(String old_file, String new_file) {
        String line;
        BufferedReader reader = null;
        int[] interest_index = {0, 2, 3, 4, 6};
        int nr_copies;

        try {
            File file = new File(old_file + ".txt");
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                String sub_str = line.substring(1, line.length() - 1);
                String[] book_parts = sub_str.split("\",\"");
                nr_copies = (int) (Math.random() * 5) + 1;

                for (int i = 1; i <= nr_copies; i++) {
                    StringBuilder str_b = new StringBuilder(); // outputting the serialized tree in this string
                    str_b.append("\"");
                    String[] book_parts_copy = new String[book_parts.length];
                    for (int j = 0; j < book_parts.length; j++) book_parts_copy[j] = book_parts[j];
                    book_parts_copy[0] += Integer.toString(i);

                    for (int j = 0; j < interest_index.length; j++) {
                        str_b.append(book_parts_copy[interest_index[j]]);
                        if (j < interest_index.length - 1) str_b.append("\",\"");
                        else str_b.append("\"");
                    }
                    append_to_file(new_file, str_b);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void append_to_file(String file, StringBuilder text_append) throws IOException {
        String filename = file + ".txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
        writer.newLine();
        writer.write(text_append.toString());
        writer.close();
    }
}
