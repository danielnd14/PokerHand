package br.com.zg


class HandClassifier implements PokerClassifier {
	private PlayingCards baralho

	HandClassifier(PlayingCards baralho) {
		this.baralho = baralho
	}

	@Override
	boolean isRoyal(PokerHand pkHand) {

		boolean isRoyalFlush = true

		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		valores.each {
			if (it <= 9) {
				isRoyalFlush = false
			}
		}

		isRoyalFlush = isRoyalFlush ? isSequenciaDevalores(valores):false

		isRoyalFlush = isRoyalFlush ? isSameSuit(cards):false

		return isRoyalFlush
	}

	@Override
	boolean isStraight(PokerHand pkHand) {

		boolean isStraightFlush = true

		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		isStraightFlush = isSequenciaDevalores(valores)

		isStraightFlush = isStraightFlush ? isSameSuit(cards):false

		return isStraightFlush
	}

	@Override
	boolean isQuadra(PokerHand pkHand) {

		boolean quadra
		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		quadra = valores.get(0) == valores.get(1) == valores.get(2) == valores.get(3)

		if (!quadra) {

			quadra = valores.get(1) == valores.get(2) == valores.get(3) == valores.get(4)

		}

		return quadra
	}

	@Override
	boolean isFull(PokerHand pkHand) {

		boolean isFullHouse = false
		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		isFullHouse = valores.get(0) == valores.get(1) && valores.get(2) == valores.get(3) == valores.get(4)

		if (!isFullHouse) {

			isFullHouse = valores.get(0) == valores.get(1) == valores.get(2) && valores.get(3) == valores.get(4)
		}

		return isFullHouse
	}

	@Override
	boolean isFlush(PokerHand pkHand) {

		boolean flush
		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		flush = isSameSuit(cards)

		flush = flush ? !isSequenciaDevalores(valores):false

		return flush
	}

	@Override
	boolean isSequence(PokerHand pkHand) {
		boolean sequence

		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		sequence = !isSameSuit(cards)

		sequence = sequence ? isSequenciaDevalores(valores):false

		return sequence
	}

	@Override
	boolean isTrinca(PokerHand pkHand) {
		boolean trinca

		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		trinca = valores.get(0) == valores.get(1) == valores.get(2) && valores.get(3) != valores.get(4)

		if(!trinca){

			trinca = valores.get(0) != valores.get(1) && valores.get(2) == valores.get(3) == valores.get(4)

		}

		return trinca
	}

	@Override
	boolean is2pares(PokerHand pkHand) {
		boolean doispares

		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		doispares = valores.get(0) == valores.get(1) && valores.get(3) == valores.last()

		if(!doispares){
			doispares = valores.get(0) == valores.get(1) && valores.get(2) == valores.get(3)
		}

		if(!doispares){
			doispares = valores.get(1) == valores.get(2) && valores.get(3) == valores.get(4)
		}

		return doispares
	}

	@Override
	boolean is1Par(PokerHand pkHand) {
		boolean umpar

		String[] cards = pkHand.cards

		ArrayList<Integer> valores = getValuesFromCards(cards)

		umpar = valores.get(0) == valores.get(1)

		if(!umpar){
			valores.get(3) == valores.last()
		}

		return umpar
	}

	@Override
	boolean isCartaAlta(PokerHand pkHand) {
		return true
	}

	static boolean isThereDuplicity(ArrayList valores) {

		boolean duplicity = false

		firstFor:
		for (int i = 0; i < valores.size(); i++) {
			int aux = valores.get(i)

			for (int j = i + 1; j < valores.size(); j++) {
				if (aux == valores.get(j)) {
					duplicity = true
					break firstFor
				}
			}

		}
		return duplicity

	}

	static boolean isSequenciaDevalores(ArrayList valores) {

		int soma = getSomaValores(valores)

		return (soma - (valores.first() * 5)) == 10
	}

	ArrayList getValuesFromCards(String[] cards) {

		ArrayList<Integer> valores = new ArrayList()

		for (int i = 0; i < cards.length; i++) {
			valores.add(baralho.allCards.get(cards[i]))
		}

		valores.sort()
		return valores
	}

	static int getSomaValores(ArrayList valores) {
		int soma = 0

		valores.each {
			soma += it
		}

		return soma
	}

	boolean isSameSuit(String[] cards) {
		boolean sameSuit

		String suit = baralho.getSuit(cards[0])
		cards.each {
			if (!suit.equals(baralho.getSuit(it))) {
				sameSuit = false
			}
		}

	}
}
