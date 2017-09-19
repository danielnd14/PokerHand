package br.com.zg.classifier

import br.com.zg.Card
import br.com.zg.CardValue
import br.com.zg.HandType

class SequenciaStrategy implements PokerClassifier {
	private Map<CardValue, List<Card>> groupValue
	List<Card> cardList

	SequenciaStrategy(List<Card> listCards, Map<CardValue, List<Card>> groupValue) {
		this.groupValue = groupValue
		this.cardList = listCards
	}

	@Override
	boolean classificar() {
		boolean sequencia = cardList.last().value.ordinal() - cardList.first().value.ordinal() == 4

		return sequencia ? groupValue.size() == 5 : sequencia
	}


	@Override
	HandType getThisHandType() {
		return HandType.SEQUENCIA
	}
}
