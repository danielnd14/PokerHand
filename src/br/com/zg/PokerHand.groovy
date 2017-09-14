package br.com.zg

class PokerHand {


	private List<Card> listCards
	private TypeHand typeHand


	PokerHand(String hand) throws IllegalArgumentException {

		hand = hand.toUpperCase()
		String[] cards = hand.split(" ")

		this.listCards = getListOfCardsByName(cards)


		if (listCards.size() != 5) {
			throw new IllegalArgumentException("Quantidade de cartas invalida")
		}

		HandClassifier handClassifier = new HandClassifier(this.listCards)
		this.typeHand = handClassifier.getTypeHand()

	}

	private static List<Card> getListOfCardsByName(String[] cards) {

		List<Card> listCards = new ArrayList()

		cards.each {
			listCards.add(new Card(it))
		}

		return listCards.sort()
	}

	Resultado compareWith(PokerHand otherHand) {

		if (this.typeHand == otherHand.typeHand) {

			return desempatar(otherHand)

		} else {

			int maior = Math.max(this.typeHand.ordinal(), otherHand.typeHand.ordinal())
			return maior == this.typeHand.ordinal() ? Resultado.WIN : Resultado.LOSS
		}


	}

	private Resultado desempateNumerico(PokerHand otherHand) {

		Resultado desempate = Resultado.DRAW

		for (int i = listCards.size() - 1; i >= 0; i--) {

			if (listCards.get(i) != otherHand.listCards.get(i)) {

				int maior = Math.max(this.listCards.get(i).value, otherHand.listCards.get(i).value)
				return maior == this.listCards.get(i).value ? Resultado.WIN : Resultado.LOSS

			}
		}
		return desempate
	}

	private Resultado desempatar(PokerHand otherHand) {


		switch (this.typeHand) {

			case TypeHand.UM_PAR:
			case TypeHand.DOIS_PARES:
				return desempatarPares(otherHand)
				break
			default:
				return desempateNumerico(otherHand)
				break
		}

	}

	static int getFirstValueDuplicated(List valores) {

		for (int i = 0; i < valores.size(); i++) {
			int aux = (int) valores.get(i)

			for (int j = i + 1; j < valores.size(); j++) {
				if (aux == valores.get(j)) {
					return (int) valores.get(j)
				}
			}

		}

	}

	static int getMaiorCartaDuplicada(List<Card> cards) {

		int[] value = new int[2]
		int iAux = 0

		for (int i = 0; i < cards.size(); i++) {
			int aux = cards.get(i).value

			for (int j = i + 1; j < cards.size(); j++) {

				if (aux == cards.get(j)) {
					value[iAux] = cards.get(j).value
					iAux++
					break
				}
			}

		}

		return Math.max(value[0], value[1])
	}

	Resultado desempatarPares(PokerHand otherHand) {

		Resultado desempate = Resultado.DRAW


		List valueActualHand = getValuesFromCards()

		List valueOtherHand = otherHand.getValuesFromCards()

		int parActual
		int parOther

		if (this.typeHand == TypeHand.UM_PAR) {

			parActual = getFirstValueDuplicated(valueActualHand)
			parOther = getFirstValueDuplicated(valueOtherHand)

		} else {

			parActual = getMaiorCartaDuplicada(valueActualHand)
			parOther = getMaiorCartaDuplicada(valueOtherHand)
		}

		if (parActual != parOther) {
			int maior = Math.max(parActual, parOther)
			desempate = maior == parActual ? Resultado.WIN : Resultado.LOSS
		}

		desempate = desempate == Resultado.DRAW ? desempateNumerico(otherHand) : desempate

		return desempate

	}

	protected List<Integer> getValuesFromCards() {

		List<Integer> valores = new ArrayList()

		listCards.each {
			valores.add(it.value)
		}

		valores.sort()
		return valores
	}

	List<Card> getListCards() {
		return listCards
	}


}
