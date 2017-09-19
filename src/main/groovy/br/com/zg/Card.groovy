package br.com.zg

class Card implements Comparable<Card> {

	private Suit naipe
	private CardValue value
	static 	Map<String, Suit> mNaipes = ["S": Suit.SPADES, "H": Suit.HEARTS, "D": Suit.DIAMONDS, "C": Suit.CLUBS]
	static String[] numeros = "2".."9"
	static String[] letras = ["T", "J", "Q", "K", "A"]

	Card(String id) throws IllegalArgumentException,StringIndexOutOfBoundsException{

		setNaipe(id)
		setValue(id)
	}

	private setNaipe(String id) throws StringIndexOutOfBoundsException{

		String tipo = id.substring(1, 2)

		if (mNaipes.containsKey(tipo)) {
			this.naipe = mNaipes.get(tipo)

		} else {
			throw new IllegalArgumentException("Naipe da carta invalido!")
		}

		mNaipes.finalize()


	}

	private setValue(String id) throws StringIndexOutOfBoundsException {

		String value = id.substring(0, 1)


		if (numeros.contains(value)) {

			int index = Integer.parseInt(value)
			index -= 2
			this.value = CardValue.values().getAt(index)

		} else if (letras.contains(value)) {

			switch (value) {

				case "T":
					this.value = CardValue.TEN
					break
				case "J":
					this.value = CardValue.VALETE
					break
				case "Q":
					this.value = CardValue.QUEEN
					break
				case "K":
					this.value = CardValue.KING
					break
				case "A":
					this.value = CardValue.ACE
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
		return this.value.ordinal() - o.value.ordinal()
	}

	@Override
	String toString() {
		return this.value.toString() + "_" + this.naipe.toString()
	}
}
