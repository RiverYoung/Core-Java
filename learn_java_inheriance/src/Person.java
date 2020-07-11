/**
*   抽象类
*   关键字： abstract
*   Tips： 抽象类不能实例化；抽象方法充当着占位方法的角色，他们在子类中具体实现。
*
*   C++区别：在C++中有一种抽象方法称为纯虚函数(pure virtual function),要在末尾用 = 0 标记
 *          class Person{
 *              public:
 *                  virtual string getDescription() = 0;
 *          }
* */

public abstract class Person {
    public abstract String getDescription(); //包含一个或多个抽象方法的类本身必须声明为抽象类
    private String name; //最好将类中属性标记为private， 对其他类不可见，子类也无法访问
                         //有时候希望父类中的某个方法或者属性只允许子类访问，可以将其声明为受保护 protected

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
