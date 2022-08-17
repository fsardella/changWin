package changwin


@groovy.transform.Immutable
class Dinero {
    BigDecimal monto

    static constraints = {
        monto blank: false, nullable: false
    }

    
    String toString() {
        return '$' << monto.setScale(2, BigDecimal.ROUND_CEILING)
    }
}
