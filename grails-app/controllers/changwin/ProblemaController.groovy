package changwin

class ProblemaController {

    ProblemaService problemaService

    static scaffold = Problema

    def save(Problema problema) {
        problemaService.save(problema, servletContext)
        redirect servletContext["usuarioActual"]
    }

    def mostrarCotizacionesDeProblema() {
        Set<Cotizacion> response = problemaService.obtenerCotizacionesDeProblema(params.id.toInteger())
        respond (response, model:[cotizacionesDeProblema: response, cantCotizacionesProblema: response.size(), maxCotizacionesPerPage: 25])
    }
}
