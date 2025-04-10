import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение (например: III + VII):");
        System.out.println("пробелы важны в выражениях после первых цифр");
        String input = scanner.nextLine();

        try {
            System.out.println(calc(input));
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Неверный формат ввода.");
        }

        String num1 = parts[0];
        String operator = parts[1];
        String num2 = parts[2];

        boolean isArabic = isArabic(num1) && isArabic(num2);
        boolean isRoman = isRoman(num1) && isRoman(num2);

        if (!isArabic && !isRoman) {
            throw new IllegalArgumentException("Числа должны быть или арабскими, или римскими, но не смешанными.");
        }

        int number1 = 0;
        int number2 = 0;

        if (isArabic) {
            number1 = Integer.parseInt(num1);
            number2 = Integer.parseInt(num2);
        } else if (isRoman) {
            number1 = romanToInt(num1);
            number2 = romanToInt(num2);
        }

        int result = 0;
        switch (operator) {
            case "+":
                result = number1 + number2;
                break;
            case "-":
                result = number1 - number2;
                break;
            case "*":
                result = number1 * number2;
                break;
            case "/":
                if (number2 == 0) {
                    throw new ArithmeticException("Деление на ноль невозможно.");
                }
                result = number1 / number2;
                break;
            default:
                throw new IllegalArgumentException("Неверная операция.");
        }

        if (isRoman) {
            if (result <= 0) {
                throw new IllegalArgumentException("Римские числа не могут быть отрицательными или равными нулю.");
            }
            return intToRoman(result);
        } else {
            return String.valueOf(result);
        }
    }

    public static boolean isArabic(String str) {
        try {
            int num = Integer.parseInt(str);
            return num >= 1 && num <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isRoman(String str) {
        return str.matches("I|II|III|IV|V|VI|VII|VIII|IX|X");
    }

    public static int romanToInt(String roman) {
        switch (roman) {
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
            default: throw new IllegalArgumentException("Неверное римское число.");
        }
    }

    public static String intToRoman(int number) {
        switch (number) {
            case 1: return "I";
            case 2: return "II";
            case 3: return "III";
            case 4: return "IV";
            case 5: return "V";
            case 6: return "VI";
            case 7: return "VII";
            case 8: return "VIII";
            case 9: return "IX";
            case 10: return "X";
            default: throw new IllegalArgumentException("Римские числа от 1 до 10.");
        }
    }

}