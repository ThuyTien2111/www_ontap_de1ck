package vn.edu.iuh.fit.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @Column(name = "CarID")
    private long car_id;
    @Column(name = "Brand", columnDefinition = "varchar(50)")
    private String brand;
    @Column(name = "Color", columnDefinition = "tinyint(4)")
    private int color;
    @ManyToOne
    @JoinColumn(name = "PerID")
    private Person person;

    public Car(String brand, int color) {
        this.brand = brand;
        this.color = color;
    }

    public Car() {
    }

    public Car(long car_id, String brand, int color, Person person) {
        this.car_id = car_id;
        this.brand = brand;
        this.color = color;
        this.person = person;
    }

    public long getCar_id() {
        return car_id;
    }

    public void setCar_id(long car_id) {
        this.car_id = car_id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Car{" +
                "car_id=" + car_id +
                ", brand='" + brand + '\'' +
                ", color=" + color +
                ", person=" + person +
                '}';
    }
}
