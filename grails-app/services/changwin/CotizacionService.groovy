package changwin

import java.time.LocalDateTime
import grails.gorm.transactions.Transactional

@Transactional
class CotizacionService {
    def save(Cotizacion cotizacionCreada, def servletContext, def params) {
        def expertoActual = Experto.get(servletContext["usuarioActual"].id)
        BigDecimal costoFinal = new BigDecimal(params.costo)
        def cotizacion = expertoActual.cotizarProblema(Problema.get(servletContext["problemaForLastCreatedCotizacion"].id),
                                                       costoFinal)
        cotizacion.save()
    }

    def aceptarCotizacionDeExperto(int cotizacionId, def servletContext, def params) {
        def necesitadoActual = Necesitado.get(servletContext["usuarioActual"].id)
        def cotizacion = Cotizacion.get(cotizacionId)
        necesitadoActual.aceptarCotizacion(cotizacion.problema, cotizacion, LocalDateTime.now())
    }
}
