public class ManagerTest {
    public static void main(String[] args){
        var boss = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        boss.setBonus(5000);

        var staff = new Employee[3];

        staff[0] = boss;
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tommy Tester", 40000, 1990, 3, 15);

        /*
        *   多态 polymorphism: 一种对象变量可以指示多种实际类型的现象；
        *   动态绑定 dynamic binding: 在运行时自动选择适当的方法；
        *   Tips: 这边的employee 既可以引用Employee对象，也可以引用Manager对象；并且在程序运行时，选择对应的getSalary方法
        *
        *   与C++的区别: 在C++中，如果希望实现动态绑定，需要将成员函数声明为virtual；
        *               在Java中，动态绑定是默认行为，如果不希望一个方法是虚拟的，可以将它标记为 final
        * */
        for (Employee employee : staff)
            System.out.println("name = " + employee.getName() + ",salary = " + employee.getSalary());
    }
}
