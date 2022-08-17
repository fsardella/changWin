package changwin

import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Transactional
class CertificadoService {

    def save(Certificado certificadoCreado, def servletContext, def params) {
        def expertoActual = Experto.get(servletContext["usuarioActual"].id)
        def certificado = new Certificado(rubro: certificadoCreado.rubro,
                                          numeroMatricula: certificadoCreado.numeroMatricula,
                                          fechaEmision: LocalDateTime.now(params.fechaEmision_year,
                                                                          params.fechaEmision_month,
                                                                          params.fechaEmision_day,                                                                             ,
                                                                          params.fechaEmision_hour,
                                                                          params.fechaEmision_minute,
                                                                          0,0),
                                          fechaVencimiento: LocalDateTime.of(params.fechaVencimiento_year,
                                                                             params.fechaVencimiento_month,
                                                                             params.fechaVencimiento_day,                                                                             ,
                                                                             params.fechaVencimiento_hour,
                                                                             params.fechaVencimiento_minute,
                                                                             0,0),
                                          experto: expertoActual)
        expertoActual.agregarRubro(certificado,
                                   servletContext["enteCertificador"])
        certificado.save()
    }
}
