package br.com.zg.classifier

import br.com.zg.Card
import br.com.zg.CardValue
import br.com.zg.HandType
import br.com.zg.Suit

class FlushStrategy implements PokerClassifier {
	private Map<CardValue, List<Card>> groupValue
	private Map<Suit, List<Card>> groupNaipe
	List<Card> cardList

	FlushStrategy(List<Card> listCards, Map<CardValue, List<Card>> groupValue, Map<Suit, List<Card>> groupNaipe) {
		this.groupValue = groupValue
		this.groupNaipe = groupNaipe
		this.cardList = listCards
	}

	@Override
	boolean classificar() {

		return this.groupNaipe.size() == 1 && !isSequence()
	}


	boolean isSequence() {

		boolean sequencia = cardList.last().value.ordinal() - cardList.first().value.ordinal() == 4

		return sequencia ? groupValue.size() == 5 : sequencia

	}

	@Override
	HandType getThisHandType() {
		return HandType.FLUSH
	}
}
