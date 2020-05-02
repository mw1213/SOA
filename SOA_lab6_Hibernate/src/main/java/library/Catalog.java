package library;

import javax.persistence.*;

@Entity
@Table(name = "catalog", schema = "bookslibrary")
public class Catalog {
    private int id;

    Book book;
    int quantity;
    int available;

    public Catalog() {
        super();
    }

    public Catalog(int quantity, int available) {
        super();
        this.available=available;
        this.quantity=quantity;
    }
    public void borrowBook(){
        this.available-=1;
    }
    public void returnBook(){
        this.available+=1;
    }

    @OneToOne(mappedBy = "catalog")
    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "quantity", nullable = false)
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Column(name = "available", nullable = false)
    public int getAvailable() {
        return available;
    }
    public void setAvailable(int available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "id=" + id +
                ", book=" + book +
                ", quantity=" + quantity +
                ", available=" + available +
                '}';
    }
}
