package library;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "reader", schema = "bookslibrary")
public class Reader {
    private int id;
    String name;
    String surname;
    Boolean notifications;
    private List<BooksBorrowing> booksBorrowing;

    public Reader() {
        super();
    }
    public Reader(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Reader(String name, String surname, Boolean notifications) {
        this.name = name;
        this.surname = surname;
        this.notifications = notifications;
    }

    @OneToMany(mappedBy = "reader")
    public List<BooksBorrowing> getBooksBorrowing() { return booksBorrowing; }
    public void setBooksBorrowing(List<BooksBorrowing> booksBorrowing) { this.booksBorrowing = booksBorrowing; }

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "surname", nullable = false)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Column(name = "notifications", nullable = false)
    public Boolean getNotifications() {
        return notifications;
    }

    public void setNotifications(Boolean notifications) {
        this.notifications = notifications;
    }

    @Override
    public String toString() {
        return "Reader: "+ name + ' '+ surname + ' ' + id;
    }
}

