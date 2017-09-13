package br.com.zg


class HandClassifier implements PokerClassifier {


	private PokerHand pkHand
	private List<Integer> valores
	private List<Card> cards

	HandClassifier(PokerHand pkHand) {


		this.pkHand = pkHand
		this.cards = pkHand.listCards

		valores = pkHand.getValuesFromCards()

	}

	@Override
	boolean isRoyal() {

		boolean isRoyalFlush = true

		cards.each {

			if (it.value <= 9) {
				isRoyalFlush = false
			}
		}

		isRoyalFlush = isRoyalFlush ? isSequenciaDevalores(valores) : false

		isRoyalFlush = isRoyalFlush ? isSameSuit(cards) : false

		return isRoyalFlush
	}

	@Override
	boolean isStraight() {

		boolean isStraightFlush = true

		isStraightFlush = isSequenciaDevalores(valores)

		isStraightFlush = isStraightFlush ? isSameSuit(cards) : false

		return isStraightFlush
	}

	@Override
	boolean isQuadra() {

		boolean quadra

		quadra = (valores.get(0) + valores.get(1) + valores.get(2) + valores.get(3)) == (valores.get(0) * 4)

		if (!quadra) {

			quadra = (valores.get(1) + valores.get(2) + valores.get(3) + valores.get(4)) == (valores.get(1) * 4)

		}

		return quadra
	}

	@Override
	boolean isFull() {

		boolean fullHouse = false

		fullHouse = valores.get(0) == valores.get(1) && (valores.get(2) + valores.get(3) + valores.get(4)) == valores.last() * 3

		fullHouse = !fullHouse ? (valores.get(0) + valores.get(1) + valores.get(2)) == valores.first() * 3 && valores.get(3) == valores.get(4) : true

		return fullHouse
	}

	@Override
	boolean isFlush() {

		boolean flush

		flush = isSameSuit(cards)

		flush = flush ? !isSequenciaDevalores(valores) : false

		return flush
	}

	@Override
	boolean isSequence() {

		boolean sequence

		sequence = !isSameSuit(cards)

		sequence = sequence ? isSequenciaDevalores(valores) : false

		return sequence
	}

	@Override
	boolean isTrinca() {
		boolean trinca

		trinca = (valores.get(0) + valores.get(1) + valores.get(2)) == valores.first() * 3 && valores.get(3) != valores.get(4)

		trinca = !trinca ? valores.get(0) != valores.get(1) && (valores.get(2) + valores.get(3) + valores.get(4)) == valores.last() * 3 : true

		trinca = !trinca ? valores.first() != valores.last() && (valores.get(1) + valores.get(2) + valores.get(3)) == valores.get(1)* 3 : true

		return trinca
	}

	@Override
	boolean is2pares() {
		boolean doispares

		doispares = valores.first() == valores.get(1) && valores.get(3) == valores.last()

		doispares = !doispares ? valores.get(0) == valores.get(1) && valores.get(2) == valores.get(3) : true

		doispares = !doispares ? valores.get(1) == valores.get(2) && valores.get(3) == valores.get(4) : true

		return doispares
	}

	@Override
	boolean is1Par() {
		boolean umpar

		umpar = valores.first() == valores.get(1)

		umpar = !umpar ? valores.get(1) == valores.get(2) : true

		umpar = !umpar ? valores.get(2) == valores.get(3) : true

		umpar = !umpar ? valores.get(3) == valores.last() : true

		return umpar
	}

	@Override
	boolean isCartaAlta() {
		return true
	}

	static boolean isThereDuplicity(List valores) {


		firstFor:
		for (int i = 0; i < valores.size(); i++) {
			int aux = (int) valores.get(i)

			for (int j = i + 1; j < valores.size(); j++) {
				if (aux == valores.get(j)) {
					return   true

				}
			}

		}


	}

	static boolean isSequenciaDevalores(List valores) {

		int soma = getSomaValores(valores)

		return (soma - (valores.first() * 5)) == 10 ? !isThereDuplicity(valores) : false
	}

	static int getSomaValores(ArrayList valores) {
		int soma = 0

		valores.each {
			soma += it
		}

		return soma
	}

	static boolean isSameSuit(List<Card> cards) {
		boolean sameSuit = true

		String suit = cards.first().naipe
		cards.each {
			if (!suit.equals(it.naipe)) {
				sameSuit = false
			}

		}
		return sameSuit

	}
}
