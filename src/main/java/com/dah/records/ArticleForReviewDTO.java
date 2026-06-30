package com.dah.records;

import java.util.List;

public record ArticleForReviewDTO(Integer id, String title, String abstractText, List<String> areas) {

}
