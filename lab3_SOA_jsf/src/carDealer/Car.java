package carDealer;

public class Car {
    String mark;
    String model;
    Double price;


    public void setModel(String model) {
        this.model = model;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getMark() {
        return mark;
    }

    public String getModel() {
        return model;
    }

    public Double getPrice() {
        return price;
    }

    public Car(String mark, String model, Double price) {
        this.mark = mark;
        this.model = model;
        this.price = price;
    }
}
