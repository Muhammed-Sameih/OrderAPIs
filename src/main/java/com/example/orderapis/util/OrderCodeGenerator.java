package com.example.orderapis.util;


import java.time.LocalDateTime;
import java.util.Random;

public class OrderCodeGenerator {

    public static String generateOrderCode() {
        LocalDateTime now = LocalDateTime.now();
        int randomNumber = generateRandomNumber(10000, 99999); // Generate a random number between 10000 and 99999
        return String.format("ORDER-%04d-%02d-%02d-%02d-%02d-%02d-%05d",
                now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                now.getHour(), now.getMinute(), now.getSecond(),
                randomNumber);
    }

    private static int generateRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
