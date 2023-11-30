package vn.edu.iuh.fit.convert;

public class PersonForm {
    private String updPerID;
    private String name;
    private String born;
    private String mail;
    private String graduated;
    private String note;
    private String salary;
    private String status;

    public PersonForm() {
    }

    public PersonForm(String updPerID, String name, String born, String mail, String graduated, String note, String salary, String status) {
        this.updPerID = updPerID;
        this.name = name;
        this.born = born;
        this.mail = mail;
        this.graduated = graduated;
        this.note = note;
        this.salary = salary;
        this.status = status;
    }

    public String getUpdPerID() {
        return updPerID;
    }

    public void setUpdPerID(String updPerID) {
        this.updPerID = updPerID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getGraduated() {
        return graduated;
    }

    public void setGraduated(String graduated) {
        this.graduated = graduated;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PersonForm{" +
                "updPerID='" + updPerID + '\'' +
                ", name='" + name + '\'' +
                ", born='" + born + '\'' +
                ", mail='" + mail + '\'' +
                ", graduated='" + graduated + '\'' +
                ", note='" + note + '\'' +
                ", salary='" + salary + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
