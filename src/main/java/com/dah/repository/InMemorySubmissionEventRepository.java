package com.dah.repository;

import java.util.Optional;

public class InMemorySubmissionEventRepository implements SubmissionEventRepository {
    private SubmissionEvent currentEvent;

    @Override
    public void saveCurrent(SubmissionEvent event) {
        this.currentEvent = event;
    }

    @Override
    public Optional<SubmissionEvent> findCurrent() {
        return Optional.ofNullable(currentEvent);
    }
}
