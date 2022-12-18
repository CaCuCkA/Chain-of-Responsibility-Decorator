package ua.edu.ucu.apps.ocr;


import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException, IOException {
        System.out.println(new CachedDocument("gs://oop-course/Geico-2021.png").parse());
        new TimedDocument("gs://oop-course/Geico-2021.png").parse();
    }
}