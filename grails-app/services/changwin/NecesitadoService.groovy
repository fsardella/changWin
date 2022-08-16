package changwin

import grails.gorm.transactions.Transactional

@Transactional
class NecesitadoService {
    def save(Necesitado necesitadoCreado, def servletContext) {
        def necesitado = necesitadoCreado
        necesitado.save()
        servletContext["usuarioActual"] = necesitado
    }

    def obtenerProblemasDeNecesitado(int necesitadoId) {
        print "El necesitado ${Necesitado.get(necesitadoId)} tiene los problemas: "
        println Necesitado.get(necesitadoId).problemas
        def problemas = Necesitado.get(necesitadoId).problemas
        return problemas
    }

    def logNecesitado(Necesitado necesitadoLogInfo, def servletContext) {
        def necesitado = Necesitado.findByNombre(necesitadoLogInfo.nombre)
        if (necesitado && necesitado.contrasenia == necesitadoLogInfo.contrasenia) {
            servletContext["usuarioActual"] = necesitado
            return necesitado
        }
        necesitadoLogInfo.errors.reject('contraseniaInvalida', 'Login inválido')
    }
}
