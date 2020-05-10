package library;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class AddData {
    public static void main(String[] args) {
        try {
            ManagementBean managementBean = new ManagementBean();
            managementBean.addBook("Kapitan Nemo", "9788376235240",
                    "Juliusz", "Verne", "Powieść", "3");
            managementBean.addBook("Ballady i romanse", "1000000000001",
                    "Adam", "Mickiewicz", "Zbiór", "6");
            managementBean.addBook("Pan Tadeusz", "1000000000021",
                    "Adam", "Mickiewicz", "Epos", "10");
            managementBean.addBook("Beowulf", "1000000004401",
                    "Autor", "Nieznany", "Epos", "2");
            managementBean.addBook("Iliada", "1003000004401",
                    "-", "Homer", "Epos", "5");
            managementBean.addBook("Odyseja", "1003050004401",
                    "-", "Homer", "Epos", "8");
            managementBean.addBook("Zbiór fraszek", "1003000004401",
                    "Jan", "Kochanowski", "Fraszki", "2");


            managementBean.addReader("Jan", "Kowalski", true);
            managementBean.addReader("Maria", "Nowak", true);
            managementBean.addReader("Tadeusz", "Pudzianowski", false);
            List<Reader> readers = managementBean.getReaders();
            List<Book> books = managementBean.getBooks();

            for (int i = 0; i<20; i++){
                Random rand = new Random();
                managementBean.addBorrowing(books.get(rand.nextInt(books.size())).getId(), readers.get(rand.nextInt(readers.size())).getId(), createRandomDate(2000,2019));
            }

        }
        catch(Exception e) {
            System.err.println("Blad przy dodawaniu rekordu: " + e);
        }
    }

    public static String createRandomIntBetween(int start, int end) {
        return String.valueOf(start + (int) Math.round(Math.random() * (end - start)));
    }

    public static String createRandomDate(int startYear, int endYear) {
        String day = createRandomIntBetween(1, 28);
        String month = createRandomIntBetween(1, 12);
        String year = createRandomIntBetween(startYear, endYear);
        String date = month+'/'+day+'/'+year;
        return date;
    }

}
