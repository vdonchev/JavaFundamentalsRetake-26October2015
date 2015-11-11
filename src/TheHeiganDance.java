import java.util.Scanner;

public class TheHeiganDance {
    private static double heiganHealth = 3000000;
    private static int playerHealth = 18500;
    private static double playerDamage;
    private static String lastSpellName = "";
    private static boolean remainingSpell = false;
    private static int posX = 7;
    private static int posY = 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playerDamage = Double.parseDouble(scanner.nextLine());
        while (true) {
            heiganHealth -= playerDamage;
            if (remainingSpell) {
                playerHealth -= 3500;
                lastSpellName = "Plague Cloud";
                remainingSpell = false;
            }

            if (heiganHealth > 0) {
                if (playerHealth > 0) {
                    String[] token = scanner.nextLine().split("\\s+");
                    String spellName = token[0];
                    int spellX = Integer.parseInt(token[1]);
                    int spellY = Integer.parseInt(token[2]);
                    for (int row = spellX - 1; row <= spellX + 1; row++) {
                        for (int col = spellY - 1; col <= spellY + 1; col++) {
                            if (posX == row && posY == col) {
                                if (posX > 0) {
                                    if (tryToEscape(spellX, spellY, posX - 1, posY)) {
                                        posX--;
                                        continue;
                                    }
                                }

                                if (posY < 14) {
                                    if (tryToEscape(spellX, spellY, posX, posY + 1)) {
                                        posY++;
                                        continue;
                                    }
                                }

                                if (posX < 14) {
                                    if (tryToEscape(spellX, spellY, posX + 1, posY)) {
                                        posX++;
                                        continue;
                                    }
                                }

                                if (posY > 0) {
                                    if (tryToEscape(spellX, spellY, posX, posY - 1)) {
                                        posY--;
                                        continue;
                                    }
                                }

                                doSpell(spellName);
                            }
                        }
                    }
                }
            }

            if (printResults()) return;
        }
    }

    private static boolean printResults() {
        if (playerHealth <= 0 || heiganHealth <= 0) {
            if (heiganHealth <= 0) {
                System.out.printf("Heigan: Defeated!\n");
            } else {
                System.out.printf("Heigan: %.2f\n", heiganHealth);
            }

            if (playerHealth <= 0) {
                System.out.printf("Player: Killed by %s\n", lastSpellName);
            } else {
                System.out.printf("Player: %d\n", playerHealth);
            }

            System.out.printf("Final position: %d, %d\n", posX, posY);
            return true;
        }
        return false;
    }

    private static void doSpell(String spell) {
        if (spell.equals("Cloud")) {
            lastSpellName = "Plague Cloud";
            remainingSpell = true;
            playerHealth -= 3500;
        } else {
            lastSpellName = "Eruption";
            playerHealth -= 6000;
        }
    }

    private static boolean tryToEscape(int spellX, int spellY, int row, int col) {
        for (int i = spellX - 1; i <= spellX + 1; i++) {
            for (int j = spellY - 1; j <= spellY + 1; j++) {
                if (i == row && j == col) {
                    return false;
                }
            }
        }

        return true;
    }
}