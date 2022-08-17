package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ProblemaController {

    ProblemaService problemaService

    static scaffold = Problema

    def save(Problema problema) {
        problemaService.save(problema, session)
        redirect session["usuarioActual"]
    }

    def mostrarCotizacionesDeProblema() {
        Set<Cotizacion> response = problemaService.obtenerCotizacionesDeProblema(params.id.toInteger())
        respond (response, model:[cotizacionesDeProblema: response, cantCotizacionesProblema: response.size(), maxCotizacionesPerPage: 25])
    }
}
