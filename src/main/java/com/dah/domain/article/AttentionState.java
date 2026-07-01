package com.dah.domain.article;

import com.dah.domain.Article;

public class AttentionState implements ArticleState {

	@Override
	public void startReview(Article article) {
		throw new UnsupportedOperationException("Artigo já está em atenção!");
	}

	@Override
	public void accept(Article article) {
    article.setState(new AcceptedState());
	}

	@Override
	public void reject(Article article) {
    article.setState(new RejectedState());
	}

	@Override
	public void markAttention(Article article) {
		throw new UnsupportedOperationException("Artigo já está em atenção!");
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
    return "Discussão";
	}
}
