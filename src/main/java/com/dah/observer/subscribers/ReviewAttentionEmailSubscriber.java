package com.dah.observer.subscribers;

import com.dah.email.EmailSender;
import com.dah.observer.events.ReviewAttentionEvent;
import com.dah.records.EmailMessage;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewAttentionEmailSubscriber implements Subscriber<ReviewAttentionEvent> {

  private EmailSender emailSender;

  @Override
  public void update(ReviewAttentionEvent event) {
    event.getReviews().stream().forEach(review -> {
      String to = review.getReviewer().getResearcher().getEmail();
      String subject = "Revisão precisa de atenção!";
      String body = getBody(review.getArticle().getTitle(), review.getArticle().getId());

      emailSender.send(new EmailMessage(to, subject, body));
    });
  }

  private String getBody(String articleName, Integer articleId) {
    return String.format("O artigo de nome \"%s\" e de ID \"%d\" precisa de atenção!\nOs vereditos estão em choque!\n",
        articleName, articleId);
  }
}
