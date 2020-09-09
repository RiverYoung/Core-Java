import java.time.LocalDate;
import java.util.Objects;

/*
*   在一个文件中，只能有一个 public 类，但可以有任意数目的非公共类
*   源文件名是 EmployeeTest.java，这是因为文件名必须与 public 类的名字相匹配；
* */
public class EmployeeTest {
    public static void main(String[] args){
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry", 50000, 1989, 10, 1);
        staff[2] = new Employee("George",4000, 1987,5,2);

        for (Employee e : staff) {
            e.raiseSalary(5);
        }

        for (Employee e : staff) {
            System.out.println("name = " + e.getName() + ", salary = " + e.getSalary() + ",hireDay = " + e.getHireDay());
        }
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
    *   C++ 中通常类的定义放在类的外面，如果在内部定义，这个方法会自动称为内联(inline)函数
    *   Java 中所有的方法都放在类的内部定义，但并不表示它们是内联方法。是否将某个方法设置称为内联方法是 Java 虚拟机的任务；
    *   即时编译器会监视这些简单，经常调用且没有被覆盖的方法调用，并进行优化
    * */
    public String getName() {
        return this.name;
    }

    /*
    *   尽管大多数方法都被设计成 public，但是在某些特殊情况下，将方法设计成 private 更合理。
    *   比如，有时你希望讲一个函数分解成若干个独立的辅助方法，通常这些辅助方法不应该成为 public 接口。
    *   这是因为它们往往与当前实现关系紧密，或者需要一个特殊协议或者调用次序。最好将这样的方法设计成 private。
    *   还有一个更重要的原因是私有方法可以保证不被其它代码依赖，当类的设计者不在需要这段代码的时候可以直接删除，而public 方法就无法保证。
    * */
    public double getSalary() {
        return this.salary;
    }

    public LocalDate getHireDay() {
        return this.hireDate;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
