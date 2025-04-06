package com.salazarisaiahnoel.skillswap.others;

public class MessageItem {
    public String getInstructor() {
        return instructor;
    }

    public String getMessageContent() {
        return messageContent;
    }

    String instructor, messageContent;

    public MessageItem(String instructor, String messageContent) {
        this.instructor = instructor;
        this.messageContent = messageContent;
    }
}
