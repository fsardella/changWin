package changwin

import grails.gorm.transactions.Transactional
import java.time.LocalDateTime

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

    def aceptarCotizacion(int cotizacionId, def servletContext, def params) {
        def necesitadoActual = Necesitado.get(servletContext["usuarioActual"].id)
        def cotizacion = Cotizacion.get(cotizacionId)
        necesitadoActual.aceptarCotizacion(cotizacion.problema, cotizacion, LocalDateTime.now())
        println "la cotizacion del estado es ${cotizacion.estado}"
        cotizacion.save()
        necesitadoActual.save()
    }
}
