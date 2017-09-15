package br.com.zg

class Card implements Comparable<Card> {

	private Suit naipe
	private CardValue value

	Card(String id) throws IllegalArgumentException,StringIndexOutOfBoundsException{

		setNaipe(id)
		setValue(id)
	}

	private setNaipe(String id) throws StringIndexOutOfBoundsException{

		String tipo = id.substring(1, 2)

		Map<String, Suit> mNaipes = ["S": Suit.SPADES, "H": Suit.HEARTS, "D": Suit.DIAMONDS, "C": Suit.CLUBS]

		if (mNaipes.containsKey(tipo)) {
			this.naipe = mNaipes.get(tipo)

		} else {
			throw new IllegalArgumentException("Naipe da carta invalido!")
		}

		mNaipes.finalize()


	}

	private setValue(String id) throws StringIndexOutOfBoundsException {

		String value = id.substring(0, 1)
		String[] numeros = "2".."9"
		String[] letras = ["T", "J", "Q", "K", "A"]

		if (numeros.contains(value)) {

			int index = Integer.parseInt(value)
			index -= 2
			this.value = CardValue.values().getAt(index)

		} else if (letras.contains(value)) {

			switch (value) {

				case "T":
					this.value = CardValue.values().getAt(8)
					break
				case "J":
					this.value = CardValue.values().getAt(9)
					break
				case "Q":
					this.value = CardValue.values().getAt(10)
					break
				case "K":
					this.value = CardValue.values().getAt(11)
					break
				case "A":
					this.value = CardValue.values().getAt(12)
					break
			}

		} else {
			throw new IllegalArgumentException("Valor da carta invalido!")
		}

	}

	Suit getNaipe() {
		return naipe
	}


	CardValue getValue() {
		return value
	}

	@Override
	int compareTo(Card o) {

		if (this.value.ordinal() < o.value.ordinal()) {
			return -1
		}

		if (this.value.ordinal() > o.value.ordinal()) {
			return 1
		}
		return 0
	}

	@Override
	String toString() {
		return this.value.toString() + "_" + this.naipe.toString()
	}
}
