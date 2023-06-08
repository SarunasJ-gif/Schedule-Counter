package com.sarunas.Scheduler.Counter.exceptions;

public class NoDaysLeftException extends RuntimeException {
    public NoDaysLeftException(String message) {
        super(message);
    }
}
