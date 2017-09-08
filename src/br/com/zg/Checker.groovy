package br.com.zg

final class Checker implements Classifier {

	private static PlayingCards baralho = new PlayingCards()

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

	static Enum whichHand(PokerHand pkHand){

	}

	@Override
	boolean isRoyal(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isStraight(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isQuadra(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isFull(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isFlush(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isSequence(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isTrinca(PokerHand pkHand) {
		return false
	}

	@Override
	boolean is2pares(PokerHand pkHand) {
		return false
	}

	@Override
	boolean is1Par(PokerHand pkHand) {
		return false
	}

	@Override
	boolean isCartaAlta(PokerHand pkHand) {
		return false
	}
}
