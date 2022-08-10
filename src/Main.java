import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        EmployeeBook employeeBook = new EmployeeBook(4);
        Employee employee;
        String path = "src/test.txt";
        try (Scanner scanner = new Scanner(new FileInputStream(path))) {
            while (scanner.hasNext()) {
                String[] info = scanner.nextLine().split("\\s+");
                employee = new Employee(info[0], info[1], info[2], Integer.parseInt(info[3]), Double.parseDouble(info[4]));
                employeeBook.addSetDepartment(Integer.parseInt(info[3]));
                employeeBook.addEmployee(employee);
            }
        }
        // Изначально считаем что к нам приходит месячна зарплата
        double totalMonth = employeeBook.costAmount();
        System.out.printf("Сумма затрат по предприятию  %.2f руб.\n", totalMonth);
        System.out.println();
        double departmentMonth = employeeBook.costAmount(5);
        System.out.printf("Сумма затрат по отделу №%d  %.2f руб.\n", 5, departmentMonth);
        System.out.println();
        System.out.println(employeeBook.minimumSalary());
        System.out.println();
        System.out.println(employeeBook.minimumSalary(3));
        System.out.println();
        System.out.println(employeeBook.maximumSalary());
        System.out.println();
        System.out.println(employeeBook.maximumSalary(4));
        System.out.println();
        double avgMonth = employeeBook.avgSalary();
        System.out.printf("Среднее затрат по предприятию  %.2f руб.\n", avgMonth);
        System.out.println();
        double departmentAvg = employeeBook.avgSalary(5);
        System.out.printf("Среднее затрат по отделу №%d  %.2f руб.\n", 5, departmentAvg);
        System.out.println();
        employeeBook.indexSalary(10); // индексация з/п по предприятию
        System.out.println();
        employeeBook.indexSalary(15, 2); // индексация з/п по отделу
        System.out.println();
        employeeBook.printFullName(); //  Ф. И. О. всех сотрудников
        System.out.println();
        employeeBook.print(); // список всех сотрудников со всеми имеющимися по ним данными
        System.out.println();
        employeeBook.print(3); // Напечатать всех сотрудников отдела (все данные, кроме отдела)
        System.out.println();
        employeeBook.printMinimumSalaryTotal(50000);
        System.out.println();
        employeeBook.printMaximumSalaryTotal(60000);
        System.out.println();
        employeeBook.addEmployee(new Employee("Бревнов",
                "Пень", "Кустинович",3, 55000));
        employeeBook.print();
        System.out.println();
        employeeBook.removeEmployee(10);
        employeeBook.print();
        System.out.println();
        employeeBook.removeEmployee("Табуреткин Игорь Иванович");
        employeeBook.print();
        System.out.println();
        employeeBook.setDepartment("Петров Иван Петрович", 1);
        System.out.println(employeeBook.findEmployee("Петров Иван Петрович"));
        System.out.println();
        employeeBook.setSalary("Петрова Ольга Ивановна", 70000);
        System.out.println(employeeBook.findEmployee("Петрова Ольга Ивановна"));
        System.out.println();
        employeeBook.printAll();
    }
}