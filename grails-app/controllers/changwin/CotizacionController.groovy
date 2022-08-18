package changwin

import grails.gorm.transactions.Transactional

@Transactional
class CotizacionController {
    CotizacionService cotizacionService

    static scaffold = Cotizacion

    def create() {
        respond new Cotizacion(params)
    }
    
    def save(Cotizacion cotizacion) {
        try {
            cotizacionService.save(cotizacion, session, params)
            redirect session["usuarioActual"]
        } catch (Exception e) {
            respond cotizacion.errors, view:'create'
        }

    }

    def aceptarCotizacion() {
    }
    
    def aceptar() {
        cotizacionService.aceptarCotizacionDeExperto(params.id.toInteger(), session, params)
        redirect Cotizacion.get(params.id.toInteger())
    }
}
