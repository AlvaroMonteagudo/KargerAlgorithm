import java.util.Random;

public class RandomGenerator {

    private static Random rnd;
    private static char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public RandomGenerator() {
        rnd = new Random();
    }

    public Random getRnd() {
        return rnd;
    }

    /*
     * Return a random string
	 */
    public String stringRandom() {
        StringBuilder sb = new StringBuilder();
        char c;
        for (int i = 0; i < 8; i++) {
            c = chars[rnd.nextInt(chars.length)];
            sb.append(c);
        }
        return sb.toString();
    }
}
