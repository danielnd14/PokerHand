package br.com.zg.classifier

import br.com.zg.Card
import br.com.zg.CardValue
import br.com.zg.HandType

class QuadraStrategy implements PokerClassifier {
	private Map<CardValue, List<Card>> groupValue


	QuadraStrategy(Map<CardValue, List<Card>> groupValue) {
		this.groupValue = groupValue
	}

	@Override
	boolean classificar() {

		return temGrupos(1, 4)
	}

	private boolean temGrupos(int quantidade, int tamanho) {

		return groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() == tamanho }.size() == quantidade
	}

	@Override
	HandType getThisHandType() {
		return HandType.QUADRA
	}
}
