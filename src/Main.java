import java.util.*;

public class Main {
    private static final int DEPARTMENT_SIZE = 5;
    private static final int EMPLOYEES_SIZE = 10;

    private static final EmployeeBook employeesBook = new EmployeeBook();


    public static void main(String[] args) throws Exception {

        Main.initialEmployees();

        System.out.println(Arrays.toString(employeesBook.getEmployees()));
        System.out.printf("Затраты на зарплаты в месяц: %s%n", employeesBook.getAmountSalaryEmployees());
        System.out.printf("Сотрудник с минимальной зарплатой: %s%n", employeesBook.getEmployeeWithMinimumSalary());
        System.out.printf("Сотрудник с максимальной зарплатой: %s%n", employeesBook.getEmployeeWithMaximumSalary());
        System.out.printf("Среднее значение зарплат: %s%n", employeesBook.getAvgSalary());
        System.out.printf("Ф. И. О. всех сотрудников: %s%n", Arrays.toString(employeesBook.getFullNameEmployees()));

        employeesBook.indexSalaryByPercentage(.25);
        System.out.println();
        System.out.printf("Сотрудник с минимальной зарплатой: %s%n", employeesBook.getEmployeeWithMinimumSalary());
        System.out.printf("Сотрудник с максимальной зарплатой: %s%n", employeesBook.getEmployeeWithMaximumSalary());

        System.out.println("Повышенная сложность");
        int workDepartment = 1;
        System.out.printf(
                "Сотрудник с минимальной зарплатой: %s%n",
                employeesBook.getEmployeeWithMinimumSalaryByDepartment(workDepartment)
        );
        System.out.printf(
                "Сотрудник с максимальной зарплатой: %s%n",
                employeesBook.getEmployeeWithMaximumSalaryByDepartment(workDepartment)
        );

        System.out.printf(
                "Затраты на зарплаты в месяц: %s, по департаменту №%s%n",
                employeesBook.getAmountSalaryEmployeesByDepartment(workDepartment),
                workDepartment
        );

        System.out.printf(
                "Среднее значение зарплат: %s, по департаменту №%s%n",
                employeesBook.getAvgSalary(workDepartment),
                workDepartment
        );

        float indexPercent = .5f;
        employeesBook.indexSalaryDepartmentByPercentage(workDepartment, indexPercent);
        employeesBook.printDepartmentEmployees(employeesBook.getEmployeesByDepartment(workDepartment));

        System.out.println();

        employeesBook.printExternalEmployees(employeesBook.getEmployeesWithLessSalaryByNumber(480));
        employeesBook.printExternalEmployees(employeesBook.getEmployeesWithGreaterThanSalaryByNumber(480));

        Employee employee = employeesBook.getEmployeeByFullName("Сотрудник 1");
        System.out.printf(
                "%n ФИО сотрудника в обработке: %s",
                employee.getFullName()
        );
        System.out.printf(
                "%n Запралата до манипуляций: %s",
                employee.getSalary()
        );
        System.out.printf(
                "%n Отдел до манипуляций: %s",
                employee.getDepartment()
        );

        double newSalary = 10;
        int newDepartmentNum = 10;
        employeesBook.changeSalaryEmployeeByFullName(employee.getFullName(), newSalary);
        employeesBook.changeDepartmentEmployeeByFullName(employee.getFullName(), new Department(newDepartmentNum));

        // Добавим доп пользователя в отдел №10
        employeesBook.add(
                new Employee(
                        String.format("Сотрудник %s", 101),
                        new Department(newDepartmentNum),
                        1
                )
        );


        System.out.printf(
                "%n Запралата после манипуляций: %s",
                employee.getSalary()
        );
        System.out.printf(
                "%n Отдел после манипуляций: %s",
                employee.getDepartment()
        );

        System.out.println();
        System.out.println(
                Arrays.toString(employeesBook.getEmployeeNamesByDepartment(newDepartmentNum))
        );
    }

    private static void initialEmployees() {

        for (int i = 0; i < EMPLOYEES_SIZE; i++) {
            int baseNum = i + 1;
            employeesBook.add(new Employee(
                    String.format("Сотрудник %s", baseNum),
                    new Department(baseNum % Main.DEPARTMENT_SIZE),
                    baseNum * 100
            ));
        }
    }


}