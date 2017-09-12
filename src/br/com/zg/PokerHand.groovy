package br.com.zg

class PokerHand {
	private String[] cards
	private ValidHand validHand
	private TypeHand typeHand

	PokerHand(String hand) {

		hand = hand.toUpperCase()

		this.cards = hand.split(" ")

		if (!Checker.isValidHand(this.cards)) {
			validHand = ValidHand.INVALID
		} else {
			validHand = ValidHand.VALID

			this.typeHand = Checker.whichHand(this)
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


					if (this.typeHand == otherHand.typeHand) {

						result = desempatar(otherHand)

					} else {

						int maior = Math.max(this.typeHand.ordinal(), otherHand.typeHand.ordinal())
						result = maior == this.typeHand.ordinal() ? Resultado.WIN : Resultado.LOSS

					}

					break
				default: break
			}

		}

		return result
	}

	private Enum desempateNumerico(PokerHand otherHand) {

		Enum desempate = Resultado.DRAW

		PlayingCards baralho = PlayingCards.getInstance()

		List valueActualHand = baralho.getValuesFromCards(this.cards)

		List valueOtherHand = baralho.getValuesFromCards(otherHand.cards)

		for (int i = valueActualHand.size() - 1; i >= 0; i--) {


			if (valueActualHand.get(i) != valueOtherHand.get(i)) {

				int maior = Math.max(valueActualHand.get(i), valueOtherHand.get(i))
				desempate = maior == valueActualHand.get(i) ? Resultado.WIN : Resultado.LOSS
				break
			}
		}
		return desempate
	}

	Enum desempatar(PokerHand otherHand) {

		Enum desempate = Resultado.DRAW

		switch (this.typeHand) {

			case TypeHand.UM_PAR:
			case TypeHand.DOIS_PARES:
				desempate = desempatarPares(otherHand)
				break
			default:
				desempate = desempateNumerico(otherHand)
				break
		}
		return desempate
	}

	static byte getFirstValueDuplicated(List valores) {

		byte value = -1

		firstFor:
		for (int i = 0; i < valores.size(); i++) {
			byte aux = (byte) valores.get(i)

			for (int j = i + 1; j < valores.size(); j++) {
				if (aux == valores.get(j)) {
					value = (byte) valores.get(j)
					break firstFor
				}
			}

		}
		return value
	}

	static byte getMaiorValueDuplicated(List valores) {

		byte[] value = new byte[2]
		int iAux = 0


		for (int i = 0; i < valores.size(); i++) {
			byte aux = (byte) valores.get(i)

			for (int j = i + 1; j < valores.size(); j++) {
				if (aux == valores.get(j)) {
					value[iAux] = (byte) valores.get(j)
					iAux++
					break
				}
			}

		}

		return Math.max(value[0], value[1])
	}

	Enum desempatarPares(PokerHand otherHand) {

		Enum desempate = Resultado.DRAW

		PlayingCards baralho = PlayingCards.getInstance()

		List valueActualHand = baralho.getValuesFromCards(this.cards)

		List valueOtherHand = baralho.getValuesFromCards(otherHand.cards)

		byte parActual
		byte parOther

		switch (this.typeHand) {
			case TypeHand.UM_PAR:

				parActual = getFirstValueDuplicated(valueActualHand)
				parOther = getFirstValueDuplicated(valueOtherHand)

				break
			case TypeHand.DOIS_PARES:
				parActual = getMaiorValueDuplicated(valueActualHand)
				parOther = getMaiorValueDuplicated(valueOtherHand)

				break

		}

		if (parActual != parOther) {
			byte maior = Math.max(parActual, parOther)
			desempate = maior == parActual ? Resultado.WIN : Resultado.LOSS
		}

		desempate = desempate == Resultado.DRAW ? desempateNumerico(otherHand) : desempate

		return desempate

	}


}
