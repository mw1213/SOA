package doradcaPiwny;

public class Piwo {
    private String kolor;
    private String nazwa;

    public Piwo(String kolor, String nazwa) {
        this.kolor = kolor;
        this.nazwa = nazwa;
    }

    public void setKolor(String kolor) {
        this.kolor = kolor;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getKolor() {
        return kolor;
    }
}
