package com.ololaa.ololaa.common.models;

import java.io.Serializable;

public class NotificationInfo implements Serializable {
    public String message;
    public NotificationInfo() { }
    public NotificationInfo(String message) { this.message = message; }
}
