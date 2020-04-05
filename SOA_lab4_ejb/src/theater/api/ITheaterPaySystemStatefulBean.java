package theater.api;


import theater.impl.Seat;
import theater.impl.TheaterSingletonBean;

import java.util.List;

public interface ITheaterPaySystemStatefulBean {
    public void pay(TheaterSingletonBean bean, List<Seat> choosenSeats);
}
