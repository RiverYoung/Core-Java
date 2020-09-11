package com.riveryoung.corejava;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @description Core Java Volume I -- Fundamentals (Chapter 4 : Object and class)
 *              用户自定义类的使用注意要点
 * @time 2020-9-11 00:12:54
 * @version V1.0.0
 * @author http://www.riveryoung.cn/
 * */
public class EmployeeTest {
    /*
     *   在一个文件中，只能有一个 public 类，但可以有任意数目的非公共类
     *   源文件名是 EmployeeTest.java，这是因为文件名必须与 public 类的名字相匹配；
     * */
    public static void main(String[] args){
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry", 50000, 1989, 10, 1);
        staff[2] = new Employee("George",4000, 1987,5,2);

        for (Employee e : staff) {
            e.setId();
            e.raiseSalary(5);
        }

        for (Employee e : staff) {
            System.out.println("name = " + e.getName() + ", salary = " + e.getSalary() + ",hireDay = " + e.getHireDay());
        }

        int n = Employee.getNextId();
        System.out.println("Next available id = " + n);

        /*
        *   Java 中方法参数的使用
        *   1. 方法不能修改基本数据类型的参数（即数值型和布尔型）；
        *   2. 方法可以改变对象参数的状态；
        *   3. 方法不能让一个对象参数引用另一个新的对象；
        *
        *   Test 1：Methods can't modify numeric parameters
        * */
        System.out.println("\nMethods can't modify numeric parameters");
        double percentage = 10;
        System.out.println("Before : percentage = " + percentage);
        tripleValue(percentage);
        System.out.println("After : percentage = " + percentage);

        /*
        *   Test2: Methods can change the state of object parameters
        * */
        System.out.println("\nMethods can change the state of object parameters");
        System.out.println("Before: salaryOfCarl = " + staff[0].getSalary());
        tripleSalary(staff[0]);
        System.out.println("After: salaryOfCarl = " + staff[0].getSalary());

        /*
        *   Test3: Methods can't attach new object to object parameters
        * */
        System.out.println("\nMethods can't attach new object to object parameters");
        System.out.println("Before: staff[0].getName() = " + staff[0].getName());
        System.out.println("Before: staff[1].getName() = " + staff[1].getName());
        swap(staff[0], staff[1]);
        System.out.println("After: staff[0].getName() = " + staff[0].getName());
        System.out.println("After: staff[1].getName() = " + staff[1].getName());

        staff[0] = new Employee("Carl", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry", 50000);
        staff[2] = new Employee();

        for (Employee e : staff) {
            System.out.println("name = " + e.getName() + ", salary = " + e.getSalary() + ",hireDay = " + e.getHireDay());
        }
    }

    private static void swap(Employee employee1, Employee employee2) {
        Employee temp = employee1;
        employee1 = employee2;
        employee2 = temp;
        System.out.println("End of method: employee1.getName() = " + employee1.getName());
        System.out.println("End of method: employee2.getName() = " + employee2.getName());
    }

    private static void tripleSalary(Employee employee) {
        employee.raiseSalary(200);
        System.out.println("End of method: salary = " + employee.getSalary());
    }

    private static void tripleValue(double percentage) {
        percentage *= 3;
        System.out.println("End of method: percentage = " + percentage);
    }
}

class Employee {
    /*
    * 可以将实例字段声明为 public，但这是一种很不好的做法。
    * 声明为 public 的数据字段允许程序中的任何地方对其进行读取与修改，这样子就完全破坏了封装。
    * 因此强烈建议将实例字段标记为 private
    **/
    private final String name;  //final 关键字 必须在构造对象时初始化，并且以后不会再修改
    private double salary;
    private LocalDate hireDate;
    /*
    *   静态字段 就是类中被声明 static 的字段，也称为 类字段（静态只是沿用了 C++ 的叫法，并无实际意义）。
    *   静态字段 属于类，每个类只有一个这样的字段，它不属于任何单个的对象。
    * */
    private static int nextId = 1;
    private int id;

    /*
    *   构造函数与类名同名，会在构造对象的时候执行，并初始化实例字段；
    *   每个类有一个以上的构造函数；
    *   构造函数可以有 0 个，1 个或多个参数，且无返回值；
    *   构造函数一般伴随着 new 操作符一起调用；
    *   不要在构造函数中定义与实例字段同名的局部变量；
    * */
    public Employee(String name, double salary, int year, int month, int day){
        /*
         *   “宽容型”方法：把 null 值转化为一个适当的非 null 值
         * */
        //this.name = Objects.requireNonNullElse(name, "unknown");

        /*
        *    “严格型”方法：严格要求参数不为null，否则抛出NullPointerException
        *   Exception in thread "main" java.lang.NullPointerException: The name cannot be null
        **/
        Objects.requireNonNull(name, "The name cannot be null");

        this.name = name;
        this.salary = salary;
        this.hireDate = LocalDate.of(year, month, day);
    }

    /*
    *   无参数构造函数
    *   1. 如果写了一个类时没有编写构造函数，那么系统会自动提供一个无参数构造函数；
    *   2. 如果类中提供了至少一个构造函数之后，但是没有提供无参数构造函数的话，那么构造函数的时候如果不提供参数就是不合法的；
    *   3. 默认字段初始化：如果构造函数没有显示的为字段设置初始值，所有的实例字段会被设置为默认值
    *       （数值为 0，布尔值为 false，对象引用为 null）；
    * */
    public Employee(){
        this.name = "unknown";
    }

    /*
    *   重载（overload） 如果多个方法有相同的名称，不同的参数，便出现了重载；
    *   Java 要完整的描述一个方法必须指定方法名以及参数类型，这个叫做方法的 签名（signature），返回类型不是签名的一部分；
    *   编译器会根据方法的签名找到对应的方法，查找匹配的过程被称为 重载解析（overloading resolution）
    * */
    public Employee(String name, double salary){
        this.name = name;
        this. salary = salary;
        this.hireDate = LocalDate.now();
    }

    /*
    *   C++ 中通常类的定义放在类的外面，如果在内部定义，这个方法会自动称为内联(inline)函数
    *   Java 中所有的方法都放在类的内部定义，但并不表示它们是内联方法。是否将某个方法设置称为内联方法是 Java 虚拟机的任务；
    *   即时编译器会监视这些简单，经常调用且没有被覆盖的方法调用，并进行优化
    * */
    public String getName() {
        return this.name;
    }

    public double getSalary() {
        return this.salary;
    }

    public LocalDate getHireDay() {
        return this.hireDate;
    }

    /*
     *   尽管大多数方法都被设计成 public，但是在某些特殊情况下，将方法设计成 private 更合理。
     *   比如，有时你希望讲一个函数分解成若干个独立的辅助方法，通常这些辅助方法不应该成为 public 接口。
     *   这是因为它们往往与当前实现关系紧密，或者需要一个特殊协议或者调用次序。最好将这样的方法设计成 private。
     *   还有一个更重要的原因是私有方法可以保证不被其它代码依赖，当类的设计者不在需要这段代码的时候可以直接删除，而public 方法就无法保证。
     * */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public int getId() {
        return id;
    }

    public void setId() {
        this.id = nextId;
        nextId++;
    }

    public static int getNextId() {
        return nextId; //return static field
    }

    public static void setNextId(int nextId) {
        Employee.nextId = nextId;
    }
}
