package com.opitzconsulting.hackathon.service;

public class NoRfidTagFoundException extends Throwable {

    public NoRfidTagFoundException() {
        super("No RFID-Tag with given ID found in database.");
    }

}
