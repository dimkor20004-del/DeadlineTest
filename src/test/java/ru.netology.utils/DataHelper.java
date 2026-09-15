package ru.netology.utils;

import ru.netology.data.UserData;

public class DataHelper {

    private DataHelper() {
    }

    public static UserData getValidUser() {
        return new UserData("vasya", "qwerty123");
    }
}