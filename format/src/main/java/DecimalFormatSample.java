import java.text.DecimalFormat;

public class DecimalFormatSample {
    public static void main(String[] args) {
        {
            DecimalFormat format = new DecimalFormat("####.000");
            System.out.println(format.format(1234.56));
            // 1234.560
        }
        {
            DecimalFormat format = new DecimalFormat("###,##0.00");
            System.out.println(format.format(24.7)); // 24.70
            System.out.println(format.format(121324.37)); // 121,324.37

        }
    }
}
