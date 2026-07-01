package com.dah.observer.subscribers;

import com.dah.domain.Review;
import com.dah.email.EmailSender;
import com.dah.observer.events.ArticleAssignedEvent;
import com.dah.records.EmailMessage;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ReviewerAssignementEmailSubscriber implements Subscriber<ArticleAssignedEvent> {

  private EmailSender emailSender;

  @Override
  public void update(ArticleAssignedEvent event) {
    Review review = event.getReview();

    String to = review.getReviewer().getResearcher().getEmail();
    String subject = "Recebeu um artigo para revisão!";
    String body = getBody(review.getArticle().getTitle(), review.getArticle().getId());

    emailSender.send(new EmailMessage(to, subject, body));
  }

  private String getBody(String articleName, Integer articleId) {
    return String.format("O artigo de nome \"%s\" e de ID \"%d\" foi designado para você revisar!\n",
        articleName, articleId);
  }
}
