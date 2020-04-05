package theater.impl;

public class Seat {
    int place;
    int price;
    boolean taken;

    public void setPlace(int place) {
        this.place = place;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }

    public int getPlace() {
        return place;
    }

    public int getPrice() {
        return price;
    }

    public boolean isTaken() {
        return taken;
    }

    public Seat(int place, int price, boolean taken) {
        this.place = place;
        this.price = price;
        this.taken = taken;
    }
}
