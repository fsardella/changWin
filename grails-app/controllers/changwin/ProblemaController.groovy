package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ProblemaController {

    ProblemaService problemaService

    static scaffold = Problema

    def save(Problema problema) {
        try {
            problemaService.save(problema, session)
            redirect session["usuarioActual"]
        } catch (Exception e) {
            respond problema.errors, view:'create'
        }
    }

    def mostrarCotizacionesDeProblema() {
        Set<Cotizacion> response = problemaService.obtenerCotizacionesDeProblema(params.id.toInteger())
        respond (response, model:[cotizacionesDeProblema: response, cantCotizacionesProblema: response.size(), maxCotizacionesPerPage: 25])
    }
}
