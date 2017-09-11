package br.com.zg

class PokerHand {
	private String[] cards
	private ValidHand validHand
	private TypeHand typeHand

	PokerHand(String hand) {

		this.cards = hand.split(" ")

		if (!Checker.isValidHand(this.cards)) {
			validHand = ValidHand.INVALID
		} else {
			validHand = ValidHand.VALID
			this.typeHand = Checker.whichHand(this)
			println(typeHand)
		}

	}

	Enum compareWith(PokerHand otherHand) {

		Enum result

		if (this.validHand != ValidHand.VALID || otherHand.validHand != ValidHand.VALID) {

			result = ValidHand.SOME_OF_HAND_IS_INVALID

		} else {

			switch (Checker.isHandEquals(this, otherHand)) {

				case true:
					result = Resultado.DRAW
					break
				case false:

					break
			}

		}

		return result
	}


}
