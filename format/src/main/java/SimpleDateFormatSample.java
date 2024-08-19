import java.text.SimpleDateFormat;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;


public class SimpleDateFormatSample {
    public static void main(String[] args) {
        {
            SimpleDateFormat format = new SimpleDateFormat("G yyyy-MM-dd HH:mm:ss");
            System.out.println(format.format(-99999999999999L));
            // 2022-05-11 13:46:09
            System.out.println(format.format(System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1)));
            // 2022-05-10 13:46:09 输出相对时间
        }

        {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            format.setTimeZone(TimeZone.getTimeZone("GMT"));
            System.out.println(format.format(System.currentTimeMillis()));
            // 2022-05-11 13:46:09 与东八时区相差 8 小时
        }
    }
}
