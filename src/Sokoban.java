import java.util.Scanner;

public final class Sokoban {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		for (int index_0 = 0; index_0 < t; index_0++) {
			int n = sc.nextInt();
			double[] bs = new double[n];
			boolean[] cs = new boolean[n];
			for (int index_1 = 0; index_1 < n; index_1++) {
				bs[index_1] = sc.nextDouble();
				cs[index_1] = sc.nextBoolean();
			}
		}
		char c = sc.next().charAt(0);
		String hey = sc.next();
	}
}
