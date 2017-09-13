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

		pokerHand1       | pokerHand2          || resultado
		"9C TC JC QC KC" | "9C 9H 5C 5H AC"    || Resultado.WIN
		"TC TH 5C 5H KH" | "9C 9H 5C 5H AC"    || Resultado.WIN
		"TS TD KC JC 7C" | "JS JC AS KC TD"    || Resultado.LOSS
		"7H 7C QC JS TS" | "7D 7C JS TS 6D"    || Resultado.WIN
		"5S 5D 8C 7S 6H" | "7D 7S 5S 5D JS"    || Resultado.LOSS
		"AS AD KD 7C 3D" | "AD AH KD 7C 4S"    || Resultado.LOSS
		"TS JS QS KS AS" | "AC AH AS AS KS"    || Resultado.WIN
		"TS JS QS KS AS" | "TC JS QC KS AC"    || Resultado.WIN
		"TS JS QS KS AS" | "QH QS QC AS 8H"    || Resultado.WIN
		"AC AH AS AS KS" | "TC JS QC KS AC"    || Resultado.WIN
		"AC AH AS AS KS" | "QH QS QC AS 8H"    || Resultado.WIN
		"TC JS QC KS AC" | "QH QS QC AS 8H"    || Resultado.WIN
		"7H 8H 9H TH JH" | "JH JC JS JD TH"    || Resultado.WIN
		"7H 8H 9H TH JH" | "4H 5H 9H TH JH"    || Resultado.WIN
		"7H 8H 9H TH JH" | "7C 8S 9H TH JH"    || Resultado.WIN
		"7H 8H 9H TH JH" | "TS TH TD JH JD"    || Resultado.WIN
		"7H 8H 9H TH JH" | "JH JD TH TC 4C"    || Resultado.WIN
		"JH JC JS JD TH" | "4H 5H 9H TH JH"    || Resultado.WIN
		"JH JC JS JD TH" | "7C 8S 9H TH JH"    || Resultado.WIN
		"JH JC JS JD TH" | "TS TH TD JH JD"    || Resultado.WIN
		"JH JC JS JD TH" | "JH JD TH TC 4C"    || Resultado.WIN
		"4H 5H 9H TH JH" | "7C 8S 9H TH JH"    || Resultado.WIN
		"4H 5H 9H TH JH" | "TS TH TD JH JD"    || Resultado.LOSS
		"4H 5H 9H TH JH" | "JH JD TH TC 4C"    || Resultado.WIN
		"7C 8S 9H TH JH" | "TS TH TD JH JD"    || Resultado.LOSS
		"7C 8S 9H TH JH" | "JH JD TH TC 4C"    || Resultado.WIN
		"TS TH TD JH JD" | "JH JD TH TC 4C"    || Resultado.WIN
		"2S 3H 4D 5H 6D" | "5H 6D 7H 8C 9C"    || Resultado.LOSS
		"2S 3H 4H 5H 6D" | "2S 3H 4D 5H 6C"    || Resultado.DRAW
		"2H 3H 4H 5H 7H" | "2D 3D 4D 5D 8D"    || Resultado.LOSS
		"2S 2H 2D 5H 6D" | "5H 5D 5H 8C 9C"    || Resultado.LOSS
		"2H 3H 4H 5H 6H" | "5H 6H 7H 8H 9H"    || Resultado.LOSS
		"TH JH QH KH AH" | "TC JC QC KC AC"    || Resultado.DRAW
		"TH TH TH TH AS" | "9C 9C 9C 9C 2S"    || Resultado.WIN
		"TH TH TH AH AS" | "9C 9C 9C 2C 2S"    || Resultado.WIN
		"2H 4H 6H 8H AS" | "3C 5C 6C 8C JS"    || Resultado.WIN
		"2H 2H 2H AH AS" | "2C 2C 2C JC JS"    || Resultado.WIN
		"2H 2H 5H AH AS" | "2C 2C 6C AC AS"    || Resultado.LOSS
		"4H 4H 2H 2H AS" | "4C 4C 3C 3C AS"    || Resultado.LOSS
		"2H 2H 5H 7H 7S" | "7C 7C 8C 8C tS"    || Resultado.LOSS
		"2H 2H 5H 7H 7S" | "2H 2H 5H 7H 7S"    || Resultado.DRAW
		"7C 7C 8C 8C tS" | "2H 2H 5H 7H 7S"    || Resultado.WIN
		"3C 8C 8C 9C 9S" | "2H 2H 5H 7H 7S"    || Resultado.WIN
		"2H 2H 5H 7H 7S" | "3C 8C 8C 9C 9S"    || Resultado.LOSS



	}
}
