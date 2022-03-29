import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.logging.Logger;

public class ColumnChooser {
    private static final Logger log = Logger.getLogger(ColumnChooser.class.getName());

    public int chooser(String[] args) throws IOException {
        if (args.length > 1) {
            throw new IllegalArgumentException();
        } else if (args.length == 0) {
            return getDefaultColumn();
        } else if (Integer.parseInt(args[0]) > 0 && Integer.parseInt(args[0]) < 15) {
            return Integer.parseInt(args[0]);
        } else {
            throw new IllegalArgumentException("Wrong column");
        }
    }

    private int getDefaultColumn() throws IOException {
        int column;
        try (Scanner scanner = new Scanner(Path.of("src/main/resources/column.txt"))) {
            if (scanner.hasNextInt()) {
                column = scanner.nextInt();
                if (column < 1 || column > 14) {
                    throw new IllegalArgumentException("Please, set the correct value at column.txt");
                }
                scanner.close();
                return column;
            } else {
                scanner.close();
                throw new IllegalArgumentException("Please, set the correct value at column.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("Something is wrong with Scanner");
            throw e;
        }
    }
}
