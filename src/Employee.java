import java.util.Objects;

public class Employee {
    private String firstName;
    private String middleName;
    private String lastName;
    private String fullName;
    private double salary;
    private int id;
    private int department;

    public Employee(String lastName, String firstName, String middleName, int department, double salary) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.fullName = lastName + " " + firstName + " " + middleName;
        this.salary = salary;
        this.department = department;
        this.id = EmployeeBook.getId();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return fullName;
    }

    public int getId() {
        return id;
    }

    public double getSalary() {
        return salary;
    }

    public int getDepartment() {
        return department;
    }


    public void setSalary(Double salary) {
        if (salary instanceof Number && salary >= 0) {
            this.salary = salary;
        } else {
            System.out.println("Ввдено не корректное число");
        }
    }

    public void setDepartment(int department) {
        this.department = department;
    }


    @Override
    public String toString() {
        return String.format("Работник %-30s id= %-5d Отдел № %-5d зарплата    %-10.2f руб.", fullName, id, department,salary);
    }
}
