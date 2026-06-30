package com.dah.domain.article;

import com.dah.domain.Article;

public class SubmittedState implements ArticleState {

	@Override
	public void startReview(Article article) {
    article.setState(new UnderReview());
	}

	@Override
	public void accept(Article article) {
		throw new UnsupportedOperationException("Artigo ainda não foi para revisão!");
	}

	@Override
	public void reject(Article article) {
		throw new UnsupportedOperationException("Artigo ainda não foi para revisão!");
	}

	@Override
	public void markAttention(Article article) {
		throw new UnsupportedOperationException("Artigo ainda não foi para revisão!");
	}

	@Override
	public boolean isPending() {
    return true;
	}

	@Override
	public boolean isFinished() {
    return !isPending();
	}

	@Override
	public String getName() {
    return "Enviado";
	}
    
}
