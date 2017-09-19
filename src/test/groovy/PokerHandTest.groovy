package br.com.zg

import spock.lang.Specification
import spock.lang.Unroll

class PokerHandTest extends Specification {
	@Unroll
	def "Compara um mao com a outra "() {

		given:
		PokerHand pkHand = new PokerHand(pokerHand1)
		PokerHand pkHand2 = new PokerHand(pokerHand2)

		expect:
		resultado == pkHand.compareWith(pkHand2)

		where:

		pokerHand1       | pokerHand2       || resultado
		"9C TC JC QC KC" | "9C 9H 5C 5H AC" || Resultado.WIN
		"TC TH 5C 5H KH" | "9C 9H 5C 5H AC" || Resultado.WIN
		"TS TD KC JC 7C" | "JS JC AS KC TD" || Resultado.LOSS
		"7H 7C QC JS TS" | "7D 7C JS TS 6D" || Resultado.WIN
		"5S 5D 8C 7S 6H" | "7D 7S 5S 5D JS" || Resultado.LOSS
		"AS AD KD 7C 3D" | "AD AH KD 7C 4S" || Resultado.LOSS
		"TS JS QS KS AS" | "AC AH AS AS KS" || Resultado.WIN
		"TS JS QS KS AS" | "TC JS QC KS AC" || Resultado.WIN
		"TS JS QS KS AS" | "QH QS QC AS 8H" || Resultado.WIN
		"AC AH AS AS KS" | "TC JS QC KS AC" || Resultado.WIN
		"AC AH AS AS KS" | "QH QS QC AS 8H" || Resultado.WIN
		"TC JS QC KS AC" | "QH QS QC AS 8H" || Resultado.WIN
		"7H 8H 9H TH JH" | "JH JC JS JD TH" || Resultado.WIN
		"7H 8H 9H TH JH" | "4H 5H 9H TH JH" || Resultado.WIN
		"7H 8H 9H TH JH" | "7C 8S 9H TH JH" || Resultado.WIN
		"7H 8H 9H TH JH" | "TS TH TD JH JD" || Resultado.WIN
		"7H 8H 9H TH JH" | "JH JD TH TC 4C" || Resultado.WIN
		"JH JC JS JD TH" | "4H 5H 9H TH JH" || Resultado.WIN
		"JH JC JS JD TH" | "7C 8S 9H TH JH" || Resultado.WIN
		"JH JC JS JD TH" | "TS TH TD JH JD" || Resultado.WIN
		"JH JC JS JD TH" | "JH JD TH TC 4C" || Resultado.WIN
		"4H 5H 9H TH JH" | "7C 8S 9H TH JH" || Resultado.WIN
		"4H 5H 9H TH JH" | "TS TH TD JH JD" || Resultado.LOSS
		"4H 5H 9H TH JH" | "JH JD TH TC 4C" || Resultado.WIN
		"7C 8S 9H TH JH" | "TS TH TD JH JD" || Resultado.LOSS
		"7C 8S 9H TH JH" | "JH JD TH TC 4C" || Resultado.WIN
		"TS TH TD JH JD" | "JH JD TH TC 4C" || Resultado.WIN
		"2S 3H 4D 5H 6D" | "5H 6D 7H 8C 9C" || Resultado.LOSS
		"2S 3H 4H 5H 6D" | "2S 3H 4D 5H 6C" || Resultado.DRAW
		"2H 3H 4H 5H 7H" | "2D 3D 4D 5D 8D" || Resultado.LOSS
		"2S 2H 2D 5H 6D" | "5H 5D 5H 8C 9C" || Resultado.LOSS
		"2H 3H 4H 5H 6H" | "5H 6H 7H 8H 9H" || Resultado.LOSS
		"TH JH QH KH AH" | "TC JC QC KC AC" || Resultado.DRAW
		"TH TH TH TH AS" | "9C 9C 9C 9C 2S" || Resultado.WIN
		"TH TH TH AH AS" | "9C 9C 9C 2C 2S" || Resultado.WIN
		"2H 4H 6H 8H AS" | "3C 5C 6C 8C JS" || Resultado.WIN
		"2H 2H 2H AH AS" | "2C 2C 2C JC JS" || Resultado.WIN
		"2H 2H 5H AH AS" | "2C 2C 6C AC AS" || Resultado.LOSS
		"4H 4H 2H 2H AS" | "4C 4C 3C 3C AS" || Resultado.LOSS
		"2H 2H 5H 7H 7S" | "7C 7C 8C 8C tS" || Resultado.LOSS
		"2H 2H 5H 7H 7S" | "2H 2H 5H 7H 7S" || Resultado.DRAW
		"7C 7C 8C 8C tS" | "2H 2H 5H 7H 7S" || Resultado.WIN
		"3C 8C 8C 9C 9S" | "2H 2H 5H 7H 7S" || Resultado.WIN
		"2H 2H 5H 7H 7S" | "3C 8C 8C 9C 9S" || Resultado.LOSS
		"2H 3C 3D 3S 6H" | "2C 3D 4D 5C 6C" || Resultado.LOSS
		"2c 8s 4s 5h td" | "tC 3h 4c 7h 8C" || Resultado.LOSS
	}

	@Unroll
	def "Verifica mão criada"() {

		given:

		PokerHand pkHand = new PokerHand(pokerHand1)

		expect:
		resultado == pkHand.typeHand

		where:

		pokerHand1       || resultado
		"9C TC JC QC KC" || HandType.STRAIGHT_FLUSH
		"TC TH 5C 5H KH" || HandType.DOIS_PARES
		"TS TD KC JC 7C" || HandType.UM_PAR
		"7H 7C QC JS TS" || HandType.UM_PAR
		"5S 5D 8C 7S 6H" || HandType.UM_PAR
		"AS AD KD 7C 3D" || HandType.UM_PAR
		"TS JS QS KS AS" || HandType.ROYAL_FLUSH
		"TS JS QS KS AS" || HandType.ROYAL_FLUSH
		"TS JS QS KS AS" || HandType.ROYAL_FLUSH
		"AC AH AS AS KS" || HandType.QUADRA
		"AC AH AS AS KS" || HandType.QUADRA
		"TC JS QC KS AC" || HandType.SEQUENCIA
		"7H 8H 9H TH JH" || HandType.STRAIGHT_FLUSH
		"7H 8H 9H TH JH" || HandType.STRAIGHT_FLUSH
		"7H 8H 9H TH JH" || HandType.STRAIGHT_FLUSH
		"7H 8H 9H TH JH" || HandType.STRAIGHT_FLUSH
		"7H 8H 9H TH JH" || HandType.STRAIGHT_FLUSH
		"JH JC JS JD TH" || HandType.QUADRA
		"JH JC JS JD TH" || HandType.QUADRA
		"JH JC JS JD TH" || HandType.QUADRA
		"JH JC JS JD TH" || HandType.QUADRA
		"4H 5H 9H TH JH" || HandType.FLUSH
		"4H 5H 9H TH JH" || HandType.FLUSH
		"4H 5H 9H TH JH" || HandType.FLUSH
		"7C 8S 9H TH JH" || HandType.SEQUENCIA
		"7C 8S 9H TH JH" || HandType.SEQUENCIA
		"TS TH TD JH JD" || HandType.FULL_HOUSE
		"2S 3H 4D 5H 6D" || HandType.SEQUENCIA
		"2S 3H 4H 5H 6D" || HandType.SEQUENCIA
		"2H 3H 4H 5H 7H" || HandType.FLUSH
		"2S 2H 2D 5H 6D" || HandType.TRINCA
		"2H 3H 4H 5H 6H" || HandType.STRAIGHT_FLUSH
		"TH JH QH KH AH" || HandType.ROYAL_FLUSH
		"TH TH TH TH AS" || HandType.QUADRA
		"TH TH TH AH AS" || HandType.FULL_HOUSE
		"2H 4H 6H 8H AS" || HandType.CARTA_ALTA
		"2H 2H 2H AH AS" || HandType.FULL_HOUSE
		"2H 2H 5H AH AS" || HandType.DOIS_PARES
		"4H 4H 2H 2H AS" || HandType.DOIS_PARES
		"2H 2H 5H 7H 7S" || HandType.DOIS_PARES
		"2H 2H 5H 7H 7S" || HandType.DOIS_PARES
		"7C 7C 8C 8C tS" || HandType.DOIS_PARES
		"3C 8C 8C 9C 9S" || HandType.DOIS_PARES
		"2H 2H 5H 7H 7S" || HandType.DOIS_PARES
		"2H 3C 3D 3S 6H" || HandType.TRINCA
	}

	@Unroll
	def "Verfica Mãos Válidas"() {
		given:

		PokerHand pkhand
		when:
		pkhand = new PokerHand(pokerHand1)
		then:
		IllegalArgumentException e = thrown()

		where:

		pokerHand1          || resultado
		"9C TC JC QC çC"    || IllegalArgumentException
		"9C TC JC QC çC JT" || IllegalArgumentException
		"9C TC JC QC "      || IllegalArgumentException
	}

	@Unroll
	def "Verfica Carta Válidas"() {
		given:

		Card carta
		when:
		carta = new Card(card)
		then:
		IllegalArgumentException e = thrown()


		where:

		card  || resultado
		"9t"  || IllegalArgumentException
		"9e"  || IllegalArgumentException
		"9v"  || IllegalArgumentException
		"9x"  || IllegalArgumentException
		"45x" || IllegalArgumentException
		"4g"  || IllegalArgumentException
		"vp"  || IllegalArgumentException
	}

	@Unroll
	def "Verfica Carta Válidas 2"() {
		given:

		Card carta
		when:
		carta = new Card(card)
		then:
		StringIndexOutOfBoundsException e = thrown()


		where:

		card  || resultado
		""  || StringIndexOutOfBoundsException
		"9"  || StringIndexOutOfBoundsException

	}


}
