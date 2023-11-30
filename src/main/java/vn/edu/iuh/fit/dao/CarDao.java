package vn.edu.iuh.fit.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.iuh.fit.entity.Car;
import vn.edu.iuh.fit.entity.Person;

import java.util.*;

@Repository
public class CarDao {
    private EntityManager manager;
    @Autowired
    public CarDao(EntityManager manager) {
        this.manager = manager;
    }
    @Transactional
    public boolean addCar(Car car, long perID){
        try {
            Person person=manager.find(Person.class, perID);
            long totalCar= (long) manager.createQuery("select count(c) from Car c").getSingleResult();
            car.setPerson(person);
            car.setCar_id(totalCar+1);
            manager.persist(car);
            return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    @Transactional
    public Map<String, Long> getTotalCarByBrand(){
        Map<String, Long> map=new HashMap<>();
        try {
            String sql="SELECT Brand, COUNT(*) AS total FROM car\n" +
                    "GROUP BY Brand";
            List<Object[]> objects=manager.createNativeQuery(sql, Object[].class).getResultList();
            for (Object[] obj:objects) {
                map.put((String) obj[0], (Long) obj[1]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return map;
    }
    @Transactional
    public Map<Person, Long> getTotalCarByPer(){
        Map<Person, Long> map=new HashMap<>();
        try {
            String sql="SELECT person.PerID, COUNT(*) AS total FROM car\n" +
                    "INNER JOIN person ON person.PerID=car.PerID\n" +
                    "GROUP BY person.PerID";
            List<Object[]> objects=manager.createNativeQuery(sql, Object[].class).getResultList();
            for (Object[] obj:objects) {
                map.put(manager.find(Person.class, (Long) obj[0]), (Long) obj[1]);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @Transactional
    public List<Car> getCarByPer(long perID){
        List<Car> list=new ArrayList<>();
        try{
            String sql="SELECT * FROM car\n" +
                    "WHERE PerID=?";
            Query query=manager.createNativeQuery(sql, Car.class);
            query.setParameter(1, perID);
            list=query.getResultList();
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
    @Transactional
    public long totalCar(long perID){
        long rs=0;
        try {
            String sql="SELECT COUNT(*) AS totalCar FROM car\n" +
                    "WHERE PerID=?";
            Query query=manager.createNativeQuery(sql);
            query.setParameter(1, perID);
            rs= Long.parseLong(query.getSingleResult().toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return rs;
    }
}
