import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) {
        PersistentArray a = new PersistentArray();
        a.newarray();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(line);
                String command = st.nextToken();
                if (command.equals("unset")) {
                    if (a.stack.size() > 1) a.stack.pop();
                } else if (command.equals("set")) {
                    int i = Integer.parseInt(st.nextToken());
                    int value = Integer.parseInt(st.nextToken());
                    a = a.set(a, i, value);
                } else if (command.equals("get")) {
                    int i = Integer.parseInt(st.nextToken());
                    int value = a.get(a, i);
                    System.out.println(value);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
