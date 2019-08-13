import java.io.*;

public class Change_book_file {
    public static void main(String[] args) {
        change_file("old_book", "new_book");
    }

    public static void change_file(String old_file, String new_file) {
        String line;
        BufferedReader reader = null;
        int[] interest_index = {0, 2, 3, 4, 6};

        try {
            File file = new File(old_file + ".txt");
            reader = new BufferedReader(new FileReader(file));

            while ((line = reader.readLine()) != null) {
                StringBuilder str_b = new StringBuilder(); // outputting the serialized tree in this string
                String sub_str = line.substring(1, line.length() - 1);
                String[] book_parts = sub_str.split("\",\"");

                str_b.append("\"");

                for (int i = 0; i < interest_index.length; i++) {
                    str_b.append(book_parts[interest_index[i]]);
                    if (i < interest_index.length-1) str_b.append("\",\"");
                    else str_b.append("\"");
                }


                //System.out.println(str_b);
                append_to_file(new_file, str_b);
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
