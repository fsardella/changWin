package changwin

class Cotizacion {
    BigDecimal costo
    Experto experto

    static constraints = {
    }

    def obtenerCosto() {
        return precio
    }

    def obtenerExperto() {
        return experto.obtenerNombre() // o mas?
    }
}
