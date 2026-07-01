package com.dah.records;

import java.util.List;

public record CoordinatorDashboard(Integer submittedArticles, Integer reviewers, Integer evaluatedArticles,
    Integer pendingArticles, List<PendingDetails> pendingDetails) {

}
