package changwin

import grails.gorm.transactions.Transactional

@Transactional
class CertificadoController {
    CertificadoService certificadoService

    static scaffold = Certificado

    def save(Certificado certificado) {
        certificadoService.save(certificado, session, params)
        redirect session["usuarioActual"]
    }

}
