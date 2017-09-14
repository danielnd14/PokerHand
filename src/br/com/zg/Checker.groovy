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



    }


}
