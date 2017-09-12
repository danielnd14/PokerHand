package br.com.zg

final class Checker {

	private static PlayingCards baralho = PlayingCards.instance

	static boolean isHandEquals(PokerHand actualHand, PokerHand otherHand) {

		boolean retorno = true

		actualHand.cards.each {

			if (!otherHand.cards.contains(it)) {
				retorno = false
			}

		}

		return retorno
	}

	static boolean isValidHand(String[] cards) {

		boolean valid = true
		cards.each {
			if (!baralho.exists(it)) {
				valid = false
			}
		}
		return valid
	}

	static Enum whichHand(PokerHand pkHand) {

		Enum retorno

		HandClassifier handClassifier = new HandClassifier(baralho,pkHand)

		if (handClassifier.isRoyal()) {

			retorno = TypeHand.ROYAL_FLUSH

		} else if (handClassifier.isStraight()) {

			retorno = TypeHand.STRAIGHT_FLUSH

		} else if (handClassifier.isQuadra()) {

			retorno = TypeHand.QUADRA

		} else if (handClassifier.isFull()) {

			retorno = TypeHand.FULL_HOUSE

		} else if (handClassifier.isFlush()) {

			retorno = TypeHand.FLUSH

		} else if (handClassifier.isSequence()) {

			retorno = TypeHand.SEQUENCIA

		} else if (handClassifier.isTrinca()) {

			retorno = TypeHand.TRINCA

		} else if (handClassifier.is2pares()) {

			retorno = TypeHand.DOIS_PARES

		} else if (handClassifier.is1Par()) {

			retorno = TypeHand.UM_PAR

		} else if (handClassifier.isCartaAlta()) {

			retorno = TypeHand.CARTA_ALTA

		}

		return retorno

	}


}
