package ksiazki;

import java.util.ArrayList;
import java.util.List;

public class BooksDataSet {
    private List<Book> books;

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<String> getAuthors(){
        List<String> authors = new ArrayList<>();
        for (Book book: books
             ) {
            if(!authors.contains(book.getAuthor())){
                authors.add(book.getAuthor());
            }
        }
        return authors;
    }

    public List<String> getTitles(String author){
        List<String> titles = new ArrayList<>();
        for (Book book: books
        ) {
            if (book.getAuthor().equalsIgnoreCase(author)){
                titles.add(book.getTitle());
            }
        }
        return titles;
    }

    public List<String> getBookTypes(){
        List<String> types = new ArrayList<>();
        for (Book book: books
        ) {
            if(!types.contains(book.getType())){
                types.add(book.getType());
            }
        }
        return types;
    }

    public BooksDataSet() {
        books = new ArrayList<>(List.of(
                new Book("Ksiazka1", "Autor1", "Typ1", "100", "PLN", "100"),
                new Book("Ksiazka2", "Autor2", "Typ2", "200", "PLN", "200"),
                new Book("Ksiazka3", "Autor3", "Typ3", "300", "PLN", "300"),
                new Book("Ksiazka4", "Autor4", "Typ4", "400", "PLN", "400"),
                new Book("Ksiazka5", "Autor5", "Typ5", "500", "PLN", "500"),
                new Book("Ksiazka6", "Autor1", "Typ1", "600", "PLN", "600"),
                new Book("Ksiazka7", "Autor2", "Typ2", "700", "PLN", "700"),
                new Book("Ksiazka8", "Autor3", "Typ3", "800", "PLN", "800"),
                new Book("Ksiazka9", "Autor4", "Typ4", "900", "PLN", "900"),
                new Book("Ksiazka10", "Autor5", "Typ5", "1000", "PLN", "1000"),
                new Book("Ksiazka11", "Autor1", "Typ1", "10", "PLN", "1100"),
                new Book("Ksiazka12", "Autor2", "Typ2", "50", "PLN", "1200"),
                new Book("Ksiazka13", "Autor3", "Typ3", "70", "PLN", "1300")
        ));
    }
}
