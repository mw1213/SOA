package bookSiteBeans;


import templateProject.Student;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "BookSite")
@SessionScoped
public class manageBookSiteBean {
    String surname;
    String name;
    String title;
    String ISBN;
    int year;
    double price;

    boolean renderUpdate = false;
    String surnameUpdate;
    String nameUpdate;
    String titleUpdate;
    String ISBNUpdate;
    int yearUpdate, idUpdate;
    double priceUpdate;

    public int getIdUpdate() {
        return idUpdate;
    }

    public void setIdUpdate(int idUpdate) {
        this.idUpdate = idUpdate;
    }

    public boolean isRenderUpdate() {
        return renderUpdate;
    }

    public void setRenderUpdate(boolean renderUpdate) {
        this.renderUpdate = renderUpdate;
    }

    public String getSurnameUpdate() {
        return surnameUpdate;
    }

    public void setSurnameUpdate(String surnameUpdate) {
        this.surnameUpdate = surnameUpdate;
    }

    public String getNameUpdate() {
        return nameUpdate;
    }

    public void setNameUpdate(String nameUpdate) {
        this.nameUpdate = nameUpdate;
    }

    public String getTitleUpdate() {
        return titleUpdate;
    }

    public void setTitleUpdate(String titleUpdate) {
        this.titleUpdate = titleUpdate;
    }

    public String getISBNUpdate() {
        return ISBNUpdate;
    }

    public void setISBNUpdate(String ISBNUpdate) {
        this.ISBNUpdate = ISBNUpdate;
    }

    public int getYearUpdate() {
        return yearUpdate;
    }

    public void setYearUpdate(int yearUpdate) {
        this.yearUpdate = yearUpdate;
    }

    public double getPriceUpdate() {
        return priceUpdate;
    }

    public void setPriceUpdate(double priceUpdate) {
        this.priceUpdate = priceUpdate;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    EntityManagerFactory factory = Persistence.createEntityManagerFactory("JPA-Zajecia");
    EntityManager em = factory.createEntityManager();

    public void addBook(){
        String surname, name, title;
        BigInteger isbn;
        int year;
        double price;
        if(this.surname!=null){
            surname = this.surname;
        } else {
            surname = "undefined";
        }
        if(this.name!= null){
            name = this.name;
        }else {
            name = "undefined";
        }
        if(this.title!=null){
            title = this.title;
        }else {
            title = "undefined";
        }
        if(this.ISBN!=null){
            isbn = new BigInteger(this.ISBN);
        } else {
            isbn = new BigInteger("0000000000000");
        }
        year = this.year;
        price = this.price;

        try {
            Book book = new Book(surname, name, title, isbn, year, price);
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            System.out.println("Zapisano w bazie: " + book);
            this.surname=null;
            this.name=null;
            this.title=null;
            this.ISBN=null;
            this.year=0;
            this.price=0.0;
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }

    }
    public List<Book> getBooks(){
        try {
            Query q = em.createQuery("FROM Book", Book.class);
            List<Book> books = q.getResultList();
            return books;
        }
            catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<Book>();
    }

    public void removeBook(Book book){
        try {
            Book book1 = em.find(Book.class, book.getId());
            em.getTransaction().begin();
            em.remove(book1);
            em.getTransaction().commit();
        }
        catch(Exception e) {
            System.err.println("Blad przy usuwaniu rekord—w: " + e);
        }
    }
    public void setUpdateBook(Book book){
        setRenderUpdate(true);
        setSurnameUpdate(book.getAuthorSurname());
        setNameUpdate(book.getAuthorName());
        setTitleUpdate(book.getTitle());
        setISBNUpdate(book.getISBN().toString());
        setYearUpdate(book.getPublishYear());
        setPriceUpdate(book.getPrice());
        setIdUpdate(book.getId());
    }
    public void updateBook(){

        try {
            BigInteger isbn = new BigInteger(getISBNUpdate());
            Book book = em.find(Book.class, getIdUpdate());
            em.getTransaction().begin();
            book.setAuthorSurname(getSurnameUpdate());
            book.setAuthorName(getNameUpdate());
            book.setTitle(getTitleUpdate());
            book.setISBN(isbn);
            book.setPublishYear(getYearUpdate());
            book.setPrice(getPriceUpdate());
            em.getTransaction().commit();
            setRenderUpdate(false);
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }

}
