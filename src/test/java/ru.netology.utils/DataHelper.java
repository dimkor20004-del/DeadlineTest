package ru.netology.utils;

import com.github.javafaker.Faker;
import ru.netology.data.UserData;

import java.util.Locale;

public class DataHelper {

    private DataHelper() {
    }

    private static final Faker faker = new Faker(new Locale("en"));

    public static UserData getValidUser() {
        return new UserData("vasya", "qwerty123");
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }
}