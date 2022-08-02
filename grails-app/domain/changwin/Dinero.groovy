package changwin

class Dinero {
    BigDecimal monto

    static constraints = {
        monto blank: false, nullable: false
    }
}
