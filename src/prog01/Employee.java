package prog01;

public class Employee extends User {

    public final static String FORMAT = "%-20s %-20s %7.1f";

    public String department;
    public String rank;
    public double  salary;

    public Employee(String name, int age, Gender gender, String country, String mail, String department, String rank, double salary) {
        super(name, age, gender, country, mail);
        this.department = department;
        this.rank = rank;
        this.salary = salary;
    }

    @Override
    public String toString() {
        String format = "%s " + FORMAT;
        return String.format(format, super.toString(), department, rank, salary);
    }
}
