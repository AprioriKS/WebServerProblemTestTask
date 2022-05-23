package main.java.com.qualityunit.service;


import java.util.List;
import main.java.com.qualityunit.model.TimeLine;

public interface TimeLineService {
    List<TimeLine> processTimeLines(List<String> timeLinesString);
}
