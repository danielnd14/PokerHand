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
	boolean is2pares() {
		return temGrupos(2, 2)
	}

	@Override
	boolean is1Par() {
		return temGrupos(1, 2)
	}

	@Override
	boolean isCartaAlta() {
		return true
	}

	final TypeHand getTypeHand() {

		if (isRoyal()) {

			return TypeHand.ROYAL_FLUSH

		} else if (isStraight()) {

			return TypeHand.STRAIGHT_FLUSH

		} else if (isQuadra()) {

			return TypeHand.QUADRA

		} else if (isFull()) {

			return TypeHand.FULL_HOUSE

		} else if (isFlush()) {

			return TypeHand.FLUSH

		} else if (isSequence()) {

			return TypeHand.SEQUENCIA

		} else if (isTrinca()) {

			return TypeHand.TRINCA

		} else if (is2pares()) {

			return TypeHand.DOIS_PARES

		} else if (is1Par()) {

			return TypeHand.UM_PAR

		} else if (isCartaAlta()) {

			return TypeHand.CARTA_ALTA

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
