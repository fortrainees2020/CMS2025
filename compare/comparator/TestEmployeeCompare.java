package comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class TestEmployeeCompare {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        
        employees.add(new Employee(101, "Priyanka", 23));
        employees.add(new Employee(106, "Nancy", 27));
        employees.add(new Employee(105, "Vibha", 21));
        
        System.out.println("Sorting by Name");
        
        //Tp sOrt the employees by name
        Collections.sort(employees, new NameComparator());
        
        Iterator<Employee> itr = employees.iterator();
        while (itr.hasNext()) {
            Employee emp = itr.next();
            System.out.println(emp.empId + " " + emp.name + " " + emp.age);
        }
        
        // Sort the employees by age
        System.out.println("Sorting by Age");
        Collections.sort(employees, new AgeComparator());
        
        Iterator<Employee> itr2 = employees.iterator();
        while (itr2.hasNext()) {
            Employee emp = itr2.next();
            System.out.println(emp.empId + " " + emp.name + " " + emp.age);
        }
    }
}
