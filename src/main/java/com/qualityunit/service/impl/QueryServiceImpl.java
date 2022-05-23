package main.java.com.qualityunit.service.impl;

import java.util.ArrayList;
import java.util.List;
import main.java.com.qualityunit.exception.DataProcessingException;
import main.java.com.qualityunit.model.Query;
import main.java.com.qualityunit.service.LineValidatorService;
import main.java.com.qualityunit.service.QueryService;

public class QueryServiceImpl implements QueryService {
    private final LineValidatorService lineValidatorService;
    private final List<Query> queryList = new ArrayList<>();

    public QueryServiceImpl(LineValidatorService lineValidatorService) {
        this.lineValidatorService = lineValidatorService;
    }

    @Override
    public List<Query> processQueries(List<String> queries) {
        for (String queryString : queries) {
            Query query = new Query();
            String[] splitQuery = queryString.split(" ");

            int requiredNumberOfQueryParameters = 5;
            if (splitQuery.length != requiredNumberOfQueryParameters) {
                throw new DataProcessingException("Invalid request!");
            } else {
                int indexOfServiceNumber = 1;
                String serviceNumber = splitQuery[indexOfServiceNumber];
                lineValidatorService.validateAndSaveServiceNumber(query, serviceNumber);

                int indexOfQuestionType = 2;
                String questionType = splitQuery[indexOfQuestionType];
                lineValidatorService.validateAndSaveQuestionType(query, questionType);

                int indexOfResponseType = 3;
                String responseType = splitQuery[indexOfResponseType];
                lineValidatorService.validateAndSaveResponseType(query, responseType);

                int indexOfDateFrom = 4;
                String dateFromAndDateTo = splitQuery[indexOfDateFrom];
                lineValidatorService.validateAndSaveDateFromAndDateTo(query, dateFromAndDateTo);

                queryList.add(query);
            }
        }
        return queryList;
    }
}
