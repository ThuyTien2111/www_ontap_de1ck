package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.entity.Person;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PersonDao {
    private EntityManager manager;

    @Autowired
    public PersonDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean logIn(long user,String password){
        //pass la email
        Person person=manager.find(Person.class, user);
        if(person!=null&&person.getEmail().equalsIgnoreCase(password)){
            return true;
        }
        return false;
    }
    @Transactional
    public boolean addPerson(Person person){
        try{
            if(person.getStatus()<0||person.getStatus()>2){
                return false;
            }
            long totalPerson= (long) manager.createQuery("select count(p) from Person p").getSingleResult();
            person.setPer_id(totalPerson+1);
            person.setStatus(1);
            manager.persist(person);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean updatePerson(Person person){
        try{
            if(person.getStatus()<0||person.getStatus()>2){
                return false;
            }
            manager.merge(person);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean deletePerson(long id){
        try{
            Person person=manager.find(Person.class, id);
            person.setStatus(0);
            manager.merge(person);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public boolean activePerson(long id){
        try{
            Person person=manager.find(Person.class, id);
            person.setStatus(1);
            manager.merge(person);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    public Person getPerson(long id){
        try{
            return manager.find(Person.class, id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Person> getListPerson(){
        try{
            return manager.createQuery("select p from Person p", Person.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public Page<Person> getAll(int page, int size){
        try{
            List<Person> persons= manager.createQuery("select p from Person p", Person.class)
                    .setFirstResult(page * size)
                    .setMaxResults(size)
                    .getResultList();
            long totalPerson= (long) manager.createQuery("select count(p) from Person p").getSingleResult();
            return new PageImpl<>(persons, PageRequest.of(page, size), totalPerson);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Person> getMaxSalaryPer(){
        List<Person> list=new ArrayList<>();
        try {
            String sql="SELECT * FROM person \n" +
                    "WHERE Salary =(\n" +
                    "SELECT MAX(Salary) FROM person\n" +
                    ")";
            list=manager.createNativeQuery(sql, Person.class).getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    @Transactional
    public double calcInsurance(long perID){
        double rs=0;
        try {
            String sql="SELECT ROUND(\n" +
                    "CASE \n" +
                    "\tWHEN (YEAR(CURDATE())-YEAR(Born) <25) then \n" +
                    "\t\tCASE \n" +
                    "\t\t\tWHEN(YEAR(CURDATE())-YEAR(Graduated) <10) then 0.1*salary\n" +
                    "\t\t\tELSE 0.1*salary-0.1*salary*0.02\n" +
                    "\t\tEND \n" +
                    "\tWHEN (YEAR(CURDATE())-YEAR(Born) >=25) AND (YEAR(CURDATE())-YEAR(Born) <50) then \n" +
                    "\t\tCASE \n" +
                    "\t\t\tWHEN(YEAR(CURDATE())-YEAR(Graduated) <10) then 0.07*salary\n" +
                    "\t\t\tELSE 0.07*salary-0.07*salary*0.02\n" +
                    "\t\tEND \n" +
                    "\tELSE \n" +
                    "\t\tCASE \n" +
                    "\t\t\tWHEN(YEAR(CURDATE())-YEAR(Graduated) <10) then 0.05*salary\n" +
                    "\t\t\tELSE 0.05*salary-0.05*salary*0.02\n" +
                    "\t\tEND \n" +
                    "END, 2) \n" +
                    "AS insurance\n" +
                    "FROM person\n" +
                    "WHERE PerID=?";
            Query query=manager.createNativeQuery(sql);
            query.setParameter(1, perID);
            rs=Double.parseDouble(query.getSingleResult().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
