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
        print "El experto ${Experto.get(expertoId)} tiene las cotizaciones: "
        println Experto.get(expertoId).cotizaciones
        def cotizaciones = Experto.get(expertoId).cotizaciones
        return cotizaciones
    }
}

