package com.dah.email;

import com.dah.records.EmailMessage;

public interface EmailSender {
  public void send(EmailMessage message);
}
