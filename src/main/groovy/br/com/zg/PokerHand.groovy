package br.com.zg

class PokerHand {


	private List<Card> listCards
	TypeHand typeHand
	private Map<CardValue, List<Card>> groupValue


	PokerHand(String hand) throws IllegalArgumentException {

		hand = hand.toUpperCase()
		String[] cards = hand.split(" ")

		this.listCards = getListOfCardsByName(cards)


		if (listCards.size() != 5) {
			throw new IllegalArgumentException("Quantidade de cartas invalida")
		}

		HandClassifier handClassifier = new HandClassifier(this.listCards)
		this.typeHand = handClassifier.typeHand
		this.groupValue = handClassifier.groupValue
	}

	private static List<Card> getListOfCardsByName(String[] cards) {

		List<Card> listCards = new ArrayList()

		cards.each {
			listCards.add(new Card(it))
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

		List<CardValue> listA = new ArrayList()
		List<CardValue> listB = new ArrayList()

		groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() > 1 }.reverse().each { listA << it.value.first() }

		otherHand.groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() > 1 }.reverse().each { listB << it.value.first() }

		Resultado result = comparar2ListasEmpatadas(listA,listB)

		if(result == Resultado.DRAW){

			listA = new ArrayList<CardValue>()
			listB = new ArrayList<CardValue>()

			groupValue.values()
					.findAll { List<Card> grupo -> grupo.size() == 1 }.reverse().each { listA << it.value.first() }

			otherHand.groupValue.values()
					.findAll { List<Card> grupo -> grupo.size() == 1 }.reverse().each { listB << it.value.first() }

			return comparar2ListasEmpatadas(listA, listB)
		}
		return result
	}


	private static comparar2ListasEmpatadas(List<CardValue> listA, List<CardValue>listB){

		for (int i = 0; i < listA.size(); i++) {

			if (listA.get(i).ordinal() < listB.get(i).ordinal()) {
				return Resultado.LOSS
			} else if (listA.get(i).ordinal() > listB.get(i).ordinal()) {
				return Resultado.WIN
			}
		}
		return Resultado.DRAW
	}

}