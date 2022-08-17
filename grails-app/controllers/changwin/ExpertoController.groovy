package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ExpertoController {
    ExpertoService expertoService

    static scaffold = Experto

    def save(Experto experto) {
        expertoService.save(experto, session)
        redirect experto
    }

    def login() {
        respond new Experto()
    }

    def loginConfirmed(Experto experto) {
        if (session["usuarioActual"]) {return}
        try {
            redirect expertoService.logExperto(experto, session)
        } catch (Exception e) {
            respond experto.errors, view:'login'
        }
    }

    def logout() {
        session.invalidate()
        redirect uri:"/"
    }

    def mostrarCotizaciones() {
        Set<Cotizacion> response = expertoService.obtenerCotizacionesDeExperto(params.id.toInteger())
        respond (response, model:[cotizacionesDeExperto: response, cantCotizacionesUsuario: response.size(), maxCotizacionesPerPage: 25])
    }

    def mostrarProblemas() {
        Set<Problema> response = expertoService.obtenerProblemasDeExperto(params.id.toInteger())
        respond (response, model:[problemasDeExperto: response, cantProblemasUsuario: response.size(), maxProblemasPerPage: 25])
    }
}
