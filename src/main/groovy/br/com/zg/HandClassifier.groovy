package br.com.zg

final class HandClassifier implements PokerClassifier {



	private Map<CardValue, List<Card>> groupValue
	private Map<Suit, List<Card>> groupNaipe
	private List<Card> cardList


	HandClassifier(List<Card> listCards) {

		this.groupValue = listCards.groupBy { it.value }

		this.groupNaipe = listCards.groupBy { it.naipe }

		this.cardList = listCards

	}

	@Override
	boolean isRoyal() {

		CardValue firsCardValue = groupValue
				.values()
				.first()
				.first().value

		return this.groupNaipe.size() == 1 && isSequence() && firsCardValue == firsCardValue.TEN
	}

	@Override
	boolean isStraight() {
		return this.groupNaipe.size() == 1 && isSequence()
	}

	@Override
	boolean isQuadra() {
		return temGrupos(1, 4)
	}

	@Override
	boolean isFull() {
		return temGrupos(1, 3) && temGrupos(1, 2)
	}

	@Override
	boolean isFlush() {
		return this.groupNaipe.size() == 1 && !isSequence()
	}

	@Override
	boolean isSequence() {

		boolean sequencia = cardList.last().value.ordinal() - cardList.first().value.ordinal() == 4

		return sequencia ? groupValue.size() == 5 : sequencia

	}

	@Override
	boolean isTrinca() {

		return temGrupos(1, 3) && !temGrupos(1, 2)
	}

	@Override
	boolean isDoisPares() {
		return temGrupos(2, 2)
	}

	@Override
	boolean isPar() {
		return temGrupos(1, 2)
	}

	@Override
	boolean isCartaAlta() {
		return true
	}

	final HandType getHandType() {

		if (isRoyal()) {

			return HandType.ROYAL_FLUSH

		} else if (isStraight()) {

			return HandType.STRAIGHT_FLUSH

		} else if (isQuadra()) {

			return HandType.QUADRA

		} else if (isFull()) {

			return HandType.FULL_HOUSE

		} else if (isFlush()) {

			return HandType.FLUSH

		} else if (isSequence()) {

			return HandType.SEQUENCIA

		} else if (isTrinca()) {

			return HandType.TRINCA

		} else if (isDoisPares()) {

			return HandType.DOIS_PARES

		} else if (isPar()) {

			return HandType.UM_PAR

		} else if (isCartaAlta()) {

			return HandType.CARTA_ALTA

		}
	}

	private boolean temGrupos(int quantidade, int tamanho) {

		return groupValue.values()
				.findAll { List<Card> grupo -> grupo.size() == tamanho }.size() == quantidade
	}

	Map<CardValue, List<Card>> getGroupValue() {
		return groupValue
	}


}
