package br.com.zg

interface Classifier {

	boolean isRoyal(PokerHand pkHand)

	boolean isStraight(PokerHand pkHand)

	boolean isQuadra(PokerHand pkHand)

	boolean isFull(PokerHand pkHand)

	boolean isFlush(PokerHand pkHand)

	boolean isSequence(PokerHand pkHand)

	boolean isTrinca(PokerHand pkHand)

	boolean is2pares(PokerHand pkHand)

	boolean is1Par(PokerHand pkHand)

	boolean isCartaAlta(PokerHand pkHand)

}