package bookManagement;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ManagedBean(name = "Books")
@SessionScoped
public class BookSiteBean {
    static BooksDataSet booksDataSet = new BooksDataSet();
    List<Book> choosenBooks = new ArrayList<>();
    List<String> titles = new ArrayList<>();
    List<Book> cart = new ArrayList<>();
    List<String> authors = booksDataSet.getAuthors();
    String title, author, type, priceFrom, priceTo, currency, pages;
    Boolean titleCheckBox=false, authorCheckBox=false, typeCheckBox=false, priceFromCheckBox=false, priceToCheckBox=false,
            currencyCheckBox=false, pagesCheckBox=false;

    public void setCart(List<Book> cart) {
        this.cart = cart;
    }

    public List<Book> getCart() {
        return cart;
    }

    public void setTitleCheckBox(Boolean titleCheckBox) {
        this.titleCheckBox = titleCheckBox;
    }

    public void setAuthorCheckBox(Boolean authorCheckBox) {
        this.authorCheckBox = authorCheckBox;
    }

    public void setTypeCheckBox(Boolean typeCheckBox) {
        this.typeCheckBox = typeCheckBox;
    }

    public void setPriceFromCheckBox(Boolean priceFromCheckBox) {
        this.priceFromCheckBox = priceFromCheckBox;
    }

    public void setPriceToCheckBox(Boolean priceToCheckBox) {
        this.priceToCheckBox = priceToCheckBox;
    }

    public void setCurrencyCheckBox(Boolean currencyCheckBox) {
        this.currencyCheckBox = currencyCheckBox;
    }

    public void setPagesCheckBox(Boolean pagesCheckBox) {
        this.pagesCheckBox = pagesCheckBox;
    }

    public Boolean getTitleCheckBox() {
        return titleCheckBox;
    }

    public Boolean getAuthorCheckBox() {
        return authorCheckBox;
    }

    public Boolean getTypeCheckBox() {
        return typeCheckBox;
    }

    public Boolean getPriceFromCheckBox() {
        return priceFromCheckBox;
    }

    public Boolean getPriceToCheckBox() {
        return priceToCheckBox;
    }

    public Boolean getCurrencyCheckBox() {
        return currencyCheckBox;
    }

    public Boolean getPagesCheckBox() {
        return pagesCheckBox;
    }

    public void setAuthors(List<String> authors) { this.authors = authors; }
    public List<String> getAuthors() { return authors; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setType(String type) { this.type = type; }
    public void setCurrency(String currency) { this.currency = currency; }
    public void setPages(String pages) { this.pages = pages; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getType() { return type; }
    public void setPriceFrom(String priceFrom) { this.priceFrom = priceFrom; }
    public void setPriceTo(String priceTo) { this.priceTo = priceTo; }
    public String getPriceFrom() { return priceFrom; }
    public void setTitles(List<String> titles) { this.titles = titles; }
    public List<String> getTitles() { return titles; }
    public String getPriceTo() { return priceTo; }
    public String getCurrency() { return currency; }
    public String getPages() { return pages; }

    public void setChoosenBooks(List<Book> choosenBooks) {
        this.choosenBooks = choosenBooks;
    }
    public List<Book> getChoosenBooks() {
        return choosenBooks;
    }

    public void setBooksDataSet(BooksDataSet booksDataSet) {
        BookSiteBean.booksDataSet = booksDataSet;
    }

    public BooksDataSet getBooksDataSet() {
        return booksDataSet;
    }

    public List<Book> getBooks(){
        return booksDataSet.getBooks();
    }

    public void loadTitles(){
        titles = booksDataSet.getTitles(getAuthor());
    }

    public void filterBooks(){
        List<Book> helpset = new ArrayList<>(booksDataSet.getBooks());
        if(titleCheckBox){
            String Booktitle;
            for (Iterator<Book> it = helpset.iterator(); it.hasNext();){
                Booktitle = it.next().getTitle();
                if(!title.contentEquals(Booktitle)){
                    it.remove();
                }
            }
        }
        if(authorCheckBox){
            String bookAuthor;
            for (Iterator<Book> it = helpset.iterator(); it.hasNext();){
                bookAuthor = it.next().getAuthor();
                if(!author.contentEquals(bookAuthor)){
                    it.remove();
                }
            }
        }
        Double bookPrice;
        if(!currencyCheckBox){
            if(priceFromCheckBox){
                for (Iterator<Book> it = helpset.iterator(); it.hasNext();){
                    bookPrice = it.next().getPrice();
                    if(!(bookPrice>=Double.parseDouble(priceFrom))){
                        it.remove();
                    }
                }
            }
            if(priceToCheckBox){
                for (Iterator<Book> it = helpset.iterator(); it.hasNext();){
                    bookPrice = it.next().getPrice();
                    if(!(bookPrice<=Double.parseDouble(priceTo))){
                        it.remove();
                    }
                }
            }
        } else{
            if(priceFromCheckBox){
                for (Iterator<Book> it = helpset.iterator(); it.hasNext();){
                    bookPrice = it.next().getPlnPrice();
                    if(!(bookPrice>=Double.parseDouble(priceFrom))){
                        it.remove();
                    }
                }
            }
            if(priceToCheckBox){
                for (Iterator<Book> it = helpset.iterator(); it.hasNext();){
                    bookPrice = it.next().getPlnPrice();
                    if(!(bookPrice<=Double.parseDouble(priceTo))){
                        it.remove();
                    }
                }
            }
        }
        choosenBooks = helpset;
    }
    public void addToCart(Book book){
        if(!cart.contains(book)) {
            cart.add(book);
        }
    }
    public void removeFromCart(Book book){
        try {
            cart.remove(book);
        } catch (Exception e){
        }
    }

}
