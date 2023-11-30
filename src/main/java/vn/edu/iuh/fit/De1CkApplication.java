package vn.edu.iuh.fit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import vn.edu.iuh.fit.dao.CarDao;
import vn.edu.iuh.fit.dao.PersonDao;
import vn.edu.iuh.fit.entity.Car;
import vn.edu.iuh.fit.entity.Person;

import java.time.LocalDate;

@SpringBootApplication
public class De1CkApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(De1CkApplication.class, args);
        PersonDao personDao=context.getBean(PersonDao.class);
        CarDao carDao=context.getBean(CarDao.class);
//        personDao.getListPerson().forEach(p->System.out.println(p.toString()));
//        System.out.println(personDao.getPerson(2));
//        System.out.println(personDao.addPerson(new Person("tien", LocalDate.of(2002, 11, 21), "tien@mail", LocalDate.now(), "none", 90000)));
//        personDao.getMaxSalaryPer().forEach(p->System.out.println(p.toString()));
/*        carDao.getTotalCarByBrand().entrySet().forEach(entry->{
            System.out.println(entry.getKey()+"- So luong: "+entry.getValue());
        });*/
     /*   carDao.getTotalCarByPer().entrySet().forEach(entry->{
            System.out.println(entry.getKey()+"\n So luong: "+entry.getValue());
        });
*/
//        System.out.println("Tiền bảo hiểm: "+personDao.calcInsurance(11));
//        System.out.println(carDao.addCar(new Car("Ford", 1), 11));
        System.out.println(personDao.logIn(11, "tien@mail"));
    }

}
