package vn.edu.iuh.fit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.edu.iuh.fit.convert.PersonForm;
import vn.edu.iuh.fit.dao.PersonDao;
import vn.edu.iuh.fit.entity.Person;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PersonControll {
    private PersonDao personDao;
    @Autowired
    public PersonControll(PersonDao personDao) {
        this.personDao = personDao;
    }
    @GetMapping("/")
    public String showHomePage(){
        return "index";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model){
        if(personDao.logIn(Long.parseLong(username), password)){
            if(Long.parseLong(username)==11){
                return "redirect:/persons/"+username;
            }else {
                return "redirect:/cars/"+username;
            }
        } else{
            model.addAttribute("error", "Sai thông tin đăng nhập");
            return "login";
        }
    }
    @GetMapping("/persons/{perID}")
    public String showAllPerson(@PathVariable Long perID,
                                @RequestParam(defaultValue = "0") int page,
                                @RequestParam(defaultValue = "5") int size,
                                Model model){
        Page<Person> personPage=personDao.getAll(page, size);
        model.addAttribute("perID",perID);
        model.addAttribute("persons",personPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", size);
        model.addAttribute("totalPerson",personPage.getTotalPages());
        return "persons";
    }
    @GetMapping("/delete-person/{perID}/{delPerID}")
    public String deletePerson(@PathVariable Long perID,
                                @PathVariable Long delPerID,
                                Model model){
        personDao.deletePerson(delPerID);
        return "redirect:/persons/"+perID;
    }
    @GetMapping("/active-person/{perID}/{actPerID}")
    public String activePerson(@PathVariable Long perID,
                               @PathVariable Long actPerID,
                               Model model){
        personDao.activePerson(actPerID);
        return "redirect:/persons/"+perID;
    }
    @GetMapping("/update-person/{perID}/{uptPerID}")
    public String showUpdatePersonForm(@PathVariable Long perID,
                               @PathVariable Long uptPerID,
                               Model model){
        Person person=personDao.getPerson(uptPerID);
        model.addAttribute("perID",perID);
        model.addAttribute("person",person);
        return "update-person";
    }
    @PostMapping("/update-person/{perID}")
    public String updatePerson(@PathVariable Long perID,
                                       @ModelAttribute PersonForm personForm,
                                       Model model){
        long updPerID=Long.parseLong(personForm.getUpdPerID());
        LocalDate born= LocalDate.parse(personForm.getBorn(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate graduated= LocalDate.parse(personForm.getGraduated(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        double salary=Double.parseDouble(personForm.getSalary());
        int status=Integer.parseInt(personForm.getStatus());
        personDao.updatePerson(new Person(updPerID, personForm.getName(), born, personForm.getMail(), graduated, personForm.getNote(), salary, status));
        return "redirect:/persons/"+perID;
    }
    @GetMapping("/signup")
    public String showSignUpForm(){
        return "signup";
    }
    @PostMapping("/create-person")
    public String signup(@ModelAttribute PersonForm personForm){
        LocalDate born= LocalDate.parse(personForm.getBorn(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate graduated= LocalDate.parse(personForm.getGraduated(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        double salary=Double.parseDouble(personForm.getSalary());
        personDao.addPerson(new Person(personForm.getName(), born, personForm.getMail(), graduated, personForm.getNote(), salary));

        return "redirect:/login";
    }
    @GetMapping("/max-salary-per")
    public String getMaxSalaryPer(Model model){
        List<Person> persons=personDao.getMaxSalaryPer();
        List<Person> personActive=new ArrayList<>();
        for (Person p:persons) {
            if(p.getStatus()!=0){
                personActive.add(p);
            }
        }
        model.addAttribute("persons", personActive);
        return "max-salary";
    }



}
