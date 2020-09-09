import java.time.LocalDate;

/**
 * @description Core Java Volume I -- Fundamentals (Chapter 4 : Object and class)
 *              显示当前月的日历，当前日期用*表示
 * @time 2020-9-9 22:49:46
 * @version V1.0.0
 * @author http://www.riveryoung.cn/
* */

public class CalenderTest {
    public static void main(String[] args){
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        date = date.minusDays(today - 1);
        int value = date.getDayOfWeek().getValue();

        System.out.println("Mon Tus Wed Thu Fri Sat Sun");
        for (int i = 0; i < value; i++) {
            System.out.print("  ");
        }
        while (date.getMonthValue() == month){
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today){
                System.out.printf("*");
            }else{
                System.out.printf(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1){
                System.out.println();
            }
        }

        if (date.getDayOfWeek().getValue() != 1){
            System.out.println();
        }
    }
}
