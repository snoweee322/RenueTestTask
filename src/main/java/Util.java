import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.TreeSet;
import java.util.logging.Logger;

public class Util {
    private static final Logger log = Logger.getLogger(Util.class.getName());
    public final String src;
    Util(String src) {
        this.src = src;
    }
    public TreeSet<String[]> search(int column, String mask) throws IOException {
        Comparator<String[]> comparator = (s1, s2) -> {
            int result = s1[column].compareTo(s2[column]);
            if (result == 0)
                result = s1[0].compareTo(s2[0]);
            return result;
        };
        TreeSet<String[]> tree = new TreeSet<>(comparator);
        try (BufferedReader bf = new BufferedReader(new FileReader(src))) {
            String rawString;
            String[] decodedString;
            while ((rawString = bf.readLine()) != null) {
                decodedString = rawString.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                if (decodedString.length != 14) {
                    throw new InvalidCSVDecodeException("CSV decoding error");
                }
                if (decodedString[column].matches("^\"?" + mask + ".*")) {
                    tree.add(decodedString);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.severe("Something is wrong with BufferedReader");
            throw e;
        }
        return tree;
    }
}
