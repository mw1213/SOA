package library;

import clojure.lang.Obj;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigInteger;
import java.util.*;

@ManagedBean(name = "Manager")
@SessionScoped
public class ManagementBean {
    EntityManagerFactory factory = Persistence.createEntityManagerFactory("Hibernate-Zajecia");
    EntityManager em = factory.createEntityManager();

    Date today = new Date();

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public void addBook(String title, String isbn, String authorName, String authorSurname, String category, String quantity){
        try {
            Book book = new Book(title, new BigInteger(isbn));
            String jpql = "SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname";
            Query query = em.createQuery(jpql);
            query.setParameter("name", authorName);
            query.setParameter("surname", authorSurname);
            List<Author> authors = query.getResultList();

            String jpql2 = "SELECT c FROM Category c WHERE c.name = :name";
            Query query2 = em.createQuery(jpql2);
            query2.setParameter("name", category);
            List<Category> categories = query2.getResultList();

            em.getTransaction().begin();
            if (authors.isEmpty()){
                Author author = new Author(authorName, authorSurname);
                author.addBook(book);
                em.persist(author);
            }
            else {
                for (Author author:authors) {
                    author.addBook(book);
                }
            }
            if(categories.isEmpty()){
                Category category1 = new Category(category);
                category1.addBook(book);
                em.persist(category1);
            }
            else {
                for(Category cat: categories){
                    cat.addBook(book);
                }
            }
            Catalog catalog = new Catalog(Integer.parseInt(quantity), Integer.parseInt(quantity));
            catalog.setBook(book);
            book.setCatalog(catalog);
            em.persist(catalog);
            em.persist(book);
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.err.println("Blad przy wczytywaniu rekordu: " + e);
        }
    }

    public void addAuthor(String name, String surname){
        try {
            String jpql = "SELECT a FROM Author a WHERE a.name = :name AND a.surname = :surname";
            Query query = em.createQuery(jpql);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            List<Author> authors = query.getResultList();

            em.getTransaction().begin();
            if (authors.isEmpty()){
                Author author = new Author(name, surname);
                em.persist(author);
            }
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.err.println("Blad przy wczytywaniu rekordu: " + e);
        }
    }

    public void addReader(String name, String surname){
        try {
            String jpql = "SELECT a FROM Reader a WHERE a.name = :name AND a.surname = :surname";
            Query query = em.createQuery(jpql);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            List<Reader> readers = query.getResultList();

            em.getTransaction().begin();
            if (readers.isEmpty()){
                Reader reader = new Reader(name, surname);
                em.persist(reader);
            }
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.err.println("Blad przy wczytywaniu rekordu: " + e);
        }
    }


    public void addCategory(String name){
        try {
            String jpql = "SELECT a FROM Category a WHERE a.name = :name";
            Query query = em.createQuery(jpql);
            query.setParameter("name", name);
            List<Category> categories = query.getResultList();

            em.getTransaction().begin();
            if (categories.isEmpty()){
                Category category1 = new Category(name);
                em.persist(category1);
            }
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.err.println("Blad przy wczytywaniu rekordu: " + e);
        }
    }

    public void addBorrowing(String readerName, String readerSurname,String title, String name, String surname, String dateIn){
        Date date;
        try{
            date = new Date(dateIn);
        }catch (Exception e){
            date = new Date();
        }
        try {
            Date returnDate = (Date) date.clone();
            returnDate.setMonth((date.getMonth() - 1 + 3) % 12 + 1);
            String jpql = "SELECT c FROM Catalog c, Author a, Book b WHERE a.name = :name AND a.surname = :surname AND b.title = :title";
            Query query = em.createQuery(jpql);
            query.setParameter("name", name);
            query.setParameter("surname", surname);
            query.setParameter("title", title);
            List<Catalog> catalogs = query.getResultList();

            String jpql2 = "SELECT b FROM Author a, Book b WHERE a.name = :name AND a.surname = :surname AND b.title = :title";
            Query query2 = em.createQuery(jpql2);
            query2.setParameter("name", name);
            query2.setParameter("surname", surname);
            query2.setParameter("title", title);
            Book book = (Book) query2.getSingleResult();

            String jpql3 = "SELECT r FROM Reader r WHERE r.name = :name AND r.surname = :surname";
            Query query3 = em.createQuery(jpql3);
            query3.setParameter("name", readerName);
            query3.setParameter("surname", readerSurname);
            Reader reader = (Reader) query3.getSingleResult();

            
            em.getTransaction().begin();
            if (catalogs.isEmpty()){
                System.out.println("nie ma takiej możliwości");
            }
            else{
                if (catalogs.get(0).getAvailable()>0){
                    BooksBorrowing booksBorrowing = new BooksBorrowing(date, returnDate);
                    booksBorrowing.setBook(book);
                    booksBorrowing.setCatalog(catalogs.get(0));
                    booksBorrowing.setReader(reader);
                    Date today = new Date();
                    if (returnDate.compareTo(today) > 0){
                        booksBorrowing.catalog.borrowBook();
                    }
                    em.persist(booksBorrowing);
                }
                else {
                    System.out.println("nie można wypożyczyć");
                }
            }
            em.getTransaction().commit();
        }
        catch (Exception e){
            System.err.println("Blad przy wczytywaniu rekordu: " + e);
        }
    }

    public void addBorrowing(int bookId, int readerId, String dateIn){
        Date date;
        try{
            date = new Date(dateIn);
        }catch (Exception e){
            date = new Date();
        }
        try {
            Date returnDate = (Date) date.clone();
            returnDate.setMonth((date.getMonth() - 1 + 3) % 12 + 1);
            String jpql = "SELECT c FROM Catalog c, Book b WHERE b.id = :id AND b.catalog.id = c.id";
            Query query = em.createQuery(jpql);
            query.setParameter("id", bookId);
            List<Catalog> catalogs = query.getResultList();

            String jpql2 = "SELECT b FROM Book b WHERE b.id = :id";
            Query query2 = em.createQuery(jpql2);
            query2.setParameter("id", bookId);
            Book book = (Book) query2.getSingleResult();

            String jpql3 = "SELECT r FROM Reader r WHERE r.id= :id";
            Query query3 = em.createQuery(jpql3);
            query3.setParameter("id", readerId);
            Reader reader = (Reader) query3.getSingleResult();


            em.getTransaction().begin();
            if (catalogs.isEmpty()){
                System.out.println("nie ma takiej możliwości");
            }
            else{
                if (catalogs.get(0).getAvailable()>0){
                    BooksBorrowing booksBorrowing = new BooksBorrowing(date, returnDate);
                    booksBorrowing.setBook(book);
                    booksBorrowing.setCatalog(catalogs.get(0));
                    booksBorrowing.setReader(reader);
                    Date today = new Date();
                    if (returnDate.compareTo(today) > 0){
                        booksBorrowing.catalog.borrowBook();
                    }
                    em.persist(booksBorrowing);
                }
                else {
                    System.out.println("nie można wypożyczyć");
                }
            }

            em.getTransaction().commit();

        }
        catch (Exception e){
            System.err.println("Blad przy wczytywaniu rekordu: " + e);
        }

    }



    public void returnBorrowing(BooksBorrowing booksBorrowing){
        Date today = new Date();

        if(booksBorrowing.getToDate().compareTo(today)>0){
            em.getTransaction().begin();
            booksBorrowing.catalog.returnBook();
            booksBorrowing.setToDate(today);
            em.getTransaction().commit();
        }
    }

    public List<Catalog> getCatalog(){
        try {
            Query q = em.createQuery("FROM Catalog", Catalog.class);
            List<Catalog> catalogs = q.getResultList();
            return catalogs;

        }
        catch(Exception e) {
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<Catalog>();
    }

    public List<Book> getBooks(){
        try {
            Query q = em.createQuery("FROM Book", Book.class);
            List<Book> books = q.getResultList();
            return books;
        }
        catch (Exception e){
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<Book>();
    }
    public List<Author> getAuthors(){
        try {
            Query q = em.createQuery("FROM Author", Author.class);
            List<Author> authors = q.getResultList();
            return authors;
        }
        catch (Exception e){
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<Author>();
    }

    public List<Category> getCategories(){
        try {
            Query q = em.createQuery("FROM Category", Category.class);
            List<Category> categories = q.getResultList();
            return categories;
        }
        catch (Exception e){
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<Category>();
    }

    public List<BooksBorrowing> getBorrowings(){
        try {
            Query q = em.createQuery("FROM BooksBorrowing", BooksBorrowing.class);
            List<BooksBorrowing> borrowings = q.getResultList();
            return borrowings;
        }
        catch (Exception e){
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<BooksBorrowing>();
    }

    public List<Reader> getReaders(){
        try {
            Query q = em.createQuery("FROM Reader", Reader.class);
            List<Reader> readers = q.getResultList();
            return readers;
        }
        catch (Exception e){
            System.err.println("Blad przy pobieraniu rekord—w: " + e);
        }
        return new ArrayList<Reader>();
    }

    boolean catalogEdit =false;
    Catalog catalogEdition;

    public Catalog getCatalogEdition() { return catalogEdition; }
    public void setCatalogEdition(Catalog catalogEdition) { this.catalogEdition = catalogEdition; }
    public boolean isCatalogEdit() { return catalogEdit; }
    public void setCatalogEdit(boolean catalogEdit) { this.catalogEdit = catalogEdit; }

    public void deleteCatalog(Catalog catalog) {
        try {
            em.getTransaction().begin();
            catalog.getBook().setCatalog(null);
            List<BooksBorrowing> borrowings =catalog.getBook().getBooksBorrowing();
            for (BooksBorrowing booksBorrowing: borrowings){
                booksBorrowing.setCatalog(null);
            }
            em.remove(catalog);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.out.println("Nie można usunać pozycji:"+e.getMessage());
        }
    }

    public void editCatalog(Catalog catalog){
        setCatalogEdition(catalog);
        catalogEdit = true;
    }
    public void editCatalogQuantity(int newState){
        int diff = newState - catalogEdition.getQuantity();
        em.getTransaction().begin();
        catalogEdition.setQuantity(newState);
        catalogEdition.setAvailable(catalogEdition.getAvailable()+diff);
        em.getTransaction().commit();
        catalogEdit=false;
    }

    public void findFromAuthor(String author, Date dateFrom, Date dateTo){
        try {
            Map<String, Date> dateMap = new HashMap<String, Date>();
            String jpql = "SELECT DISTINCT r FROM Reader r, BooksBorrowing b WHERE b.reader.id = r.id AND b.book.author.id = :author";
            if(dateFrom != null){
                jpql +=" AND b.fromDate >= :dateFrom";
                dateMap.put("dateFrom", dateFrom);
            }
            if(dateTo != null){
                jpql +=" AND b.toDate <= :dateTo";
                dateMap.put("dateTo", dateTo);
            }

            Query query = em.createQuery(jpql);
            query.setParameter("author", Integer.parseInt(author));
            for(String key: dateMap.keySet()){
                query.setParameter(key, dateMap.get(key));
            }
            List<Reader> readers = query.getResultList();
            setLastQueryResult(readers.toString());
        } catch (Exception e){
            System.out.println("Nie udało się wykonać zapytania: "+e.getMessage());
        }

    }


    public void findFromBook(String book, Date dateFrom, Date dateTo){
        try {
            Map<String, Date> dateMap = new HashMap<String, Date>();
            String jpql = "SELECT DISTINCT r FROM Reader r, BooksBorrowing b WHERE b.reader.id = r.id AND b.book.id = :book";
            if(dateFrom != null){
                jpql +=" AND b.fromDate >= :dateFrom";
                dateMap.put("dateFrom", dateFrom);
            }
            if(dateTo != null){
                jpql +=" AND b.toDate <= :dateTo";
                dateMap.put("dateTo", dateTo);
            }

            Query query = em.createQuery(jpql);
            query.setParameter("book", Integer.parseInt(book));
            for(String key: dateMap.keySet()){
                query.setParameter(key, dateMap.get(key));
            }
            List<Reader> readers = query.getResultList();
            setLastQueryResult(readers.toString());
        } catch (Exception e){
            System.out.println("Nie udało się wykonać zapytania: "+e.getMessage());
        }

    }
    public void findFromReaders(String reader, Date dateFrom, Date dateTo){
        try {
            Map<String, Date> dateMap = new HashMap<String, Date>();
            String jpql = "SELECT DISTINCT books FROM Book books, BooksBorrowing b WHERE b.reader.id = :reader AND books.id = b.book.id";
            if(dateFrom != null){
                jpql +=" AND b.fromDate >= :dateFrom";
                dateMap.put("dateFrom", dateFrom);
            }
            if(dateTo != null){
                jpql +=" AND b.toDate <= :dateTo";
                dateMap.put("dateTo", dateTo);
            }

            Query query = em.createQuery(jpql);
            query.setParameter("reader", Integer.parseInt(reader));
            for(String key: dateMap.keySet()){
                query.setParameter(key, dateMap.get(key));
            }
            List<Book> books = query.getResultList();
            setLastQueryResult(books.toString());
        } catch (Exception e){
            System.out.println("Nie udało się wykonać zapytania: "+e.getMessage());
        }

    }

    public void findBiggestAuthor(){
        try {
            Map<String, Date> dateMap = new HashMap<String, Date>();
            String jpql1 = "SELECT COUNT(a) FROM Author a, BooksBorrowing b WHERE b.book.author.id = a.id GROUP BY a.id";
            Query query1 = em.createQuery(jpql1);
            List<Long> result1 = query1.getResultList();
            Long maxBorrows = result1.get(0);
            for (Long i: result1){
                if (i>maxBorrows){
                    maxBorrows = i;
                }
            }
            String jpql2 = "SELECT a FROM Author a, BooksBorrowing b WHERE b.book.author.id = a.id GROUP BY a.id HAVING COUNT(a) = :maxBorrows";
            Query query = em.createQuery(jpql2);
            query.setParameter("maxBorrows", maxBorrows);
            List result = query.getResultList();
            String output="Author with most read books is: ";
            for (Object o: result
                 ) {
                output += o.toString();
            }
            setLastQueryResult(output);

        } catch (Exception e){
            System.out.println("Nie udało się wykonać zapytania: "+e.getMessage());
        }
    }

    String lastQueryResult;

    public String getLastQueryResult() {
        return lastQueryResult;
    }

    public void setLastQueryResult(String lastQueryResult) {
        this.lastQueryResult = lastQueryResult;
    }
    Map<String, String> stringsParameters = new HashMap<String, String>();
    Map<String, Long> longParameters = new HashMap<String, Long>();
    Map<String, Date> dateParameters = new HashMap<String, Date>();

    public Map<String, String> getStringsParameters() { return stringsParameters; }
    public void setStringsParameters(Map<String, String> stringsParameters) { this.stringsParameters = stringsParameters; }
    public Map<String, Long> getLongParameters() { return longParameters; }
    public void setLongParameters(Map<String, Long> longParameters) { this.longParameters = longParameters; }
    public Map<String, Date> getDateParameters() { return dateParameters; }
    public void setDateParameters(Map<String, Date> dateParameters) { this.dateParameters = dateParameters; }

    public void addParameter(String type, String name, String value){
        System.out.println(type);
        System.out.println(name);
        System.out.println(value);
        try {
            if (type.equals("Long")) {
                addLongParameter(name, Long.parseLong(value));
            } else if (type.equals("Date")) {
                addDateParameter(name, new Date(value));
            } else if (type.equals("String")) {
                addStringParameter(name, value);
            } else {
                System.out.println("Nie dodano parametru");
            }
        }catch (Exception e){
            System.out.println("Nie dodano parametru: "+e.getMessage());
        }
    }

    public void addStringParameter(String name, String param){
        stringsParameters.put(name, param);
    }
    public void addLongParameter(String name, Long param){
        longParameters.put(name, param);
    }
    public void addDateParameter(String name, Date param){
        dateParameters.put(name, param);
    }
    public void flushParameters(){
        stringsParameters.clear();
        dateParameters.clear();
        longParameters.clear();
    }

    public void createQuery(String queryText){
        if(queryText.equals("")){
            return;
        }
        try {

            Query query = em.createQuery(queryText);
            for (String key: dateParameters.keySet()){
                query.setParameter(key, dateParameters.get(key));
            }
            for (String key: longParameters.keySet()){
                query.setParameter(key, longParameters.get(key));
            }
            for (String key: stringsParameters.keySet()){
                query.setParameter(key, stringsParameters.get(key));
            }
            List result = query.getResultList();
            System.out.println(result);
            setLastQueryResult(result.toString());
        }
        catch (Exception e){
            setLastQueryResult(e.getMessage());
        }
        flushParameters();
    }
}
