package com.dah.repository;

import java.util.Optional;
import com.dah.domain.SubmissionEvent;

public interface SubmissionEventRepository {

    SubmissionEvent saveCurrent(SubmissionEvent event);
    
    Optional<SubmissionEvent> findCurrent();
}
