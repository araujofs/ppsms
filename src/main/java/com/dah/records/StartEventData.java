package com.dah.records;

import java.time.LocalDate;

import com.dah.enums.Category;

public record StartEventData(String name, String city, LocalDate submissionDeadline, LocalDate start, LocalDate end,
    Category category,
    Integer reviewersPerArticle) {

}
