package changwin

import grails.gorm.transactions.Transactional

import java.time.LocalDateTime

@Transactional
class CertificadoService {

    def save(Certificado certificadoCreado, def servletContext) {
        def expertoActual = Experto.get(servletContext["usuarioActual"].id)
        // def certificado = new Certificado(rubro: certificadoCreado.rubro,
        //                                   numeroMatricula: certificadoCreado.numeroMatricula,
        //                                   fechaEmision: certificadoCreado.fechaEmision,
        //                                   fechaVencimiento: certificadoCreado.fechaVencimiento,
        //                                   experto: expertoActual)
        def certificado = new Certificado(rubro: certificadoCreado.rubro,
                                          numeroMatricula: certificadoCreado.numeroMatricula,
                                          fechaEmision: LocalDateTime.now(),
                                          fechaVencimiento: LocalDateTime.of(2023,2,3,6,30,40,50000),
                                          experto: expertoActual)
        expertoActual.agregarRubro(certificado,
                                   servletContext["enteCertificador"])
        certificado.save()
    }
}
