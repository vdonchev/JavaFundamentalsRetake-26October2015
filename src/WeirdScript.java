import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WeirdScript {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int letterCode = Integer.parseInt(scanner.nextLine());
        String key = constructKey(letterCode);
        StringBuilder text = new StringBuilder();

        String token;
        while (!(token = scanner.nextLine()).equals("End")) {
            text.append(token);
        }

        Pattern pattern = Pattern.compile("(" + key + ")(.{0,50}?)\\1");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group(2));
        }
    }

    /**
     * Returns generated key based on the input integer
     *
     * @param num - Alphabet position of the letter
     * @return Constructed key
     */
    private static String constructKey(int num) {
        while (num > 52) {
            num -= 52;
        }

        if (num > 26) {
            return String.format("%c%c", (char) (num + 38), (char) (num + 38));
        }

        return String.format("%c%c", (char) (num + 96), (char) (num + 96));
    }
}