package OtherHomework;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        compare(1);
        compare(2);
        compare(5);
        compare(15);
    }

    public static void compare(int day) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, new int[day]);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] memory)
    {
        if (memory[day - 1] != 0)
            return memory[day - 1];
        else {
            int day1 = (startNumbers[startNumbers.length - 1] * startNumbers[startNumbers.length - 3]) % 10 + 1;
            if (day == 1) return day1;

            int day2 = (day1 * startNumbers[startNumbers.length - 2]) % 10 + 1;
            if (day == 2) return day2;

            int day3 = (day2 * startNumbers[startNumbers.length - 1]) % 10 + 1;
            if (day == 3) return day3;

            int day4 = (day3 * day1) % 10 + 1;
            if (day == 4) return day4;

            memory[0] = day1;
            memory[1] = day2;
            memory[2] = day3;
            memory[3] = day4;
            
            int prev = chooseHobbyRecursive(startNumbers, day - 1, memory);
            int prevPrePrev = chooseHobbyRecursive(startNumbers, day - 3, memory);
            
            memory[day - 1] = (prev * prevPrePrev) % 10 + 1;
            return (prev * prevPrePrev) % 10 + 1;
        }
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(numbers.size() - 1);
    }
}
