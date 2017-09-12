package br.com.zg

class PokerHand {
    private String[] cards
    private ValidHand validHand
    private TypeHand typeHand

    PokerHand(String hand) {

        hand = hand.toUpperCase()

        this.cards = hand.split(" ")

        if (!Checker.isValidHand(this.cards)) {
            validHand = ValidHand.INVALID
        } else {
            validHand = ValidHand.VALID

            this.typeHand = Checker.whichHand(this)
        }

    }

    Enum compareWith(PokerHand otherHand) {

        Enum result

        if (this.validHand != ValidHand.VALID || otherHand.validHand != ValidHand.VALID) {

            result = ValidHand.SOME_OF_HAND_IS_INVALID

        } else {

            switch (Checker.isHandEquals(this, otherHand)) {

                case true:

                    result = Resultado.DRAW

                    break

                case false:


                    if (this.typeHand == otherHand.typeHand) {

                        result = desempatar(otherHand)

                    } else {

                        int maior = Math.max(this.typeHand.ordinal(), otherHand.typeHand.ordinal())
                        result = maior == this.typeHand.ordinal() ? Resultado.WIN : Resultado.LOSS

                    }

                    break
                default:break
            }

        }

        return result
    }

    private Enum desempatar(PokerHand otherHand) {

        Enum desempate = Resultado.DRAW
        PlayingCards baralho = PlayingCards.getInstance()
        ArrayList valueActualHand = baralho.getValuesFromCards(this.cards)
        ArrayList valueOtherHand = baralho.getValuesFromCards(otherHand.cards)

        for (int i = valueActualHand.size(); i >= 0; i--) {

            if (valueActualHand.get(i) != valueOtherHand.get(i)) {
                int maior = Math.max(valueActualHand.get(i), valueOtherHand.get(i))
                desempate = maior == valueActualHand.get(i) ? Resultado.WIN : Resultado.LOSS
                break
            }
        }
        return desempate
    }

}
