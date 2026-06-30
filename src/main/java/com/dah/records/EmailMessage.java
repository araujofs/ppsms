package com.dah.records;

public record EmailMessage(String to, String subject, String body) {
  @Override
  public String toString() {
    return String.format("Destinatário: %s\nAssunto: %s\nCorpo: %s", to, subject, body);
  }
}
