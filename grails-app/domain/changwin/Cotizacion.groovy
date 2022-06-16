package changwin

class Cotizacion {
    BigDecimal costo
    Experto experto

    static constraints = {
        costo ([blank:false, nullable:false])
        experto ([blank:false, nullable:false])
        
    }

    def obtenerCosto() {
        return precio
    }

    def obtenerExperto() {
        return experto.obtenerNombre() // o mas?
    }
}
