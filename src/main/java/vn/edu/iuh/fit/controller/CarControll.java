package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import vn.edu.iuh.fit.convert.CarForm;
import vn.edu.iuh.fit.dao.CarDao;
import vn.edu.iuh.fit.dao.PersonDao;
import vn.edu.iuh.fit.entity.Car;
import vn.edu.iuh.fit.entity.Person;

import java.util.List;
import java.util.Map;

@Controller
public class CarControll {
    private CarDao carDao;
    private PersonDao personDao;
    @Autowired
    public CarControll(CarDao carDao, PersonDao personDao) {
        this.carDao = carDao;
        this.personDao = personDao;
    }
    @GetMapping("/cars/{perID}")
    public String showCars(@PathVariable long perID, Model model){
        List<Car> cars=carDao.getCarByPer(perID);
        Person person=personDao.getPerson(perID);
        model.addAttribute("person", person);
        model.addAttribute("cars", cars);
        return "cars";
    }
    @GetMapping("/create-car/{perID}")
    public String showCreateCarForm(@PathVariable long perID, Model model){
        model.addAttribute("perID", perID);
        return "create-car";
    }
    @PostMapping("/create-car/{perID}")
    public String createCar(@PathVariable long perID,
                            @ModelAttribute CarForm carForm){
        int color=Integer.parseInt(carForm.getColor());
        carDao.addCar(new Car(carForm.getBrand(), color), perID);
        return "redirect:/cars/"+perID;
    }
    @GetMapping("/car-by-brand")
    public String getTotalCarByBrand(Model model){
        Map<String, Long> map=carDao.getTotalCarByBrand();
        model.addAttribute("map",map);
        return "car-by-brand";
    }
    @GetMapping("/car-by-per")
    public String getTotalCarByPer(Model model){
        Map<Person, Long> map=carDao.getTotalCarByPer();
        model.addAttribute("map", map);
        return "car-by-per";
    }
    @GetMapping("/calc-insurance/{perID}")
    public String calcInsurance(@PathVariable long perID, Model model){
        double insurance=personDao.calcInsurance(perID);
        long totalCars=carDao.totalCar(perID);
        double total=totalCars*insurance;
        List<Car> cars=carDao.getCarByPer(perID);
        model.addAttribute("insurance", insurance);
        model.addAttribute("totalCars", totalCars);
        model.addAttribute("total", total);
        model.addAttribute("cars", cars);
        return "calc-insurance";
    }

}
