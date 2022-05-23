package main.java.com.qualityunit.service;

import java.util.List;
import main.java.com.qualityunit.model.Query;

public interface QueryService {
    List<Query> processQueries(List<String> queries);
}
