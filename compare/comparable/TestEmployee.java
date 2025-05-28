package comparable;


import java.util.*;

public class TestEmployee {
    public static void main(String[] args) {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee(101, "champ", 30));
        list.add(new Employee(102, "cherry", 25));
        list.add(new Employee(103, "chavan", 35));

        Collections.sort(list);

        System.out.println("Sorted Employees (by age descending):");
        for (Employee e : list) {
            System.out.println(e);
        }
    }
}
