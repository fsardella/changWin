package changwin

import java.time.LocalDateTime
import grails.gorm.transactions.Transactional

@Transactional
class CotizacionService {
    def save(Cotizacion cotizacionCreada, def session, def params) {
        def expertoActual = Experto.get(session["usuarioActual"].id)
        BigDecimal costoFinal = new BigDecimal(params.costo)
        def cotizacion = expertoActual.cotizarProblema(Problema.get(session["problemaForLastCreatedCotizacion"].id),
                                                       costoFinal)
        cotizacion.save()
    }

    def aceptarCotizacionDeExperto(int cotizacionId, def session, def params) {
        def necesitadoActual = Necesitado.get(session["usuarioActual"].id)
        def cotizacion = Cotizacion.get(cotizacionId)
        necesitadoActual.aceptarCotizacion(cotizacion.problema, cotizacion, LocalDateTime.now())
    }
}
