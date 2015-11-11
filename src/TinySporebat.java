import java.util.Scanner;

public class TinySporebat {
    private static int health = 5800;
    private static int glowcaps = 0;
    private static final char EXTRA_LIFE = 'L';
    private static final int LIFE_POINTS = 200;
    private static final int SPOREBAT_PRICE = 30;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String token;
        while (!(token = scanner.nextLine()).equals("Sporeggar")) {
            for (int i = 0; i < token.length() - 1; i++) {
                if (token.charAt(i) == EXTRA_LIFE) {
                    health += LIFE_POINTS;
                } else {
                    health -= token.charAt(i);
                }

                if (health <= 0) {
                    System.out.printf("Died. Glowcaps: %d\n", glowcaps);
                    return;
                }
            }

            glowcaps += Character.getNumericValue(token.charAt(token.length() - 1));
        }

        System.out.printf("Health left: %d\n", health);
        if (glowcaps >= SPOREBAT_PRICE) {
            System.out.printf("Bought the sporebat. Glowcaps left: %d\n", glowcaps - SPOREBAT_PRICE);
        } else {
            System.out.printf("Safe in Sporeggar, but another %d Glowcaps needed.\n", SPOREBAT_PRICE - glowcaps);
        }
    }
}