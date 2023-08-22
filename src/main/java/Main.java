import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> people = getPeople();

        //Imperative Approach ❌
        System.out.println("Imperative Approach ❌");
        List<Person> females = new ArrayList<>();
        for (Person person : people) {
            if (person.getGender().equals(Gender.Female)) {
                females.add(person);
            }
        }
        //females.forEach(System.out::println);

        //Declarative Approach ✅
        System.out.println("\nDeclarative Approach ✅");
        //Filter
        List<Person> females1 = people.stream()
                .filter(person -> person.getGender().equals(Gender.Female))
                .collect(Collectors.toList());
        //females1.forEach(System.out::println);

        //Sort
        List<Person> sorted = people.stream()
                .sorted(Comparator.comparing(Person::getAge)
                        .thenComparing(Person::getGender)
                        .reversed())
                .collect(Collectors.toList());
        //sorted.forEach(System.out::println);

        //All match
        boolean allMatch = people.stream()
                .allMatch(person -> person.getAge() > 2);
        //System.out.println("allMatch = " + allMatch);

        //Any match
        boolean anyMatch = people.stream()
                .anyMatch(person -> person.getAge() > 2);
        //System.out.println("anyMatch = " + anyMatch);

        //None match
        boolean noneMatch = people.stream()
                .noneMatch(person -> person.getName().equals("Dn"));
        //System.out.println("noneMatch = " + noneMatch);

        //Max
        people.stream()
                .max(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);
        ;

        //Min
        people.stream()
                .min(Comparator.comparing(Person::getAge))
                .ifPresent(System.out::println);

        //Group
        Map<Gender, List<Person>> genderListMap = people.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        genderListMap.forEach((gender, people1) -> {
            System.out.println(gender);
            people1.forEach(System.out::println);
            System.out.println();
        });
    }

    private static List<Person> getPeople() {
        return List.of(
                new Person("Dn", 42, Gender.Male),
                new Person("Julia", 47, Gender.Female),
                new Person("Lora", 3, Gender.Female)
        );
    }
}
