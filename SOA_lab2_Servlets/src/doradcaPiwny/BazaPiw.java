package doradcaPiwny;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class BazaPiw {
    private List<Piwo> Piwa;

    public BazaPiw() {
        Piwa = Arrays.asList(new Piwo("jasne", "Harnaś"),new Piwo("jasne", "Artus"),
                new Piwo("jasne", "Barkas"),new Piwo("jasne", "Bosman"),
                new Piwo("jasne", "Bosman"),new Piwo("jasne", "Pale Ale belgijskie"),
                new Piwo("jasne", "Brok"),new Piwo("jasne", "Żywiec"),
                new Piwo("jasne", "Tyskie"),new Piwo("jasne", "Samson"),
                new Piwo("jasne", "Bernard"),new Piwo("jasne", "Perła"),
                new Piwo("jasne", "Corona"),new Piwo("jasne", ""),
                new Piwo("ciemne", "Guinness"),new Piwo("ciemne", "Kormoran"),
                new Piwo("ciemne", "Zacne ciemne"),new Piwo("ciemne", "Koziel"),
                new Piwo("ciemne", "Okocim ciemne"), new Piwo("ciemne", "Książęce"),
                new Piwo("ciemne", "Komes"),new Piwo("ciemne", "Fortuna czarne"),
                new Piwo("inne", "Radler Warka"),new Piwo("inne", "Radler Wojak"),
                new Piwo("inne", "Radler Okocim"),new Piwo("inne", "Radler Łomża"));
    }

    public List<Piwo> getPiwa(String kolor){
        List<Piwo> response = new ArrayList<>();
        for (Piwo piwo: Piwa
             ) {
            if (piwo.getKolor().equalsIgnoreCase(kolor)){
                response.add(piwo);
            }
        }
        return response;
    }
}
