package theater.impl;

import theater.api.ITheaterPaySystemStatefulBean;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;
import java.util.List;

@Stateful
@LocalBean
public class TheaterPaySystemStatefulBean implements ITheaterPaySystemStatefulBean {
    int wallet;
    String operation_value = "No operations made";

    public void setOperation_value(String operation_value) {
        this.operation_value = operation_value;
    }

    public String getOperation_value() {
        return operation_value;
    }

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public int getWallet() {
        return wallet;
    }

    @Override
    public void pay(TheaterSingletonBean bean, List<Seat> choosenSeats) {
        setOperation_value("Success");
        bean.buyTicket(choosenSeats, this);
    }
}
