package br.com.zg

class Card implements Comparable<Card> {

	private Suit naipe
	private CardValue value

	Card(String id) {


		setNaipe(id)
		setValue(id)
	}

	private setNaipe(String id) {
		String naip = id.substring(1,2)
		

	}
	private setValue(String id) {


	}

	Suit getNaipe() {
		return naipe
	}


	CardValue getValue() {
		return value
	}

	@Override
	int compareTo(Card o) {

		if (this.value < o.value) {
			return -1
		}
		if (this.value > o.value) {
			return 1
		}
		return 0
	}
}
