package theater.impl;

import theater.api.ITheaterAvailableSeatsStatelessBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
@LocalBean
public class TheaterAvailableSeatsStatelessBean implements ITheaterAvailableSeatsStatelessBean {
    private List<Seat> freeSeats = new ArrayList<>();


    public void setFreeSeats(List<Seat> freeSeats) {
        this.freeSeats = freeSeats;
    }

    public List<Seat> getFreeSeats() {
        return freeSeats;
    }

    @Override
    public void getSeats(List<Seat> seats) {
        if(!freeSeats.isEmpty()){
            freeSeats.clear();
        }
        for (Seat seat: seats){
            if(!seat.isTaken()){
                freeSeats.add(seat);
            }
        }
    }
}
