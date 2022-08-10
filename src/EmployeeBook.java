import java.util.*;

public class EmployeeBook {

    private Employee[] employeeBook;
    private int amount;
    private static int id = 1;

    private Set<Integer> setDep = new HashSet<>();

    public EmployeeBook(int size) {
        this.employeeBook = new Employee[size];
    }

    public static int getId() {
        return id;
    }


    private Set<Integer> getSetDepartment() {
        return setDep;
    }

    // Добавление отдела в множество
    public void addSetDepartment(int department) {
        setDep.add(department);
    }

    // Минимальная зарплата по предприятию

    public Employee minimumSalary() {
        Employee min = null;
        double minimum = Double.MAX_VALUE;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getSalary() < minimum) {
                minimum = employee.getSalary();
                min = employee;
            }
        }
        return min;
    }
    // Минимальная зарплата по отделу

    public Employee minimumSalary(int department) {
        Employee min = null;
        double minimum = Double.MAX_VALUE;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getDepartment() == department && employee.getSalary() < minimum) {
                minimum = employee.getSalary();
                min = employee;
            }
        }
        return min;
    }
    // Максимальная зарплата по предприятию

    public Employee maximumSalary() {
        Employee max = null;
        double maximum = Double.MIN_VALUE;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getSalary() > maximum) {
                maximum = employee.getSalary();
                max = employee;
            }
        }
        return max;
    }
    // Максимальная зарплата по отделу

    public Employee maximumSalary(int department) {
        Employee max = null;
        double maximum = Double.MIN_VALUE;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getDepartment() == department && employee.getSalary() > maximum) {
                maximum = employee.getSalary();
                max = employee;
            }
        }
        return max;
    }

    // Ощие затраты по предприятию
    public double costAmount() {
        double total = 0;
        for (Employee employee : employeeBook) {
            if (employee != null) {
                total += employee.getSalary();
            }
        }
        return total;
    }

    // Ощие затраты по отделу
    public double costAmount(int department) {
        double total = 0;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getDepartment() == department) {
                total += employee.getSalary();
            }
        }
        return total;
    }

    // Средняя зарплата по предприятию

    public double avgSalary() {
        return (costAmount() / amount);
    }

    // Средняя зарплата по отделу
    public double avgSalary(int department) {
        int count = 0;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getDepartment() == department) {
                count++;
            }
        }
        return (costAmount(department) / count);
    }

    // Индексация зарплаты по всему предприятию

    public void indexSalary(int percent) {
        double coefficient = (double) percent / 100 + 1;
        for (Employee employee : employeeBook) {
            if (employee != null) {
                employee.setSalary(employee.getSalary() * coefficient);
            }
        }
    }
    // Индексация зарплаты по отделам

    public void indexSalary(int percent, int department) {
        double coefficient = (double) percent / 100 + 1;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getDepartment() == department) {
                employee.setSalary(employee.getSalary() * coefficient);
            }
        }
    }
    // Печать сотрудников с зарплатой менее

    public void printMinimumSalaryTotal(double minimum) {
        int count = 0;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getSalary() < minimum) {
                if (count == 0) {
                    System.out.printf("Сотрудники с зарплатой менее %.2f руб:\n", minimum);
                    count++;
                }
                System.out.printf("%d  %-30s  %-10.2f\n", employee.getId(), employee.getFullName(), employee.getSalary());
                count++;
            }
        }
        if (count == 0) {
            System.out.println(("Сотрудников с зарплатой менее " + minimum + " на предприятии нет"));
        }
    }

    // Печать сотрудников с зарплатов более или равной

    public void printMaximumSalaryTotal(double maximum) {
        int count = 0;
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getSalary() >= maximum) {
                if (count == 0) {
                    System.out.printf("Сотрудники с зарплатой равной или более %.2f руб:\n", maximum);
                    count++;
                }
                System.out.printf("%d  %-30s  %-10.2f\n", employee.getId(), employee.getFullName(), employee.getSalary());
                count++;
            }
        }
        if (count == 0) {
            System.out.println(("Сотрудников с зарплатой равной или более " + maximum + " на предприятии нет"));
        }
    }


    // Добавление сотрудника
    public void addEmployee(Employee employee) {
        for (int i = 0; i < employeeBook.length; i++) {
            if (employeeBook[i] == null) {
                employeeBook[i] = employee;
                id++;
                amount++;
                return;
            }
        }
        employeeBook = allocate(employeeBook);
        addEmployee(employee);
    }

    // Выделение пвмяти (содание нового массива и копирование старого)

    private Employee[] allocate(Employee[] employeeBook) {
        int newLength = (int) (employeeBook.length * 1.5);
        Employee[] tmp = new Employee[newLength];
        System.arraycopy(employeeBook, 0, tmp, 0, employeeBook.length);
        return tmp;
    }

    // Удаление сотрудника по Ф.И.О.

    public void removeEmployee(String fulName) {
        for (int i = 0; i < employeeBook.length; i++) {
            if (employeeBook[i] != null && fulName.equals(employeeBook[i].getFullName())) {
                employeeBook[i] = null;
                amount--;
                return;
            }
        }
        System.out.println("Такого работника нет");
    }

    // Удаление сотрудника по id

    public void removeEmployee(int id) {
        for (int i = 0; i < employeeBook.length; i++) {
            if (employeeBook[i] != null && id == employeeBook[i].getId()) {
                employeeBook[i] = null;
                amount--;
                return;
            }
        }
        System.out.println("Такого работника нет");
    }

    // Изменение по Ф.И.О номера отдела
    public void setDepartment(String fullName, int department) {
        if (!setDep.contains(department)) {
            System.out.printf("Отдела №%d не существует\n", department);
            return;
        }
        if (findEmployee(fullName) != null) {
            findEmployee(fullName).setDepartment(department);
            return;
        }
        System.out.println(fullName + " не является нашим работником");
    }

    // Изменение по Ф.И.О зарплаты
    public void setSalary(String fullName, double salary) {
        if (findEmployee(fullName) != null) {
            findEmployee(fullName).setSalary(salary);
            return;
        }
        System.out.println(fullName + " не является нашим работником");
    }

    // Печать Ф.И.О. всего предприятия

    public void printFullName() {
        for (Employee employee : employeeBook) {
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }


    // Печать сотрудников
    public void print() {
        for (Employee employee : employeeBook) {
            if (employee != null) {
                System.out.println(employee);
            }
        }
    }

    // Печать сотрудников отдела без отдела
    public void print(int department) {
        if (!setDep.contains(department)) {
            System.out.println("Такого отдела не существует");
        }
        System.out.println(("Сотрудники отдела № " + department));
        for (Employee employee : employeeBook) {
            if (employee != null && employee.getDepartment() == department) {
                System.out.printf("%-30s  id= %-5d зарплата    %-10.2f руб.\n", employee.getFullName(), employee.getId(), employee.getSalary());
            }
        }
    }
    // Получить Ф. И. О. всех сотрудников по отделам (напечатать список отделов и их сотрудников).
    public void printAll() {
        System.out.println("Список отделов");
        setDep.forEach(s -> System.out.println("Отдел №" + s));
        System.out.println();
        Arrays.sort(employeeBook, new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                if (o1 != null && o2 != null) {
                    return o1.getDepartment() - o2.getDepartment();
                }
                return 0;
            }
        });
        int current = 0;
        for (Employee employee : employeeBook) {
            if (employee != null && current != employee.getDepartment()) {
                System.out.println("Отдел №" + employee.getDepartment());
                current++;
            }
            if (employee != null) {
                System.out.println(employee.getFullName());
            }
        }
    }

    // Поиск сотрудника

    public Employee findEmployee(String fullName) {
        for (Employee employee : employeeBook) {
            if (employee != null && fullName.trim().equalsIgnoreCase(employee.getFullName())) {
                return employee;
            }
        }
        return null;
    }
}
