package library;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category", schema = "bookslibrary")
public class Category {
    private int id;
    String name;
    List<Book> books = new ArrayList();

    public void addBook(Book book){
        books.add(book);
        book.setCategory(this);
    }

    public void removeBook(Book book){
        books.remove(book);
        book.setCategory(null);
    }

    public Category() {
        super();
    }

    public Category(String name) {
        super();
        this.name=name;
    }

    @OneToMany(mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Book> getBooks() { return books; }
    public void setBooks(List<Book> books) { this.books = books; }

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

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", books=" + books +
                '}';
    }
}
