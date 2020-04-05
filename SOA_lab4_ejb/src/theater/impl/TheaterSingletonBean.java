package theater.impl;


import com.sun.xml.internal.ws.api.message.ExceptionHasMessage;

import javax.annotation.PostConstruct;
import javax.ejb.Lock;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Startup
@Lock
public class TheaterSingletonBean {
    private List<Seat> listOfSeats = new ArrayList<>();

    public void setListOfSeats(List<Seat> listOfSeats) {
        this.listOfSeats = listOfSeats;
    }

    public  int getSeatPrice(int seatPlace){
        int price=0;
        try{
            price = listOfSeats.get(seatPlace).getPrice();
        }catch (Exception e){
            System.out.println("not a valid seat");
        }
        return price;
    }

    public List<Seat> getListOfSeats() {
        return listOfSeats;
    }

    public void buyTicket(List<Seat> seats, TheaterPaySystemStatefulBean bean) {
        int wallet = bean.getWallet();
        int wholePrice = 0;
        try {
            for (Seat seat: seats){
                if(seat.isTaken()){
                    throw new CashException("seats taken");
                }
                wholePrice+=seat.getPrice();
            }
            if (wallet>=wholePrice){
                wallet-=wholePrice;
                for (Seat seat:seats) {
                    seat.setTaken(true);
                }
                bean.setWallet(wallet);
            }
            else {
                throw new CashException("not enough cash");
            }
        } catch (CashException e){
            bean.setOperation_value(e.message);
        }

    }
    @PostConstruct
    private void initialize() {
        int x;
        Seat seat;
        for (int i = 0; i < 20; i++) {
            if (i < 10) {
                x = 100;
            } else {
                x = 60;
            }
            seat = new Seat(i, x, false);
            listOfSeats.add(seat);
        }
    }
}