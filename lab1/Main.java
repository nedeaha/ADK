import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Array a = new Array();
        String fileName = "test.txt";
        a.newarray();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts[0].equals("unset")) {
                    a.stack.pop();
                } else if (parts[0].equals("set")) {
                    int i = Integer.parseInt(parts[1]);
                    int value = Integer.parseInt(parts[2]);
                    a = a.set(a, i, value);
                } else if (parts[0].equals("get")) {
                    int i = Integer.parseInt(parts[1]);
                    int value = a.get(a, i);
                    //System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
