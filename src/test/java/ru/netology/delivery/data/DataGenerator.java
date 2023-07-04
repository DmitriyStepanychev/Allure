package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        String date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

//    public static String generateCity(String locale) {
//        Faker faker = new Faker(new Locale(locale));
//        String city = faker.address().city();
//        return city;
//    }
    public static String generateCity() {
        var cities = new String[] {"Майкоп", "Горно-Алтайск", "Барнаул", "Благовещенск", "Архангельск", "Астрахань", "Уфа",
                "Белгород", "Брянск", "Улан-Удэ", "Владимир", "Волгоград", "Вологда", "Воронеж", "Махачкала", "Биробиджан",
                "Чита", "Иваново", "Магас", "Иркутск", "Нальчик", "Калининград", "Элиста", "Калуга", "Петропавловск-Камчатский",
                "Черкесск", "Петрозаводск", "Кемерово", "Киров", "Сыктывкар", "Кострома", "Краснодар", "Красноярск", "Симферополь",
                "Курган", "Курск", "Санкт-Петербург", "Липецк", "Магадан", "Йошкар-Ола", "Саранск", "Москва", "Мурманск", "Нарьян-Мар",
                "Нижний Новгород", "Великий Новгород", "Новосибирск", "Омск", "Оренбург", "Орёл", "Пенза", "Пермь", "Владивосток",
                "Псков", "Ростов-на-Дону", "Рязань", "Самара", "Саратов", "Якутск", "Южно-Сахалинск", "Екатеринбург", "Севастополь",
                "Владикавказ", "Смоленск", "Ставрополь", "Тамбов", "Казань", "Тверь", "Томск", "Тула", "Кызыл", "Тюмень", "Ижевск",
                "Ульяновск", "Хабаровск", "Абакан", "Ханты-Мансийск", "Челябинск", "Грозный", "Чебоксары", "Анадырь", "Салехард", "Ярославль"};
        return cities[new Random().nextInt(cities.length)];
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String name = lastName + " " + firstName;
        return name;
    }

    public static String generateSpecificName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String firstName = "Семён";
        String lastName = faker.name().lastName();
        String name = lastName + " " + firstName;
        return name;
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        String phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            UserInfo user = new UserInfo(generateCity(), generateName(locale), generatePhone(locale));
            return user;
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
