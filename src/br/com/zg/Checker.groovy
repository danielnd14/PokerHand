package br.com.zg

final class Checker {

    private static PlayingCards baralho = new PlayingCards()

    static boolean isHandEquals(PokerHand actualHand, PokerHand otherHand) {

        boolean retorno = true

        actualHand.cards.each {

            if (!otherHand.cards.contains(it)) {
                retorno = false
            }

        }

        return retorno
    }

    static boolean isValidHand(String[] cards) {

        boolean valid = cards.size() == 5

        if (valid) {
            cards.each {
                if (!baralho.exists(it)) {
                    valid = false
                }
            }
        }

        return valid
    }

    static TypeHand whichHand(PokerHand pkHand) {


        HandClassifier handClassifier = new HandClassifier(pkHand)

        if (handClassifier.isRoyal()) {

            return TypeHand.ROYAL_FLUSH

        } else if (handClassifier.isStraight()) {

            return TypeHand.STRAIGHT_FLUSH

        } else if (handClassifier.isQuadra()) {

            return TypeHand.QUADRA

        } else if (handClassifier.isFull()) {

            return TypeHand.FULL_HOUSE

        } else if (handClassifier.isFlush()) {

            return  TypeHand.FLUSH

        } else if (handClassifier.isSequence()) {

            return TypeHand.SEQUENCIA

        } else if (handClassifier.isTrinca()) {

            return TypeHand.TRINCA

        } else if (handClassifier.is2pares()) {

            return TypeHand.DOIS_PARES

        } else if (handClassifier.is1Par()) {

            return  TypeHand.UM_PAR

        } else if (handClassifier.isCartaAlta()) {

            return  TypeHand.CARTA_ALTA

        }

    }


}
