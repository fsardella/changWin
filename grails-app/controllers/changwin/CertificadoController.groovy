package changwin

import grails.gorm.transactions.Transactional

@Transactional
class CertificadoController {
    CertificadoService certificadoService

    static scaffold = Certificado

    def save(Certificado certificado) {
        try {
            certificadoService.save(certificado, session, params)
            redirect session["usuarioActual"]
        } catch (Exception e) {
            respond certificado.errors, view:'create'
        }
    }

}
