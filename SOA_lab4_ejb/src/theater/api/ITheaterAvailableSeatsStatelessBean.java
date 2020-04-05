package theater.api;


import theater.impl.Seat;

import java.util.List;

public interface ITheaterAvailableSeatsStatelessBean {
    public void getSeats(List<Seat> seats);
}
