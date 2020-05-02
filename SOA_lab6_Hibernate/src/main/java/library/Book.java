package library;

import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "book", schema = "bookslibrary")
public class Book {
    private int id;
    private String title;
    private BigInteger ISBNNumber;
    private Author author;
    private Category category;
    private Catalog catalog;
    private List<BooksBorrowing> booksBorrowing;

    public Book() { super(); }
    public Book(String title, BigInteger ISBNNumber) {
        super();
        this.title=title;
        this.ISBNNumber=ISBNNumber;
    }

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "title", nullable = false)
    public String getTitle() { return title; }
    public void setTitle(String title) {
        this.title = title;
    }

    @Column(name = "ISBNNumber", nullable = false, columnDefinition = "numeric(13,0)")
    public BigInteger getISBNNumber() {
        return ISBNNumber;
    }
    public void setISBNNumber(BigInteger ISBNNumber) {
        this.ISBNNumber = ISBNNumber;
    }

    @OneToOne
    @JoinColumn(name = "catalog_id")
    public Catalog getCatalog() { return catalog; }
    public void setCatalog(Catalog catalog) { this.catalog = catalog; }

    @OneToMany(mappedBy = "book")
    public List<BooksBorrowing> getBooksBorrowing() { return booksBorrowing; }
    public void setBooksBorrowing(List<BooksBorrowing> booksBorrowing) { this.booksBorrowing = booksBorrowing; }

    @ManyToOne(fetch = FetchType.LAZY)
    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    @ManyToOne(fetch = FetchType.LAZY)
    public Author getAuthor() { return author; }
    public void setAuthor(Author author) { this.author = author; }

    @Override
    public String toString() {
        return "'" + title + "\' " + author;
    }
}


