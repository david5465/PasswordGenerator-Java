import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PasswordGenerator {
    private static final String LOWERCASE_LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE_LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+[]{}<>.,?";

    public static void main(String[] args) {
        int length = getPasswordLength();
        List<Character> characterTypes = getCharacterTypes();
        String password = generatePassword(length, characterTypes);
        System.out.println("Contraseña generada: " + password);
    }

    private static int getPasswordLength() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Longitud de la contraseña: ");
        return scanner.nextInt();
    }

    private static List<Character> getCharacterTypes() {
        List<Character> characterTypes = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Incluir números? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            characterTypes.add('N');
        }

        System.out.print("Incluir letras mayúsculas? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            characterTypes.add('L');
        }

        System.out.print("Incluir letras minúsculas? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            characterTypes.add('l');
        }

        System.out.print("Incluir símbolos? (s/n): ");
        if (scanner.nextLine().equalsIgnoreCase("s")) {
            characterTypes.add('S');
        }

        return characterTypes;
    }

    private static String generatePassword(int length, List<Character> characterTypes) {
        StringBuilder password = new StringBuilder();
        String validCharacters = buildValidCharacters(characterTypes);
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(validCharacters.length());
            password.append(validCharacters.charAt(randomIndex));
        }

        return password.toString();
    }

    private static String buildValidCharacters(List<Character> characterTypes) {
        StringBuilder validCharacters = new StringBuilder();

        for (Character characterType : characterTypes) {
            switch (characterType) {
                case 'N':
                    validCharacters.append(NUMBERS);
                    break;
                case 'L':
                    validCharacters.append(UPPERCASE_LETTERS);
                    break;
                case 'l':
                    validCharacters.append(LOWERCASE_LETTERS);
                    break;
                case 'S':
                    validCharacters.append(SYMBOLS);
                    break;
                default:
                    break;
            }
        }

        return validCharacters.toString();
    }
}
