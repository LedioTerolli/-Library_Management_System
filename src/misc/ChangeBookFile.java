package misc;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class ChangeBookFile {
    public static void main(String[] args) {
        change_file("old_book", "new_book");
        //boolean check = check_unique(store_book_id("new_book"));
        //System.out.println(check);
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
                if (book_parts.length != 7) continue;
                if (check_for_letters(book_parts[0])) continue;
                if (book_parts[3].length() > 100) continue;

                nr_copies = (int) (Math.random() * 5) + 1;

                for (int i = 1; i <= nr_copies; i++) {
                    StringBuilder str_b = new StringBuilder(); // outputting the serialized tree in this string
                    str_b.append("\"");
                    String[] book_parts_copy = book_parts.clone();
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

    public static boolean check_for_letters(String id) {
        for (int i = 0; i < id.length(); i++) {
            char current = id.charAt(i);
            int y = Character.getNumericValue(current);
            if (y > 9) return true;
        }
        return false;
    }

    public static boolean check_unique(List<String> id_list) {
        HashMap<String, Boolean> mapChar = new HashMap<>();
        ListIterator a = id_list.listIterator();

        for (int i = 0; i < id_list.size(); i++) {
            String current = (String) a.next();
            System.out.println(current);
            Boolean flag = mapChar.get(current);
            if (flag == null)
                mapChar.put(current, true);
            else {
                return false;
            }
        }
        return true;
    }

    public static List<String> store_book_id(String new_book) {

        String line;
        BufferedReader reader = null;

        List<String> id_list = new LinkedList<>();

        try {
            File file = new File(new_book + ".txt");
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                String sub_str = line.substring(1, line.length() - 1);
                String[] book_parts = sub_str.split("\",\"");
                id_list.add(book_parts[0]);
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

        return id_list;
    }
}
