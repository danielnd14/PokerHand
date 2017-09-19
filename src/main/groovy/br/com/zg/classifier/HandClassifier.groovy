package br.com.zg.classifier

import br.com.zg.Card
import br.com.zg.CardValue
import br.com.zg.HandType
import br.com.zg.Suit

class HandClassifier {


	static HandType getHandType(List<Card> listCards) {

		List<PokerClassifier> estrategias = new ArrayList()
		Map<CardValue, List<Card>> groupValue
		Map<Suit, List<Card>> groupNaipe

		groupValue = getGroupValue(listCards)
		groupNaipe = getGroupNaipe(listCards)

		estrategias.add(new RoyalStrategy(listCards, groupValue, groupNaipe))
		estrategias.add(new StraightFlushStrategy(listCards, groupValue, groupNaipe))
		estrategias.add(new QuadraStrategy(groupValue))
		estrategias.add(new FullHouseStrategy(groupValue))
		estrategias.add(new FlushStrategy(listCards, groupValue, groupNaipe))
		estrategias.add(new SequenciaStrategy(listCards, groupValue))
		estrategias.add(new TrincaStrategy(groupValue))
		estrategias.add(new DoisParesStrategy(groupValue))
		estrategias.add(new UmParStrategy(groupValue))
		estrategias.add(new CartaAltaStrategy())

		return getClassificacao(estrategias)


	}

	private static HandType getClassificacao(List<PokerClassifier> estrategias) {

		for (PokerClassifier strategy : estrategias) {
			if (strategy.classificar()) {
				return strategy.getThisHandType()

			}
		}
	}

	static Map<CardValue, List<Card>> getGroupValue(List<Card> listCards) {
		return listCards.groupBy { it.value }
	}

	static Map<Suit, List<Card>> getGroupNaipe(List<Card> listCards) {
		return listCards.groupBy { it.naipe }
	}
}
