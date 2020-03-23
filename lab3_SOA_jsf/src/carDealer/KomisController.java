package carDealer;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "Komis")
@SessionScoped
public class KomisController implements Serializable {
    static String carModel="";
    static String carMark="";
    List<String> carModels;
    static List<String> carMarks = List.of("Tesla", "BMW", "Audi");
    static Double from=0.0;
    static Double to=100000.0;
    static String engine="";
    static String email;
    static String name;
    static List<Car> cars = List.of(
            new Car("Tesla","1",99999.0),new Car("Tesla","1",9999.0),
            new Car("Tesla","1",9999.0),new Car("Tesla","2",79999.0),
            new Car("Tesla","2",7999.0),new Car("Tesla","2",799.0),
            new Car("Tesla","3",69999.0),new Car("Tesla","3",6999.0),
            new Car("Audi","A1",89999.0),new Car("Tesla","3",699.0),
            new Car("Audi","A1",8999.0),new Car("Audi","A1",899.0));

    List<Car> cars_aviable = new ArrayList<>();

    public void setCars(List<Car> cars) { this.cars = cars; }
    public List<Car> getCars() { return cars; }
    public void setEmail(String email) { this.email = email; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public String getName() { return name; }
    public void setEngine(String engine) { this.engine = engine; }
    public String getEngine() { return engine; }
    public Double getFrom() { return from; }
    public Double getTo() { return to; }
    public void setFrom(Double from) { this.from = from; }
    public void setTo(Double to) { this.to = to; }
    public String getCarModel() { return carModel; }
    public String getCarMark() { return carMark; }
    public List<String> getCarModels() { return carModels; }
    public List<String> getCarMarks() { return carMarks; }
    public void setCarModel(String carModel) { this.carModel = carModel; }
    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }
    public void setCarModels(List<String> carModels) {
        this.carModels = carModels;
    }
    public void setCarMarks(List<String> carMarks) {
        this.carMarks = carMarks;
    }
    public void setCars_aviable(List<Car> cars_aviable) { this.cars_aviable = cars_aviable; }
    public List<Car> getCars_aviable() { return cars_aviable; }

    public void loadModels(){
        if(carMark.contentEquals("Tesla")){
            carModels = List.of("1", "2", "3");
        }
        else if(carMark.contentEquals("BMW")){
            carModels = List.of("X1", "Z4", "330e");
        }
        else if(carMark.contentEquals("Audi")){
            carModels = List.of("A1", "A2", "A3");
        } else {
            carModels = List.of();
        }
    }
    public void loadCars(){
        if (!cars_aviable.isEmpty()){
            cars_aviable.clear();
        }
        for (Car car: cars
             ) {
            if (car.mark.contentEquals(this.carMark)  && car.model.contentEquals(this.carModel) &&
                    car.price<=this.to && car.price>=this.from){
                cars_aviable.add(car);
            }
        }
    }
}
