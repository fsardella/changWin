package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ProblemaService {

    def save(Problema problemaCreado, def servletContext) {
        def necesitadoActual = Necesitado.get(servletContext["usuarioActual"].id)
        def problema = necesitadoActual.crearProblema(problemaCreado.descripcion,
                                                      problemaCreado.rubro,
                                                      problemaCreado.ubicacion,
                                                      problemaCreado.barrio,
                                                      [],
                                                      problemaCreado.emergencia)
        problema.save()
    }
}
