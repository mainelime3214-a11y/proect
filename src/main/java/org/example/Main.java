package org.example;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://postgres:5432/app";
        String user = "app";
        String password = "pass";

        System.out.println(">>> Шаг 1: Ждем 5 секунд для стабилизации БД...");
        try {
            Thread.sleep(5000);

            System.out.println(">>> Шаг 2: Пробуем подключиться к " + url);
            try (Connection conn = DriverManager.getConnection(url, user, password)) {
                System.out.println(">>> Шаг 3: ПОДКЛЮЧЕНО!");

                try (Statement st = conn.createStatement()) {
                    // Создаем таблицу
                    st.execute("CREATE TABLE IF NOT EXISTS aqa_test (id SERIAL PRIMARY KEY, status VARCHAR(50))");
                    // Чистим старое и добавляем новое
                    st.execute("DELETE FROM aqa_test");
                    st.execute("INSERT INTO aqa_test (status) VALUES ('COMPLETED')");

                    // Проверяем
                    ResultSet rs = st.executeQuery("SELECT status FROM aqa_test");
                    if (rs.next()) {
                        System.out.println(">>> ШАГ 4: ДАННЫЕ В БД НАЙДЕНЫ: " + rs.getString("status"));
                        System.out.println(">>> ЗАДАНИЕ ВЫПОЛНЕНО УСПЕШНО!");
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("!!! ОШИБКА: " + e.getMessage());
        }
    }
}