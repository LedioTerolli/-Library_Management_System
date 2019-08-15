package JLMS.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Book {
    private int id;
    private String cover_url;
    private String title;
    private String author;
    private String category;
    private int branch_id;
    private boolean available;
    //private ArrayList<Loan> loan_log;
    //private Queue<Hold> hold_list;

    public Book () {}

    public Book(int id, String cover_url, String title, String author, String category, int branch_id, boolean available) {
        this.id = id;
        this.cover_url = cover_url;
        this.title = title;
        this.author = author;
        this.category = category;
        this.branch_id = branch_id;
        this.available = available;
        //loan_log = new ArrayList<Loan>();
        //hold_list = new LinkedList<Hold>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCover_url() {
        return cover_url;
    }

    public void setCover_url(String cover_url) {
        this.cover_url = cover_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(int branch_id) {
        this.branch_id = branch_id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", cover_url='" + cover_url + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", branch_id=" + branch_id +
                ", available=" + available +
                '}';
    }
}
