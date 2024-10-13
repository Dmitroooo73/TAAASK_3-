import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    // 1. Проверка на странную пару строк
    public static boolean isStrangePair(String str1, String str2) {
        return (str1.isEmpty() && str2.isEmpty()) || 
               (!str1.isEmpty() && !str2.isEmpty() &&
                str1.charAt(0) == str2.charAt(str2.length() - 1) &&
                str1.charAt(str1.length() - 1) == str2.charAt(0));
    }

    // 2. Скидка на товары
    public static List<String[]> sale(List<String[]> products, int discount) {
        List<String[]> result = new ArrayList<>();
        double factor = 1 - discount / 100.0;
        for (String[] product : products) {
            int discountedPrice = Math.max(1, (int) Math.round(Integer.parseInt(product[1]) * factor));
            result.add(new String[]{product[0], String.valueOf(discountedPrice)});
        }
        return result;
    }

    // 3. Попадание в мишень
    public static boolean successShoot(int x, int y, int r, int m, int n) {
        return Math.hypot(m - x, n - y) <= r;
    }

    // 4. Четность числа и суммы его цифр
    public static boolean parityAnalysis(int num) {
        int sum = String.valueOf(num).chars().map(c -> c - '0').sum();
        return (num % 2) == (sum % 2);
    }

    // 5. Игра "Камень, ножницы, бумага"
    public static String rps(String p1, String p2) {
        if (p1.equals(p2)) return "Tie";
        return (p1.equals("rock") && p2.equals("scissors") || 
                p1.equals("scissors") && p2.equals("paper") || 
                p1.equals("paper") && p2.equals("rock")) ? "Player 1 wins" : "Player 2 wins";
    }

    // 6. Мультипликативное постоянство
    public static int bugger(int num) {
        int count = 0;
        while (num >= 10) {
            num = String.valueOf(num).chars().reduce(1, (a, b) -> a * (b - '0'));
            count++;
        }
        return count;
    }

    // 7. Наибольшая стоимость инвентаря
    public static String mostExpensive(List<Object[]> inventory) {
        String maxItem = "";
        int maxCost = 0;
        for (Object[] item : inventory) {
            int totalCost = (int) item[1] * (int) item[2];
            if (totalCost > maxCost) {
                maxCost = totalCost;
                maxItem = (String) item[0];
            }
        }
        return "Наиб. общ. стоимость у предмета " + maxItem + " - " + maxCost;
    }

    // 8. Длина уникальных символов
    public static String longestUnique(String str) {
        String longest = "", current = "";
        for (char c : str.toCharArray()) {
            current = current.contains(String.valueOf(c)) ? current.substring(current.indexOf(c) + 1) + c : current + c;
            if (current.length() > longest.length()) longest = current;
        }
        return longest;
    }

    // 9. Префикс и суффикс
    public static boolean isPrefix(String word, String prefix) {
        return word.startsWith(prefix.replace("-", ""));
    }

    public static boolean isSuffix(String word, String suffix) {
        return word.endsWith(suffix.replace("-", ""));
    }

    // 10. Подходит ли кирпич в отверстие
    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        return (a <= w && b <= h) || (a <= h && b <= w) ||
               (b <= w && c <= h) || (b <= h && c <= w) ||
               (a <= w && c <= h) || (a <= h && c <= w);
    }

    public static void main(String[] args) {
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println("UTF-8 encoding is not supported!");
        }

        // Тестирование всех методов
        System.out.println(isStrangePair("ratio", "orator"));  // true
        System.out.println(isStrangePair("sparkling", "groups"));  // true
        System.out.println(isStrangePair("bush", "hubris"));  // false

        List<String[]> products = List.of(new String[]{"Laptop", "124200"}, new String[]{"Phone", "51450"}, new String[]{"Headphones", "13800"});
        sale(products, 25).forEach(p -> System.out.println(p[0] + ": " + p[1]));

        System.out.println(successShoot(0, 0, 5, 2, 2));  // true
        System.out.println(parityAnalysis(243));  // true
        System.out.println(rps("rock", "paper"));  // Player 2 wins

        System.out.println(bugger(999));  // 4

        List<Object[]> inventory = List.of(new Object[]{"Скакалка", 550, 8}, new Object[]{"Шлем", 3750, 4}, new Object[]{"Мяч", 2900, 10});
        System.out.println(mostExpensive(inventory));  // Наиб. общ. стоимость у предмета Мяч - 29000

        System.out.println(longestUnique("abcba"));  // abc
        System.out.println(isPrefix("automation", "auto-"));  // true
        System.out.println(doesBrickFit(1, 2, 2, 1, 1));  // false
    }
}
