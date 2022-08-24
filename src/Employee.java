public class Employee {

    private static int counter;
    private final int id;

    private final String fullName;

    private Department department;

    private double salary;

    public Employee(String fullName, Department department, double salary) {
        this.id = ++Employee.counter;
        this.fullName = fullName;
        this.department = department;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public Employee setDepartment(Department department) {
        this.department = department;
        return this;
    }

    public Employee setSalary(double salary) {
        this.salary = salary;
        return this;
    }

    @Override
    public String toString() {
        return String.format(
                "id: %s. Ф.И.О: %s. Департамент: %s. Запралата: %s",
                id,
                fullName,
                department,
                salary
        );
    }
}
