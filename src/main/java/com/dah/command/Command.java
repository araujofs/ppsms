package com.dah.command;

public interface Command<T> {
    void execute(T data);
}
