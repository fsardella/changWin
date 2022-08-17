package changwin

import grails.gorm.transactions.Transactional

@Transactional
class CotizacionService {

    def save(Cotizacion cotizacionCreada, def servletContext, def params) {
        def expertoActual = Experto.get(servletContext["usuarioActual"].id)
        BigDecimal costoFinal = new BigDecimal(params.costo.monto)
        def cotizacion = expertoActual.cotizarProblema(Problema.get(servletContext["problemaForLastCreatedCotizacion"].id),
                                                       costoFinal)
        println cotizacion
        cotizacion.save()
    }
}
