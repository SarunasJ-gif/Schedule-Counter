package com.sarunas.Scheduler.Counter.exceptions;

public class NotEnoughTimeForTaskException extends RuntimeException {
    public NotEnoughTimeForTaskException(String message) {
        super(message);
    }
}
