package com.lostoctet.restservices.exceptions;

import java.util.Date;

//Class for Custom Error Details
public class CustomErrorDetails {
    private Date timeStamp;
    private String message;
    private String errorDetails;

    //Fields Constructors
    public CustomErrorDetails(Date timeStamp, String message, String errorDetails) {
        this.timeStamp = timeStamp;
        this.message = message;
        this.errorDetails = errorDetails;
    }

    //Getters
    public Date getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorDetails() {
        return errorDetails;
    }
}
