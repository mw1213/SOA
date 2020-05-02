package library;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigInteger;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        try {
            ManagementBean managementBean = new ManagementBean();
            managementBean.addBorrowing("Marcin", "Tabor", "Gdzie jest pusia", "Maciej", "Wilk", "01/05/2020");
        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }
}
