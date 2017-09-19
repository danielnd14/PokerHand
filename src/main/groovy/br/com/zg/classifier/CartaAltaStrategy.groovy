package br.com.zg.classifier

import br.com.zg.HandType

class CartaAltaStrategy implements PokerClassifier {

	@Override
	boolean classificar() {
		return true
	}

	@Override
	HandType getThisHandType() {
		return HandType.CARTA_ALTA
	}
}
