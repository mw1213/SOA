package currencyExchange;

import NBPConnector.NBPConnector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "Wymien")
@RequestScoped
public class PrzeliczaczWalut {
    static NBPConnector connector = new NBPConnector();
    String to="PLN";
    String howMuch="123";
    Double worth=123.0;

    String from = "EUR";
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public void setHowMuch(String howMuch) {
        this.howMuch = howMuch;
    }

    public void setWorth(Double worth) {
        this.worth = worth;
    }

    public String getTo() {
        return to;
    }

    public String getHowMuch() {
        return howMuch;
    }

    public String wymien(){
        worth = connector.calculate(from, to, Double.parseDouble(howMuch));
        return "Przeliczenie";
    }
    public Double getWorth(){
        return this.worth;
    }
}
