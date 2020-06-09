package com.shasha.msksfulltesting.web.exception;

/**
 * Created by kobis on 07 Jun, 2020
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Not Found...Opps...");
    }
}
