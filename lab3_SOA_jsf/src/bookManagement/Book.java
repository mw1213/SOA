package bookManagement;

public class Book {
    private String title;
    private String author;
    private String type;
    private Double price;
    private String currency;
    private String pages;
    private Double plnPrice;

    public void setPlnPrice(Double plnPrice) {
        this.plnPrice = plnPrice;
    }

    public Double getPlnPrice() {
        return plnPrice;
    }

    public Book(String title, String author, String type, Double price, String currency, String pages) {
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

    public void setPrice(Double price) {
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

    public Double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getPages() {
        return pages;
    }
}
