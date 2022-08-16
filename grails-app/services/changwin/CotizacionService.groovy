package changwin

import grails.gorm.transactions.Transactional

@Transactional
class CotizacionService {

    def save(Cotizacion cotizacionCreada, def servletContext) {
        def expertoActual = Experto.get(servletContext["usuarioActual"].id)
        def cotizacion = expertoActual.cotizarProblema(servletContext["problemaForLastCreatedCotizacion"],
                                                       cotizacionCreada.costo.monto)
        cotizacion.save()
    }
}
