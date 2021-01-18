package com.example.demo.helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.demo.domain.User;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    private static final String USER_LIST_FILE_PATH = "../../../../../resources/顧客データ";
    static String[] HEADERs = { "Id", "Name", "PhoneNumber", "Birthday", "Email", "PostalCode", "Address",
            "NumberOfPurChases", "LastPurchaseDate" };

    public static boolean hasCSVFormat(MultipartFile file) { // ファイル形式がCSVかどうかを確認する
        if (USER_LIST_FILE_PATH.equals(file.getContentType())
                || file.getContentType().equals("application/vnd.ms-excel")) {
            return true;
        }

        return false;
    }

    public static List<User> csvToUsers(InputStream is) throws NumberFormatException, ParseException {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                CSVParser csvParser = new CSVParser(fileReader,
                        CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<User> users = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

            for (CSVRecord csvRecord : csvRecords) {
                User user = new User(csvRecord.get("Id"), csvRecord.get("Name"), csvRecord.get("PhoneNumber"),
                        sdf1.parse(csvRecord.get("Birthday")), csvRecord.get("Email"), csvRecord.get("PostalCode"),
                        csvRecord.get("Address"), Integer.parseInt(csvRecord.get("NumberOfPurChases")),
                        sdf2.parse(csvRecord.get("LastPurchaseDate")));

                users.add(user);
            }

            return users;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream usersToCSV(List<User> users) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
                CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (User user : users) {
                List<String> data = Arrays.asList(user.getId(), user.getName(), user.getPhoneNumber(),
                        String.valueOf(user.getBirthday()), user.getEmail(), user.getPostalCode(), user.getAddress(),
                        String.valueOf(user.getNumberOfPurchases()), String.valueOf(user.getLastPurchaseDate()));

                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}
