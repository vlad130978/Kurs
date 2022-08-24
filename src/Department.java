public class Department {
    private final int departmentNum;

    public Department(int departmentNum) {
        this.departmentNum = departmentNum;
    }

    public int getDepartmentNum() {
        return departmentNum;
    }

    @Override
    public String toString() {
        return String.format(
                "Отдел №%s",
                departmentNum
        );
    }
}
