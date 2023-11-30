package vn.edu.iuh.fit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name ="person")
public class Person {
    @Id
    @Column(name = "PerID")
    private long per_id;
    @Column(name = "Name", columnDefinition = "varchar(50)")
    private String name;
    @Column(name = "Born")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate born;
    @Column(name = "Email", columnDefinition = "varchar(100)")
    private String email;
    @Column(name = "Graduated")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate graduated;
    @Column(name = "Note", columnDefinition = "varchar(500)")
    private String note;
    @Column(name = "Salary")
    private double salary;
    @Column(name = "Status", columnDefinition = "tinyint(2)")
    private int status;

    public Person() {
    }

    public Person(String name, LocalDate born, String email, LocalDate graduated, String note, double salary) {
        this.name = name;
        this.born = born;
        this.email = email;
        this.graduated = graduated;
        this.note = note;
        this.salary = salary;
    }

    public Person(long per_id, String name, LocalDate born, String email, LocalDate graduated, String note, double salary, int status) {
        this.per_id = per_id;
        this.name = name;
        this.born = born;
        this.email = email;
        this.graduated = graduated;
        this.note = note;
        this.salary = salary;
        this.status = status;
    }

    public long getPer_id() {
        return per_id;
    }

    public void setPer_id(long per_id) {
        this.per_id = per_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBorn() {
        return born;
    }

    public void setBorn(LocalDate born) {
        this.born = born;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getGraduated() {
        return graduated;
    }

    public void setGraduated(LocalDate graduated) {
        this.graduated = graduated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{" +
                "per_id=" + per_id +
                ", name='" + name + '\'' +
                ", born=" + born +
                ", email='" + email + '\'' +
                ", graduated=" + graduated +
                ", note='" + note + '\'' +
                ", salary=" + salary +
                ", status=" + status +
                '}';
    }
}
