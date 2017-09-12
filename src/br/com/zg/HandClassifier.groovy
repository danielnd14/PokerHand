package br.com.zg


class HandClassifier implements PokerClassifier {
    private PlayingCards baralho
    private PokerHand pkHand
    private ArrayList<Integer> valores
    private String[] cards

    HandClassifier(PlayingCards baralho, PokerHand pkHand) {

        this.baralho = baralho
        this.pkHand = pkHand
        this.cards = pkHand.cards
        valores = baralho.getValuesFromCards(this.cards)

    }

    @Override
    boolean isRoyal() {

        boolean isRoyalFlush = true

        valores.each {

            if (it <= 9) {
                isRoyalFlush = false
            }
        }
        isRoyalFlush = isRoyalFlush ? isSequenciaDevalores(valores) : false

        isRoyalFlush = isRoyalFlush ? isSameSuit(cards) : false

        return isRoyalFlush
    }

    @Override
    boolean isStraight() {

        boolean isStraightFlush = true

        isStraightFlush = isSequenciaDevalores(valores)

        isStraightFlush = isStraightFlush ? isSameSuit(cards) : false

        return isStraightFlush
    }

    @Override
    boolean isQuadra() {

        boolean quadra

        quadra = (valores.get(0) + valores.get(1) + valores.get(2) + valores.get(3)) == (valores.get(0) * 4)

        if (!quadra) {

            quadra = (valores.get(1) + valores.get(2) + valores.get(3) + valores.get(4)) == (valores.get(1) * 4)

        }

        return quadra
    }

    @Override
    boolean isFull() {

        boolean fullHouse = false

        fullHouse = valores.get(0) == valores.get(1) && (valores.get(2) + valores.get(3) + valores.get(4)) == valores.last() * 3

        if (!fullHouse) {

            fullHouse = (valores.get(0) + valores.get(1) + valores.get(2)) == valores.first() * 3 && valores.get(3) == valores.get(4)

        }

        return fullHouse
    }

    @Override
    boolean isFlush() {

        boolean flush

        flush = isSameSuit(cards)

        flush = flush ? !isSequenciaDevalores(valores) : false

        return flush
    }

    @Override
    boolean isSequence() {

        boolean sequence

        sequence = !isSameSuit(cards)

        sequence = sequence ? isSequenciaDevalores(valores) : false

        return sequence
    }

    @Override
    boolean isTrinca() {
        boolean trinca

        trinca = (valores.get(0) + valores.get(1) + valores.get(2)) == valores.first() * 3 && valores.get(3) != valores.get(4)

        if (!trinca) {

            trinca = valores.get(0) != valores.get(1) && (valores.get(2) + valores.get(3) + valores.get(4)) == valores.last() * 3

        }

        return trinca
    }

    @Override
    boolean is2pares() {
        boolean doispares

        doispares = valores.get(0) == valores.get(1) && valores.get(3) == valores.last()

        if (!doispares) {
            doispares = valores.get(0) == valores.get(1) && valores.get(2) == valores.get(3)
        }

        if (!doispares) {
            doispares = valores.get(1) == valores.get(2) && valores.get(3) == valores.get(4)
        }

        return doispares
    }

    @Override
    boolean is1Par() {
        boolean umpar

        umpar = valores.get(0) == valores.get(1)

        if (!umpar) {
            valores.get(3) == valores.last()
        }

        return umpar
    }

    @Override
    boolean isCartaAlta() {
        return true
    }

    static boolean isThereDuplicity(ArrayList valores) {

        boolean duplicity = false

        firstFor:
        for (int i = 0; i < valores.size(); i++) {
            int aux = valores.get(i)

            for (int j = i + 1; j < valores.size(); j++) {
                if (aux == valores.get(j)) {
                    duplicity = true
                    break firstFor
                }
            }

        }
        return duplicity

    }

    static boolean isSequenciaDevalores(ArrayList valores) {

        int soma = getSomaValores(valores)

        return (soma - (valores.first() * 5)) == 10 ? !isThereDuplicity(valores) : false
    }

    static int getSomaValores(ArrayList valores) {
        int soma = 0

        valores.each {
            soma += it
        }

        return soma
    }

    boolean isSameSuit(String[] cards) {
        boolean sameSuit = true

        String suit = baralho.getSuit(cards[0])
        cards.each {
            if (!suit.equals(baralho.getSuit(it))) {
                sameSuit = false
            }

        }

        return sameSuit

    }
}
