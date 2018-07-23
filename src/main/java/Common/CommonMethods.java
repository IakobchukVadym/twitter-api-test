package Common;

public class CommonMethods {
    public static int getRandInt(int min, int max) {
        int i = min + (int) (Math.random() * ((max - min) + 1));
        return i;
    }
}
