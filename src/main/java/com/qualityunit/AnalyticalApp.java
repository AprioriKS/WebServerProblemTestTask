package main.java.com.qualityunit;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import main.java.com.qualityunit.exception.DataProcessingException;
import main.java.com.qualityunit.service.AnalyticalService;
import main.java.com.qualityunit.service.LineValidatorService;
import main.java.com.qualityunit.service.QueryService;
import main.java.com.qualityunit.service.TimeLineService;
import main.java.com.qualityunit.service.impl.AnalyticalServiceImpl;
import main.java.com.qualityunit.service.impl.LineValidatorServiceImpl;
import main.java.com.qualityunit.service.impl.QueryServiceImpl;
import main.java.com.qualityunit.service.impl.TimeLineServiceImpl;

public class AnalyticalApp {
    private static final LineValidatorService lineValidatorService = new LineValidatorServiceImpl();
    private static final QueryService queryService = new QueryServiceImpl(lineValidatorService);
    private static final TimeLineService timeLineService =
            new TimeLineServiceImpl(lineValidatorService);
    private static final AnalyticalService analyticalService = new AnalyticalServiceImpl(
            queryService, timeLineService);

    private static final List<String> queries = new ArrayList<>();
    private static final List<String> timeLines = new ArrayList<>();
    private static final File dataToAnalyze = new File("src/main/resources/DataToAnalyze.txt");

    public static void main(String[] args) {
        Scanner scanner;
        try {
            scanner = new Scanner(dataToAnalyze);
        } catch (FileNotFoundException e) {
            throw new DataProcessingException("The specified path to the file could not be found!", e);
        }
        int numberOfLines = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < numberOfLines; i++) {
            String line = scanner.nextLine();
            if (line.startsWith("D")) {
                queries.add(line);
            } else if (line.startsWith("C")) {
                timeLines.add(line);
            } else {
                throw new DataProcessingException("The line must start with C or D symbol!");
            }
        }
        scanner.close();
        String[] analysis = analyticalService.analyze(timeLines, queries);
        for (String out:analysis) {
            System.out.println(out);
        }
    }
}
