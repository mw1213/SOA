package theater.impl;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;


@ManagedBean(name = "TheaterApp")
@SessionScoped
public class TheaterApp {
    @EJB
    private TheaterAvailableSeatsStatelessBean theaterAvailableSeatsStatelessBean;
    @EJB
    private TheaterPaySystemStatefulBean theaterPaySystemStatefulBean;
    @EJB
    private TheaterSingletonBean theaterSingletonBean;

    private  List<Seat> choosenSeats = new ArrayList<>();
    private List<Seat> freeSeats = new ArrayList<>();


    public void setFreeSeats(List<Seat> freeSeats) {
        this.freeSeats = freeSeats;
    }

    public void loadFreeSeats(){
        theaterAvailableSeatsStatelessBean.getSeats(theaterSingletonBean.getListOfSeats());
        freeSeats = theaterAvailableSeatsStatelessBean.getFreeSeats();
    }

    public List<Seat> getFreeSeats() {
        return freeSeats;
    }

    public void setWallet(int wallet){
        theaterPaySystemStatefulBean.setWallet(wallet);
        System.out.println(theaterPaySystemStatefulBean.getWallet());
    }
    public int getWallet(){
        return theaterPaySystemStatefulBean.getWallet();
    }

    public void setChosenSeats(List<Seat> choosenSeats) {
        this.choosenSeats = choosenSeats;
    }

    public List<Seat> getChosenSeats() {
        return choosenSeats;
    }

    public void addToChosenSeats(Seat seat) {
        if (!choosenSeats.contains(seat)) {
            choosenSeats.add(seat);
        }
    }
    public String getPayMessage(){
        return theaterPaySystemStatefulBean.getOperation_value();
    }
    public void removeFromChosenSeats(Seat seat)
    {
        try {
            choosenSeats.remove(seat);
        } catch (Exception e){
        }
    }

    public void payForTickets(){
        theaterPaySystemStatefulBean.pay(theaterSingletonBean, choosenSeats);
        choosenSeats.clear();
    }

}
