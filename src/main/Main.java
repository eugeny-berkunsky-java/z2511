package main;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.HOURS;

public class Main {

    public static void main(String[] args) {
	    new Main().run();
    }

    private void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader("dates.txt"))) {
            String line = reader.readLine();
            if (line == null) {
                System.out.println("Empty");
            } else {
                LocalDate ld = stringToDate(line);
                LocalDate min = ld;
                LocalDate max = ld;
                while ((line = reader.readLine())!=null) {
                    ld = stringToDate(line);
                    if (max.isBefore(ld)) max = ld;
                    if (min.isAfter(ld)) min = ld;
                }
                System.out.println(min.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
                System.out.println(max.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LocalDate stringToDate(String line) {
        String[] s = line.split(" ");
        int d = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int y = Integer.parseInt(s[2]);
        return LocalDate.of(y, m, d);
    }

    private void run3() {
        LocalDateTime dt = LocalDateTime.now();
        System.out.println(dt);
        LocalDateTime christmas = LocalDateTime.of(2020, 1, 7, 0, 0);
        System.out.println(christmas);
        long days = ChronoUnit.YEARS.between(dt,christmas);
        System.out.println(days);
        Date date = Date.from(dt.toInstant(ZoneOffset.UTC).minus(2, HOURS));
        System.out.println(date);
    }

    private void run2() {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get("file.txt"))) {
            List<String> list = reader.lines().collect(Collectors.toList());
            String[] strs = new String[list.size()];
            String[] strs2 = list.toArray(strs);
            for (String str : strs2) {
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void run1() {
        try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
            PrintWriter writer = new PrintWriter("out.txt")) {
            String line;
            while ((line = reader.readLine())!=null) {
                writer.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
