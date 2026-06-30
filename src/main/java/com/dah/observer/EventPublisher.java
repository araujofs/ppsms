package com.dah.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dah.observer.events.DomainEvent;
import com.dah.observer.subscribers.Subscriber;

public class EventPublisher {
  private Map<Class<? extends DomainEvent>, List<Subscriber<?>>> subscribers = new HashMap<>();

  public <T extends DomainEvent> void subscribe(Class<T> eventType, Subscriber<T> subscriber) {
    subscribers.computeIfAbsent(eventType, k -> new ArrayList<>()).add(subscriber);
  }

  public <T extends DomainEvent> void unsubscribe(Class<T> eventType, Subscriber<T> subscriber) {
    subscribers.computeIfPresent(eventType, (key, subscribers) -> {
      subscribers.remove(subscriber);
      return subscribers;
    });
  }

  @SuppressWarnings("unchecked")
  public void publish(DomainEvent event) {
    subscribers.computeIfPresent(event.getClass(), (key, subscribers) -> {

      // subscribe e unsubscribe garantem tipagem correta
      subscribers.forEach(subscriber -> {
        ((Subscriber<DomainEvent>) subscriber).update(event);
      });

      return subscribers;
    });
  }
}
