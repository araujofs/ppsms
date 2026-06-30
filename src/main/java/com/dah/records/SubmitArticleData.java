package com.dah.records;

import java.util.List;

public record SubmitArticleData(String title, String abstractText, List<Integer> authorsIds, List<Integer> areaIds) {

}
