package com.activitycalculator.util;

import javafx.scene.control.TextField;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FieldUtil {

    public static double textFieldToDouble(TextField textField) {
        String str = textField.getText().trim();
        double num = 0;
        try {
        if (!str.isEmpty()) {
            num = Double.parseDouble(str);
        }
    } catch (NumberFormatException e) {
            return  0;
    }
        return num;
    }

    public static LocalDateTime textFieldToLocalDateTime(TextField textField) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String str = textField.getText().trim();
        LocalDateTime dateTime = LocalDateTime.now();
        try {
            if (!str.isEmpty()) {
                 dateTime = LocalDateTime.parse(str, formatter);
            }
        } catch (DateTimeException e) {
            return  dateTime;
        }
        return dateTime;
    }

    public static String localDateTimeToString(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return localDateTime.format(formatter);
    }

}
