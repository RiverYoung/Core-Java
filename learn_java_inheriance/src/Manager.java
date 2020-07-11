/**
 *  继承：inheriance   复用已有的类中的属性和方法，并增加新的属性和方法，使得类能适应新的情况；
 *                    已有的类称为父类(parent class)，新类称为子类(child class)
 *  关键字：extends
 *
 *  与C++的区别：       Java用关键字extends替代了C++中的冒号 ( : )，
 *                    在Java中，所有的继承都是公共继承，而没有C++中的私有继承和保护继承；
 *                    C++中，一个类可以有很多个父类；Java不支持多重继承，但是提供了类似多重继承的功能，详细查阅 接口 相关知识
 */

public class Manager extends Employee{

    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day); //1. 使用super调用构造器的语句，必须是子类构造器的第一条语句；
                                                //2. 如果子类的构造器没有显式的调用父类的构造器，将自动调用父类的无参构造器
                                                //3. C++构造器中，使用初始化列表调用父类的构造器
                                                //   Manager::Manager(String name, double salary, int year, int month, int day)
                                                //          :Employee(name, salary, year, month, day)
                                                //   {
                                                //      bouns = 0;
                                                //    }
        bonus = 0;
    }

    /*
    *   关键字 super
    *   父类中有些方法对子类并不适用，可以在子类中通过关键字 super 声明，提供一种新的方法覆盖(Override)父类中的方法
    * */
    public double getSalary() {
        double baseSalary = super.getSalary(); //java中适用关键字super调用父类的方法，而在C++中则采用操作符(::)，eg: Employee::getSalary
        //super和this引用很相似，但super只是一个指示编译器调用父类方法的特殊关键字，不是一个对象的引用
        return baseSalary + bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }
}
