package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ExpertoService {

    def save(Experto expertoCreado, def servletContext) {
        def experto = expertoCreado
        experto.save()
        servletContext["usuarioActual"] = experto
    }


    def logExperto(Experto expertoLogInfo, def servletContext) {
        def experto = Experto.findByNombre(expertoLogInfo.nombre)
        if (experto && experto.contrasenia == expertoLogInfo.contrasenia) {
            servletContext["usuarioActual"] = experto
            return experto
        }
        expertoLogInfo.errors.reject('contraseniaInvalida', 'Login inválido')
    }

    def obtenerCotizacionesDeExperto(int expertoId) {
        def cotizaciones = Experto.get(expertoId).cotizaciones
        return cotizaciones
    }

    def obtenerProblemasDeExperto(int expertoId) {
        def expertoActual = Experto.get(expertoId)
        def problemas = Problema.all.findAll{prob -> expertoActual.certificados.any{cert -> prob.rubro.nombre == cert.rubro.nombre && cert.esValido()}}
        println "problemas son ${problemas}"
        return problemas
    }
}

