package changwin

import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Transactional
class CertificadoService {

    def save(Certificado certificadoCreado, def servletContext, def params) {
        println params.fechaEmision
        println params.fechaEmision_year
        LocalDateTime fechaDeEmision = LocalDateTime.of(params.fechaEmision_year.toInteger(), params.fechaEmision_month.toInteger(), params.fechaEmision_day.toInteger(), params.fechaEmision_hour.toInteger(), params.fechaEmision_minute.toInteger(), 0, 0)
        LocalDateTime fechaDeVencimiento = LocalDateTime.of(params.fechaVencimiento_year.toInteger(), params.fechaVencimiento_month.toInteger(), params.fechaVencimiento_day.toInteger(), params.fechaVencimiento_hour.toInteger(), params.fechaVencimiento_minute.toInteger(), 0, 0)
        def expertoActual = Experto.get(servletContext["usuarioActual"].id)
        def certificado = new Certificado(rubro: certificadoCreado.rubro,
                                          numeroMatricula: certificadoCreado.numeroMatricula,
                                          fechaEmision: fechaDeEmision,
                                          fechaVencimiento: fechaDeVencimiento,
                                          experto: expertoActual)
        expertoActual.agregarRubro(certificado,
                                   servletContext["enteCertificador"])
        certificado.save()
    }
}
