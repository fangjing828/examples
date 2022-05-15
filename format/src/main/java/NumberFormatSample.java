import java.text.NumberFormat;

public class NumberFormatSample {
    public static void main(String[] args) {
        NumberFormat format = NumberFormat.getPercentInstance();
        format.setMinimumFractionDigits(2); // 保留到小数点后两位
        System.out.println(0.47); // 输出 47.00%
    }
}
