package changwin

class Cotizacion {
    BigDecimal costo
    Experto experto

    static constraints = {
        costo ([blank:false, nullable:false])
        experto ([blank:false, nullable:false])   
    }

    def getCosto() {
        return costo
    }

    def getExperto() {
        return this.experto
    }
}
