import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;

public class Start {
    public static void main(String[] args) throws IOException {
        ColumnChooser columnChooser = new ColumnChooser();
        int column = columnChooser.chooser(args);
        System.out.println("Chosen column: " + column +
                "\nType below what you want to find: ");
        Scanner scanner = new Scanner(System.in);
        String mask = scanner.next();
        scanner.close();
        Instant start = Instant.now();
        Util util = new Util("src/main/resources/airports.dat");
        TreeSet<String[]> tree = util.search(column - 1, mask);
        Instant end = Instant.now();
        System.out.println("Results: " + tree.size() + " Time: " + Duration.between(start, end).toMillis() + "ms");
        tree.forEach(leaf -> System.out.println(Arrays.toString(leaf)));
    }
}
