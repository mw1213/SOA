package templateProject;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "student")
public class Student {
    private int id;
    private String imie;
    private String nazwisko;
    private Date dodanieData;

    public Student(String name, String surname, Date ceated, Date date) {
        super();
        this.imie = name;
        this.nazwisko = surname;
        this.dodanieData = ceated;
    }
    public Student(){
        super();
    }

    @Id
    @GeneratedValue
    @Column(name="id", nullable = false)
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="imie", nullable = false)
    public void setImie(String imie) {
        this.imie = imie;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public void setDodanieData(Date dodanieData) {
        this.dodanieData = dodanieData;
    }

    public String getImie() {
        return imie;
    }
    @Column(name="nazwisko", nullable = false)
    public String getNazwisko() {
        return nazwisko;
    }
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true)
    public Date getDodanieData() {
        return dodanieData;
    }
}
