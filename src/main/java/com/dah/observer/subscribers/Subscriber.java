package com.dah.observer.subscribers;

import com.dah.observer.events.DomainEvent;

public interface Subscriber<T extends DomainEvent> {
   public void update(T event);
}
