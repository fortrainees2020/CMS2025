package comparable;

public class Employee implements Comparable<Employee> {
    int empId;
    String name;
    int age;

    public Employee(int empId, String name, int age) {
        this.empId = empId;
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Employee e) {// e1.compareTo(e2)
        if (this.age == e.age) // if e1 's age == e2'age
            return 0;
        else if (this.age < e.age)
            return -1; // descending order by age
        else
            return 1;
    }

    @Override
    public String toString() {
        return empId + " - " + name + " - " + age;
    }
}
