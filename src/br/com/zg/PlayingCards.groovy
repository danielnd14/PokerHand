package br.com.zg

class PlayingCards {

	private HashMap allCards

	private String[] suits

	private static final PlayingCards instance = new PlayingCards()

	static PlayingCards getInstance() {
		return instance
	}

	private PlayingCards() {

		allCards = [

				"2S": 2,
				"2H": 2,
				"2D": 2,
				"2C": 2,

				"3S": 3,
				"3H": 3,
				"3D": 3,
				"3C": 3,

				"4S": 4,
				"4H": 4,
				"4D": 4,
				"4C": 4,

				"5S": 5,
				"5H": 5,
				"5D": 5,
				"5C": 5,

				"6S": 6,
				"6H": 6,
				"6D": 6,
				"6C": 6,

				"7S": 7,
				"7H": 7,
				"7D": 7,
				"7C": 7,

				"8S": 8,
				"8H": 8,
				"8D": 8,
				"8C": 8,

				"9S": 9,
				"9H": 9,
				"9D": 9,
				"9C": 9,

				"TS": 10,
				"TH": 10,
				"TD": 10,
				"TC": 10,

				"JS": 11,
				"JH": 11,
				"JD": 11,
				"JC": 11,

				"QS": 12,
				"QH": 12,
				"QD": 12,
				"QC": 12,

				"KS": 13,
				"KH": 13,
				"KD": 13,
				"KC": 13,

				"AS": 14,
				"AH": 14,
				"AD": 14,
				"AC": 14
		]

		this.suits = ["S", "H", "D", "C"]


	}

	boolean exists(String card) {

		return this.allCards.containsKey(card)
	}

	String getSuit(String card) {

		String retorno
		this.suits.each {

			if (it.equals(card.substring(1))) {
				retorno = it
			}

		}

		return retorno
	}


}
