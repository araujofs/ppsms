package com.dah.observer.subscribers;

import java.util.List;

import com.dah.domain.Article;
import com.dah.domain.Review;
import com.dah.domain.User;
import com.dah.email.EmailSender;
import com.dah.enums.ReviewOutcome;
import com.dah.observer.events.ArticleConcludedEvent;
import com.dah.records.EmailMessage;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AuthorResultEmailSubscriber implements Subscriber<ArticleConcludedEvent> {

  private EmailSender emailSender;

  @Override
  public void update(ArticleConcludedEvent event) {
    event.getArticle().getAuthors().stream().forEach(author -> {
      String to = author.getEmail();
      String subject = "Seu artigo foi revisado!";
      String body = getBody(event, author);

      emailSender.send(new EmailMessage(to, subject, body));
    });
  }

  public String getBody(ArticleConcludedEvent event, User author) {
    StringBuilder reviewsText = new StringBuilder();
    List<Review> reviews = event.getReviews();
    Article article = event.getArticle();

    for (int i = 0; i < reviews.size(); i++) {
      Review review = reviews.get(i);

      reviewsText.append("""
          [Revisor %d]
          Principal Contribuição ou pontos positivos
          ================================
          %s

          Pontos negativos
          ================================
          %s

          """.formatted(
          i + 1,
          review.getContribution(),
          review.getCriticism()));
    }

    String emailBody;

    if (event.getOutcome().equals(ReviewOutcome.ACCEPTED)) {
      emailBody = """
          Prezado(a) Sr(a). %s:

          Parabéns! Sua submissão de nº %d, intitulada "%s", para o %s - %s, foi aceita.

          As avaliações estão disponíveis ao final do e-mail.

          Atenciosamente,

          Coordenador(a) do Comitê de Programa do %s

          %s
          """;
    } else {
      emailBody = """
          Prezado(a) Sr(a). %s:

          Lamentamos informar que seu artigo de nº %d intitulado "%s" não pôde ser aceito para o %s - %s.

          Ao final do email, seguem os pareceres dos revisores, que esperamos que possam auxiliá-lo em futuras submissões.

          Agradecemos sua submissão.

          Atenciosamente,

          Coordenador(a) do Comitê de Programa do %s

          %s
          """;
    }

    return emailBody.formatted(
        author.getName(),
        article.getId(),
        article.getTitle(),
        event.getEvent().getName(),
        event.getEvent().getCategory(),
        event.getEvent().getName(),
        reviewsText.toString());
  }
}
