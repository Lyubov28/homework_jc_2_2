package com.company;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
// количество несовершеннолетних
        long count = persons.stream()
                .limit(30)
                .filter(value -> value.getAge() < 18)
                .count();
        System.out.println("Количество несовершеннолетних: " + count);

        //список фамилий призывников
        List<String> list = persons.stream()
                .limit(100)
                .filter(value -> value.getSex() == Sex.MAN && value.getAge() >= 18 && value.getAge() <= 27)
                .map(value -> value.getFamily())
                .collect(Collectors.toList());
        System.out.println("Список фамилий призывников:");
        System.out.println(list);

        //список потенциально работоспособных людей с высшим образованием
        List<Person> job = persons.stream()
                .limit(20)
                .filter(value -> (((value.getSex() == Sex.MAN) && value.getAge() <= 65)
                        || ((value.getSex() == Sex.WOMAN) && value.getAge() <= 60)) &&
                        (value.getEducation() == Education.HIGHER) && value.getAge() >= 18)
                .sorted(Comparator.comparing(value -> value.getFamily()))
                .collect(Collectors.toList());
        System.out.println("Список потенциально работоспособных людей с высшим образованием:");

        for (Person listWorker : job) {
            System.out.println(listWorker);
        }


    }
}
