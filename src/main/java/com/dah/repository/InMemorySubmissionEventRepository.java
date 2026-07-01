package com.dah.repository;

import java.util.Optional;

import com.dah.domain.SubmissionEvent;

public class InMemorySubmissionEventRepository implements SubmissionEventRepository {
    private SubmissionEvent currentEvent;

    @Override
    public SubmissionEvent saveCurrent(SubmissionEvent event) {
        this.currentEvent = event;
        return event;
    }

    @Override
    public Optional<SubmissionEvent> findCurrent() {
        return Optional.ofNullable(currentEvent);
    }
}
