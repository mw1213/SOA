package numberPicker;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@ManagedBean(name = "Sprawdz")
@RequestScoped
public class SprawdzaczLosu {
    static int losuj = (int)(Math.random() * 5 + 1);
    static List<Integer> counter = new ArrayList<Integer>(Arrays.asList(0,0,0,0,0));
    private final Logger log = Logger.getLogger(getClass().getName());
    public void newLosuj(){
        losuj = (int)(Math.random()*5 + 1);
    }

    public String sprawdz(Integer nazwa){
        log.info(counter.get(nazwa-1).toString());
        counter.set(nazwa-1, counter.get(nazwa-1)+1);
        log.info(counter.get(nazwa-1).toString());
        if (nazwa == losuj){
            newLosuj();
            return "Wygrana";
        }
        else {
            return String.valueOf(nazwa);
        }
    }

    public List<Integer> getCounter(){
        return counter;
    }
}
