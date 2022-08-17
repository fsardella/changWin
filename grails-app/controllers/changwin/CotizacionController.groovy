package changwin

import grails.gorm.transactions.Transactional

@Transactional
class CotizacionController {
    CotizacionService cotizacionService

    static scaffold = Cotizacion

    def create() {
        session["problemaForLastCreatedCotizacion"] = Problema.get(params.id)
        respond new Cotizacion(params)
    }
    
    def save(Cotizacion cotizacion) {
        cotizacionService.save(cotizacion, session, params)
        redirect session["usuarioActual"]
    }
    
    def aceptar() {
        cotizacionService.aceptarCotizacionDeExperto(params.id.toInteger(), session, params)
        redirect Cotizacion.get(params.id.toInteger())
    }
}
