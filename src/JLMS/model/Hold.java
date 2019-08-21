package JLMS.model;

import java.sql.Date;

public class Hold {

    private long book_id;
    private String patron_username;
    private Date start_date;

    public Hold() {
    }

    public Hold(long book_id, String patron_username, Date start_date) {
        this.book_id = book_id;
        this.patron_username = patron_username;
        this.start_date = start_date;
    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getPatron_username() {
        return patron_username;
    }

    public void setPatron_username(String patron_username) {
        this.patron_username = patron_username;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    @Override
    public String toString() {
        return "Hold{" +
                "book_id=" + book_id +
                ", patron_username='" + patron_username + '\'' +
                ", start_date=" + start_date +
                '}';
    }
}
