package ksiazki;

import NBPConnector.NBPConnector;

public class Book {
    private String title;
    private String author;
    private String type;
    private String price;
    private String currency;
    private String pages;
    private String plnPrice;

    public void setPlnPrice(String plnPrice) {
        this.plnPrice = plnPrice;
    }

    public String getPlnPrice() {
        return plnPrice;
    }

    public Book(String title, String author, String type, String price, String currency, String pages) {
        this.title = title;
        this.author = author;
        this.type = type;
        this.price = price;
        this.currency = currency;
        this.pages = pages;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPages() {
        return pages;
    }
}
