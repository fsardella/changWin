package changwin

import java.time.LocalDateTime
import grails.gorm.transactions.Transactional

@Transactional
class CotizacionService {
    def save(Cotizacion cotizacionCreada, def session, def params) {
        def expertoActual = Experto.get(session["usuarioActual"].id)
        BigDecimal costoFinal = new BigDecimal(params.costo)
        def cotizacion = expertoActual.cotizarProblema(Problema.get(params.idProblema),
                                                       costoFinal)
        cotizacion.save()
    }

    def aceptarCotizacionDeExperto(int cotizacionId, def session, def params) {
        def necesitadoActual = Necesitado.get(session["usuarioActual"].id)
        def cotizacion = Cotizacion.get(cotizacionId)
        LocalDateTime fechaDeReunion = LocalDateTime.of(params.fechaReunion_year.toInteger(), params.fechaReunion_month.toInteger(), params.fechaReunion_day.toInteger(), params.fechaReunion_hour.toInteger(), params.fechaReunion_minute.toInteger(), 0, 0)
        necesitadoActual.aceptarCotizacion(cotizacion.problema, cotizacion, fechaDeReunion)
    }
}
