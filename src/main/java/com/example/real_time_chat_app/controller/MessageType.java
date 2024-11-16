package com.example.real_time_chat_app.controller;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum MessageType {

    JOIN,
    CHAT,
    LEAVER
}
