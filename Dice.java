import java.util.Random;

public class Dice{
	public static int Roll() {
		Random rand = new Random();
		return 1 + rand.nextInt(6);
	}
}
