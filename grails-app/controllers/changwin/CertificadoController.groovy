package changwin

class CertificadoController {
    CertificadoService certificadoService

    static scaffold = Certificado

    def save(Certificado certificado) {
        certificadoService.save(certificado, servletContext, params)
        redirect servletContext["usuarioActual"]
    }

}
