import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println(calc(readLine()));
    }

    public static String calc(String input) {
        String[] s = input.split(" ");
        if (s.length != 3) throw new IllegalStateException("Expected the following format -> '1 + 1' or -> 'I + V'");
        Integer a;
        Integer b;
        int otv;
        boolean intT;
        boolean aBool = isInt(s[0]);
        boolean bBool = isInt(s[2]);
        if (aBool && bBool) {
            a = Integer.parseInt(s[0]);
            b = Integer.parseInt(s[2]);
            intT = true;
        } else if (!aBool && !bBool) {
            a = romanToInt(s[0]);
            b = romanToInt(s[2]);
            intT = false;
        } else throw new IllegalStateException("Wrong expression type! Example '1 + 1' or 'V + II'");
        switch (s[1]) {
            case ("*") -> otv = a * b;
            case ("/") -> otv = a / b;
            case ("+") -> otv = a + b;
            case ("-") -> otv = a - b;
            default -> throw new IllegalStateException("Unexpected value: " + s[1]);
        }
        if (otv <= 0 && !intT) throw new IllegalStateException("answer less than 1");
        return intT ? String.valueOf(otv) : intToRoman(otv);
    }

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            if (num >= 10) {
                sb.append("X");
                num -=10;
            }
            if (num >= 9) {
                sb.append("IX");
                num -=9;
            }
            if (num >= 5) {
                sb.append("V");
                num -= 5;
            }
            if (num >= 4) {
                sb.append("IV");
                num -= 4;
            }
            if (num >= 1) {
                sb.append("I");
                num -= 1;
            }
        }

        return sb.toString();
    }

    public static int romanToInt(String str) {
        char[] array = str.toCharArray();
        int otv = 0;
        for (char c: array) {
            if (c == 'I') otv += 1;
            if (c == 'V' && otv == 1) otv += 3;
            else if(c == 'V' && otv == 0) otv += 5;
            if (c == 'X' && otv == 1) otv += 8;
            else if(c == 'X' && otv == 0) otv += 10;
            if (c != 'I' && c != 'V' && c != 'X') throw new IllegalStateException("Wrong roman number");
        }
        return otv;
    }

    public static boolean isInt(String str) {
        try {
            Integer d = Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public static String readLine() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter expression ");
        return br.readLine();
    }
}
