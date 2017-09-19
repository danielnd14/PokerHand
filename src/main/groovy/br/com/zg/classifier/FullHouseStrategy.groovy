package br.com.zg.classifier

import br.com.zg.Card
import br.com.zg.CardValue
import br.com.zg.HandType

class FullHouseStrategy implements PokerClassifier {
	private Map<CardValue, List<Card>> groupValue


	FullHouseStrategy(Map<CardValue, List<Card>> groupValue) {
		this.groupValue = groupValue

	}

	@Override
	boolean classificar() {

		return temGrupos(1, 3) && temGrupos(1, 2)
	}

	private boolean temGrupos(int quantidade, int tamanho) {

		return groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() == tamanho }.size() == quantidade
	}

	@Override
	HandType getThisHandType() {
		return HandType.FULL_HOUSE
	}
}
