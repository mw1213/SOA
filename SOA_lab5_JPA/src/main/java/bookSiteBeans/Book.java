package bookSiteBeans;



import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table( name = "book" )
public class Book {
    private int id;
    private String authorSurname;
    private String authorName;
    private String title;
    private BigInteger ISBN;
    private int publishYear;
    private double price;


    public Book(){
        super();
    }
    public Book(String surname, String name, String title, BigInteger ISBN, int year, double price){
        super();
        this.authorName = name;
        this.authorSurname = surname;
        this.title = title;
        this.ISBN = ISBN;
        this.publishYear = year;
        this.price = price;
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
    @Column(name="authors_surname", nullable = false)
    public String getAuthorSurname() {
        return authorSurname;
    }
    @Column(name="authors_name", nullable = false)
    public String getAuthorName() {
        return authorName;
    }
    @Column(name = "title", nullable = false)
    public String getTitle() {
        return title;
    }
    @Column(name="ISBN", nullable = false, columnDefinition = "numeric(13,0)")
    public BigInteger getISBN() {
        return ISBN;
    }
    @Column(name="publish_year", nullable = false)
    public int getPublishYear() {
        return publishYear;
    }
    @Column(name="price", nullable = false)
    public double getPrice() {
        return price;
    }

    public void setAuthorSurname(String authorSurname) {
        this.authorSurname = authorSurname;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setISBN(BigInteger ISBN) {
        this.ISBN = ISBN;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
