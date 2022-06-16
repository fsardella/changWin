package changwin

class Problema {
    private LocalDate fecha
    private String descripcion
    // imagen
    List cotizaciones = []

    static constraints = {
    }

    def cotizar(Cotizacion cotizacion) {
        cotizaciones << cotizacion
    }

    def obtenerCorizaciones() {
        return cotizaciones // esto esta ok?
    }
}
