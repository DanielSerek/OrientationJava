import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.toMap;

public class Streams {
    public static void main(String[] args) {

        // Write a Stream Expression to get the even numbers from the following list:
        List<Integer> numbers = asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);

        numbers.stream().filter(x -> x % 2 == 0).forEach(System.out::println);

        // Using a new list
        List<Integer> evenNumbers = numbers.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());

        for (Integer number : evenNumbers) {
            System.out.println(number + " ");
        }

        System.out.println("**********************************************************");

        //Write a Stream Expression to get the squared value of the positive numbers from the following list:
        List<Integer> numbers2 = asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);
        numbers2.stream()
                .filter(x -> x > 0)
                .map(x -> x * x)
                .forEach(System.out::println);

        System.out.println("**********************************************************");

        //Write a Stream Expression to find which number squared value is more then 20 from the following list:
        List<Integer> numbers3 = asList(3, 9, 2, 8, 6, 5);
        numbers3.stream()
                .map(x -> x * x)
                .filter(x -> x > 20)
                .forEach(System.out::println);

        System.out.println("**********************************************************");

        //Write a Stream Expression to get the average value of the odd numbers from the following list:
        List<Integer> numbers4 = asList(1, 3, -2, -4, -7, -3, -8, 12, 19, 6, 9, 10, 14);
        IntSummaryStatistics stats = numbers4.stream()
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Average of all numbers is: " + stats.getAverage());

        System.out.println("**********************************************************");

        //Write a Stream Expression to get the sum of the odd numbers in the following list:
        List<Integer> numbers5 = asList(5, 9, 1, 2, 3, 7, 5, 6, 7, 3, 7, 6, 8, 5, 4, 9, 6, 2);
        IntSummaryStatistics stats2 = numbers5.stream()
                .filter((x) -> (x % 2) != 0)
                .mapToInt((x) -> x)
                .summaryStatistics();
        System.out.println("Summary of all numbers is: " + stats2.getSum());

        System.out.println("**********************************************************");

        //Write a Stream Expression to find the uppercase characters in a string!
        String s = "cAgjJKL";
        String upperCaseCharacters = s.chars()
                .filter(Character::isUpperCase)
                .collect(StringBuilder::new,
                        StringBuilder::appendCodePoint,
                        StringBuilder::append).toString();
        System.out.println(upperCaseCharacters);


        // Igorovo řešení
        String n6 = "AadfaHjfaoasdfOJ";
        System.out.println();
        List<Character> upperCases = n6.chars()
                .mapToObj(letter -> (char) letter)
                .filter(Character::isUpperCase)
                .collect(Collectors.toList());
        upperCases.stream()
                .forEach(System.out::print);

        System.out.println();
        System.out.println("**********************************************************");

        //Write a Stream Expression to find the strings which starts with a given letter(as parameter), in the following list:
        Character input = 'A';
        List<String> cities = asList("ROME", "LONDON", "NAIROBI", "CALIFORNIA", "ZURICH", "NEW DELHI", "AMSTERDAM", "ABU DHABI", "PARIS");
        cities.stream()
                .filter(x -> x.substring(0, 1).equals(input.toString()))
                .forEach(System.out::println);

        System.out.println("**********************************************************");

        // Write a Stream Expression to concatenate a Character list to a string!
        ArrayList<Character> characters = new ArrayList<>(asList('A', 'h', 'o', 'j'));
        String str = characters.stream().map(e -> e.toString()).collect(Collectors.joining());
        System.out.println(str);

        System.out.println("**********************************************************");

        //Write a Stream Expression to find the frequency of characters in a given string!
        String s3 = "AadfaHjfaoasdfOJ";
        Map<Character, Integer> charactersFrequency = s3.chars().boxed()
                .collect(toMap(k -> Character.valueOf((char) k.intValue()),
                        v -> 1,
                        Integer::sum));
        System.out.println(charactersFrequency);

        System.out.println("**********************************************************");

        //Create a Fox class with 3 properties:name, color and age Fill a list with at least 5 foxes and:
        //
        //Write a Stream Expression to find the foxes with green color!
        //Write a Stream Expression to find the foxes with green color, and age less then 5 years!
        //Write a Stream Expression to find the frequency of foxes by color!
        Fox fox1 = new Fox("Bystrouška", "rusty", 3);
        Fox fox2 = new Fox("Lišák", "grey", 2);
        Fox fox3 = new Fox("Chicken Killer", "black", 10);
        Fox fox4 = new Fox("Lovely Fox", "green", 1);
        Fox fox5 = new Fox("Green Fox", "green", 3);
        List<Fox> foxes = new ArrayList<>(asList(fox1, fox2, fox3, fox4, fox5));

        List<Fox> greenFoxes = foxes.stream().filter(x -> x.getColor() == "green").collect(Collectors.toList());
        for (Fox greenFox : greenFoxes) {
            System.out.println(greenFox.getName() + " " + greenFox.getColor() + " " + greenFox.getAge());
        }

        foxes.stream().filter(x -> x.getColor() == "green").map(x -> x.getName() + " " + x.getAge()).forEach(System.out::println);

        System.out.println("**********************************************************");

        foxes.stream().filter(x -> x.getColor() == "green" && x.getAge() < 5).map(x -> x.getName() + " " + x.getAge()).forEach(System.out::println);

        System.out.println("**********************************************************");

        Map<String, Integer> foxesByColor = foxes.stream()
                .collect(toMap(k -> k.getColor(),
                        v -> 1,
                        Integer::sum));
        System.out.println(foxesByColor);

        System.out.println("**********************************************************");

        //Find a random Wikipedia article and copy the contents to a txt file.
        //
        //Create a Stream expression which reads all text from this file, and prints the 100 most common words
        // in descending order. Keep in mind that the text contains punctuation characters which should be ignored.
        // The result should be something like this:
        String fileName = "src/iron.txt";

        try {
            Map<String, Long> wordsCounter = Files.lines(Paths.get(fileName))
                    .flatMap(st -> stream(st.split("\\P{L}")))
                    .collect(Collectors.groupingBy(Function.identity(), counting()))
                    .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .limit(100)
                    .collect(LinkedHashMap::new, (m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
            System.out.println(wordsCounter);

            System.out.println("**********************************************************");

            Files.lines(Paths.get(fileName))
                    .flatMap(st -> stream(st.split("\\W+"))) //Split lines into words \\P{L}
                    .collect(Collectors.groupingBy(Function.identity(), counting())) //Collect all the words and count them
                    .entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))//Sort in reversed order
                    .limit(100)
                    .forEach(x -> System.out.println(x.getKey() + " : " + x.getValue()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("**********************************************************");

        /*The swcharacters.csv file contains the list of characters appeared in the Star Wars universe.

        "BBY" stands for Before the Battle of Yavin, it's the "year zero" in the Galactic Standard Calendar. For the age calculation let's assume that it's "year zero" and every character lives for the sake of simplicity.

        Create Stream expressions to perform the following exercises:

        Print the name of the heaviest character (if the mass is unknown, ignore that character)
        Print the average height of the male characters
        Print the average height of the female characters
        💪 Get the age distribution of the characters by gender (where the gender can be "male", "female" and "other")
        The age groups are: "below 21", "between 21 and 40", "above 40" and "unknown"
        The result should be a Map<String, Map<String, Integer>>*/

        fileName = "src/swcharacters.csv";
        List<String> lines = null;

        try {
            lines = Files.readAllLines(Paths.get(fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }

        String[][] splitWords = new String[lines.size()][];

        for (int i = 1; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                splitWords[i] = lines.get(i).split(";");
            }
        }

//        Stream<String> stream1 = Arrays.stream(splitWords);
//        Optional<Integer> heaviest = stream1.map(a -> Integer.parseInt(a[3])).sorted(Comparator.reverseOrder()).findFirst();

    }
}
