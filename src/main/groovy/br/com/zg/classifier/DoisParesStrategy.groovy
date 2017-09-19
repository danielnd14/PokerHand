package br.com.zg.classifier

import br.com.zg.Card
import br.com.zg.CardValue
import br.com.zg.HandType

class DoisParesStrategy implements PokerClassifier {
	private Map<CardValue, List<Card>> groupValue

	DoisParesStrategy(Map<CardValue, List<Card>> groupValue) {
		this.groupValue = groupValue

	}

	@Override
	boolean classificar() {
		return temGrupos(2, 2)
	}

	private boolean temGrupos(int quantidade, int tamanho) {

		return groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() == tamanho }.size() == quantidade
	}


	@Override
	HandType getThisHandType() {
		return HandType.DOIS_PARES
	}
}
