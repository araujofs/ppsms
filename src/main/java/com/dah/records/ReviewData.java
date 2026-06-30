package com.dah.records;

import com.dah.enums.Verdict;

public record ReviewData(Integer reviewId, String contribution, String criticism, Verdict verdict) {
    
}
