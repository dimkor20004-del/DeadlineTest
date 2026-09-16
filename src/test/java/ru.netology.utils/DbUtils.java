package ru.netology.utils;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

    private DbUtils() {
    }

    private static final String URL = "jdbc:mysql://localhost:3306/deadline";
    private static final String USER = "mrtotalsecurity";
    private static final String PASS = "CzmGtmRjc3cLGV7KXza294520qCMYXuF";

    public static String getVerificationCode(String login) {
        String sql = "SELECT code FROM auth_codes " +
                "JOIN users ON auth_codes.user_id = users.id " +
                "WHERE users.login = ? " +
                "ORDER BY auth_codes.created DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            QueryRunner runner = new QueryRunner();
            return runner.query(conn, sql, new ScalarHandler<>(), login);
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения кода подтверждения", e);
        }
    }

    public static void cleanDatabase() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            QueryRunner runner = new QueryRunner();
            runner.update(conn, "DELETE FROM auth_codes");
            runner.update(conn, "DELETE FROM card_transactions");
            runner.update(conn, "DELETE FROM cards");
            runner.update(conn, "DELETE FROM users");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка очистки базы данных", e);
        }
    }
}