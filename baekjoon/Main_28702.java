import java.io.*;
import java.util.*;

public class Main_28702 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int result = 0;

        for (int i = 0; i < 3; i++) {
            String input = br.readLine();

            if (input.equals("Fizz") || input.equals("Buzz") || input.equals("FizzBuzz")) {
                continue;
            } else {
                result = Integer.parseInt(input) + (3 - i);
            }
        }

        if (result % 3 == 0 && result % 5 == 0) System.out.println("FizzBuzz");
        else if (result % 3 == 0) System.out.println("Fizz");
        else if (result % 5 == 0) System.out.println("Buzz");
        else System.out.println(result);
    }
}
