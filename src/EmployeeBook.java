import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EmployeeBook {
    private final int step = 10;
    private int size = 0;
    private Employee[] employees = new Employee[step];

    public EmployeeBook add(Employee employee) {
        this.employees[size++] = employee;

        if (this.size >= this.employees.length) {
            Employee[] extendEmployees = new Employee[this.employees.length + step];
            for (int i = 0; i < this.size; i++) {
                extendEmployees[i] = this.employees[i];
            }
            this.employees = extendEmployees;
        }

        return this;
    }

    public void remove(String fullName) {
        this.removeByIndex(this.getIndexByFullName(fullName));
    }

    public void remove(int employeeId) {
        this.removeByIndex(this.getIndexByEmployeeId(employeeId));
    }

    public void removeByIndex(int index) {
        if (index < 0) {
            return;
        }

        this.employees[index] = null;
        if (this.size > index) {
            for (int i = index; i < this.size - 1; i++) {
                this.employees[i] = this.employees[i + 1];
            }
        }
        this.size--;
    }


    private int getIndexByFullName(String fullName) {
        for (int i = 0; i < this.size; i++) {
            if (this.employees[i].getFullName().equals(fullName)) {
                return i;
            }
        }

        return -1;
    }

    private int getIndexByEmployeeId(int employeeId) {
        for (int i = 0; i < this.size; i++) {
            if (this.employees[i].getId() == employeeId) {
                return i;
            }
        }

        return -1;
    }


    public Employee[] getEmployees() {
        return this.employees;
    }

    public double getAmountSalaryEmployees() {
        double amountSalaries = 0;

        for (int i = 0; i < this.size; i++) {
            amountSalaries += this.employees[i].getSalary();
        }

        return amountSalaries;
    }

    public Employee getEmployeeWithMinimumSalary() {
        Employee minSalaryEmployee = this.employees[0];
        for (int i = 0; i < this.size; i++) {
            if (minSalaryEmployee.getSalary() > this.employees[i].getSalary()) {
                minSalaryEmployee = this.employees[i];
            }
        }

        return minSalaryEmployee;
    }

    public Employee getEmployeeWithMaximumSalary() {
        Employee maxSalaryEmployee = this.employees[0];
        for (int i = 0; i < this.size; i++) {
            if (maxSalaryEmployee.getSalary() < this.employees[i].getSalary()) {
                maxSalaryEmployee = this.employees[i];
            }
        }

        return maxSalaryEmployee;
    }

    public double getAvgSalary() {
        double amountSalaryEmployees = this.getAmountSalaryEmployees();

        return amountSalaryEmployees / this.size;
    }

    public String[] getFullNameEmployees() {
        String[] fullNameEmployees = new String[this.size];
        for (int i = 0; i < this.size; i++) {
            fullNameEmployees[i] = this.employees[i].getFullName();
        }

        return fullNameEmployees;
    }

    /**
     * #TODO Уровень 2
     */
    public void indexSalaryByPercentage(double percent) throws Exception {

        if (0 > percent || percent > 1) {
            throw new Exception("Не корректное значение процента индексации ЗП");
        }

        percent += 1;

        for (int i = 0; i < this.size; i++) {
            double currentSalary = this.employees[i].getSalary();
            this.employees[i].setSalary(currentSalary * percent);
        }
    }

    public Employee[] getEmployeesByDepartment(Integer department) {
        ArrayList<Employee> employeesDepartment = new ArrayList<>();

        for (int i = 0; i < this.size; i++) {
            if (department.equals(this.employees[i].getDepartment().getDepartmentNum())) {
                employeesDepartment.add(this.employees[i]);
            }
        }

        return employeesDepartment.toArray(new Employee[0]);
    }

    public Employee getEmployeeWithMinimumSalaryByDepartment(Integer department) {
        Employee minSalaryEmployee = null;
        for (Employee employee : this.getEmployeesByDepartment(department)) {
            if (Objects.isNull(minSalaryEmployee)) {
                minSalaryEmployee = employee;
                continue;
            }

            if (minSalaryEmployee.getSalary() > employee.getSalary()) {
                minSalaryEmployee = employee;
            }
        }

        return minSalaryEmployee;
    }

    public Employee getEmployeeWithMaximumSalaryByDepartment(Integer department) {
        Employee maxSalaryEmployee = null;
        for (Employee employee : this.getEmployeesByDepartment(department)) {
            if (Objects.isNull(maxSalaryEmployee)) {
                maxSalaryEmployee = employee;
                continue;
            }
            if (maxSalaryEmployee.getSalary() < employee.getSalary()) {
                maxSalaryEmployee = employee;
            }
        }

        return maxSalaryEmployee;
    }

    public double getAmountSalaryEmployeesByDepartment(Integer department) {
        double amountSalaries = 0;

        for (Employee employee : this.getEmployeesByDepartment(department)) {
            amountSalaries += employee.getSalary();
        }

        return amountSalaries;
    }

    public double getAvgSalary(Integer department) {
        Employee[] departmentEmployees = this.getEmployeesByDepartment(department);
        double amountSalaryEmployees = this.getAmountSalaryEmployeesByDepartment(department);

        return amountSalaryEmployees / departmentEmployees.length;
    }

    public void indexSalaryDepartmentByPercentage(Integer department, double percent) throws Exception {

        if (0 > percent || percent > 1) {
            throw new Exception("Не корректное значение процента индексации ЗП");
        }

        percent += 1;

        for (Employee employee : this.getEmployeesByDepartment(department)) {
            double currentSalary = employee.getSalary();
            employee.setSalary(currentSalary * percent);
        }
    }

    public void printDepartmentEmployees(Employee[] employees) {
        String[] result = new String[employees.length];
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            result[i] = String.format(
                    "Ф.И.О: %s. Запралата: %s",
                    employee.getFullName(),
                    employee.getSalary()
            );
        }
        System.out.println(Arrays.toString(result));
    }

    public Employee[] getEmployeesWithLessSalaryByNumber(double number) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            if (this.employees[i].getSalary() < number) {
                employees.add(this.employees[i]);
            }
        }
        return employees.toArray(new Employee[0]);
    }

    public Employee[] getEmployeesWithGreaterThanSalaryByNumber(double number) {
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < this.size; i++) {
            if (this.employees[i].getSalary() > number) {
                employees.add(this.employees[i]);
            }
        }
        return employees.toArray(new Employee[0]);
    }

    public void printExternalEmployees(Employee[] employees) {
        String[] result = new String[employees.length];
        for (int i = 0; i < employees.length; i++) {
            Employee employee = employees[i];
            result[i] = String.format(
                    "id: %s. Ф.И.О: %s. Запралата: %s",
                    employee.getId(),
                    employee.getFullName(),
                    employee.getSalary()
            );
        }

        System.out.println(Arrays.toString(result));
    }

    public Employee getEmployeeByFullName(String fullName) {
        int index = this.getIndexByFullName(fullName);
        if (index < 0) {
            return null;
        }

        return this.employees[index];
    }

    public boolean changeSalaryEmployeeByFullName(String fullName, double newSalary) {
        Employee employee = this.getEmployeeByFullName(fullName);
        if (Objects.isNull(employee)) {
            return false;
        }

        employee.setSalary(newSalary);
        return true;
    }

    public boolean changeDepartmentEmployeeByFullName(String fullName, Department newDepartment) {
        Employee employee = this.getEmployeeByFullName(fullName);
        if (Objects.isNull(employee)) {
            return false;
        }

        employee.setDepartment(newDepartment);
        return true;
    }

    public String[] getEmployeeNamesByDepartment(Integer department) {
        Employee[] employees = this.getEmployeesByDepartment(department);
        String[] resNames = new String[employees.length];
        for (int i = 0; i < employees.length; i++) {
            resNames[i] = employees[i].getFullName();
        }

        return resNames;
    }
}
