package com.dah.records;

import java.time.LocalDate;

public record PendingDetails(String articleTitle, String reviewerEmail, LocalDate dueDate) {
    
}
