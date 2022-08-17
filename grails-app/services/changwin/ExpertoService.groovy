package changwin

import grails.gorm.transactions.Transactional

@Transactional
class ExpertoService {

    def save(Experto expertoCreado, def session) {
        def experto = expertoCreado
        experto.save()
        session["usuarioActual"] = experto
    }


    def logExperto(Experto expertoLogInfo, def session) {
        def experto = Experto.findByNombre(expertoLogInfo.nombre)
        if (experto && experto.contrasenia == expertoLogInfo.contrasenia) {
            session["usuarioActual"] = experto
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
        def problemas = Problema.all.findAll{prob -> expertoActual.certificados.any{cert -> prob.rubro.nombre == cert.rubro.nombre && cert.esValido() && !prob.estaConfirmado()}}
        return problemas
    }

    def obtenerCertificadosDeExperto(int expertoId) {
        def certificados = Experto.get(expertoId).certificados
        return certificados
    }
}

