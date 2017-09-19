package br.com.zg

class PokerHand {

	private List<Card> listCards
	HandType typeHand
	private Map<CardValue, List<Card>> groupValue


	PokerHand(String hand) throws IllegalArgumentException {

		hand = hand.toUpperCase()
		String[] cards = hand.split(" ")

		this.listCards = getListOfCardsByName(cards)

		if (listCards.size() != 5) {
			throw new IllegalArgumentException("Quantidade de cartas invalida")
		}

		HandClassifier handClassifier = new HandClassifier(this.listCards)
		this.typeHand = handClassifier.handType
		this.groupValue = handClassifier.groupValue
	}

	private static List<Card> getListOfCardsByName(String[] cards) {

		List<Card> listCards = new ArrayList()

		cards.collect {
			listCards << new Card(it)
		}

		return listCards.sort()
	}

	Resultado compareWith(PokerHand otherHand) {

		//println(this.typeHand.toString() + " -VS- " + otherHand.typeHand.toString())

		if (this.typeHand == otherHand.typeHand) {

			return desempatar(otherHand)

		} else {

			int maior = Math.max(this.typeHand.ordinal(), otherHand.typeHand.ordinal())
			return maior == this.typeHand.ordinal() ? Resultado.WIN : Resultado.LOSS
		}


	}


	private Resultado desempatar(PokerHand otherHand) {

		List<CardValue> listA
		List<CardValue> listB

		listA = getListaParaDesempate(this)
		listB = getListaParaDesempate(otherHand)


		for (int i = 0; i < listA.size(); i++) {

			if (listA.get(i).ordinal() < listB.get(i).ordinal()) {
				return Resultado.LOSS
			} else if (listA.get(i).ordinal() > listB.get(i).ordinal()) {
				return Resultado.WIN
			}
		}

		return Resultado.DRAW
	}

	private static List<CardValue> getListaParaDesempate(PokerHand hand) {

		List<CardValue> cardValueList

		cardValueList = hand.groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() > 1 }.reverse().collect { it.first().value }

		hand.groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() == 1 }.reverse().collect { cardValueList << it.first().value }

		return cardValueList

	}
	List<Card> getListCards() {
		return listCards
	}

}