package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ProblemaService {

    def save(Problema problemaCreado, def session) {
        def necesitadoActual = Necesitado.get(session["usuarioActual"].id)
        def problema = necesitadoActual.crearProblema(problemaCreado.descripcion,
                                                      problemaCreado.rubro,
                                                      problemaCreado.ubicacion,
                                                      problemaCreado.barrio,
                                                      [],
                                                      problemaCreado.emergencia)
        problema.save()
    }

    def obtenerCotizacionesDeProblema(int problemaId) {
        print "El problema ${Problema.get(problemaId)} tiene las cotizaciones: "
        println Problema.get(problemaId).cotizaciones
        def cotizaciones = Problema.get(problemaId).cotizaciones
        return cotizaciones
    }
}
