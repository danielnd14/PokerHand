package br.com.zg.classifier

import br.com.zg.HandType

interface PokerClassifier {

	boolean classificar()

	HandType getThisHandType()
}