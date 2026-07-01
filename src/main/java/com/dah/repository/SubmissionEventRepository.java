package com.dah.repository;

import java.util.Optional;

public interface SubmissionEventRepository {

    void saveCurrent(SubmissionEvent event);
    
    Optional<SubmissionEvent> findCurrent();
}
