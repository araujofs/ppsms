package com.dah.domain.article;

import com.dah.domain.Article;

public class UnderReview implements ArticleState {

	@Override
	public void startReview(Article article) {
		throw new UnsupportedOperationException("Artigo já foi para revisão!");
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
    article.setState(new AttentionState());
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
    return "Em revisão";
	}
    
}
