package JLMS.model;

import java.time.LocalDate;

public class Loan {
    private int book_id;
    private String patron_username;
    private LocalDate start_date;
    private LocalDate due_date;
    private double fine;

    public Loan() {
    }

    public Loan(int book_id, String patron_username, LocalDate start_date, LocalDate due_date, double fine) {
        this.book_id = book_id;
        this.patron_username = patron_username;
        this.start_date = start_date;
        this.due_date = due_date;
        this.fine = fine;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getPatron_username() {
        return patron_username;
    }

    public void setPatron_username(String patron_username) {
        this.patron_username = patron_username;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getDue_date() {
        return due_date;
    }

    public void setDue_date(LocalDate due_date) {
        this.due_date = due_date;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    @Override
    public String toString() {
        return "Loan{" +
                "book_id=" + book_id +
                ", patron_username='" + patron_username + '\'' +
                ", start_date=" + start_date +
                ", due_date=" + due_date +
                ", cost=" + fine +
                '}';
    }
}
