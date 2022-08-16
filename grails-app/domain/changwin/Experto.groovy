package changwin

class Experto extends Usuario {
    Set<Certificado> certificados = []
    Set<Cotizacion> cotizaciones = []

    static constraints = {
        nombre blank: false, nullable: false
    }

    static hasMany = [
        cotizaciones: Cotizacion,
        certificados: Certificado
    ]

    def agregarRubro(Certificado certificado, EnteCertificador enteCertificador) {
        this.certificados << certificado
        enteCertificador.validarCertificado(certificado)
    }

    def actualizarCertificado(Certificado certificado) {
        Set<Certificado> eliminables = this.certificados.findAll{cert -> cert.rubro == certificado.rubro
                                                             && cert != certificado}
        this.certificados.removeAll{cert -> eliminables.contains(cert)}
    }

    def eliminarRubro(Certificado certificado) {
        if (!this.certificados.contains(certificado)) {
            return
        }
        this.certificados.removeElement(certificado)

        Set<Cotizacion> eliminables = this.cotizaciones.findAll{cot -> cot.getRubro() == certificado.rubro
                                                            && !cot.estaConfirmada()}
        eliminables.forEach{elim -> this.eliminarCotizacion(elim)}
    }

    def eliminarCotizacion(Cotizacion cotizacion) {
        if (!this.cotizaciones.contains(cotizacion)) {
            return
        }
        this.cotizaciones.removeElement(cotizacion)
        cotizacion.eliminar()
    }

    Cotizacion cotizarProblema(Problema problema, BigDecimal costo) {
        if (!this.certificados.any{cert -> problema.rubro == cert.rubro && cert.esValido()}) {
            throw new Exception("Acceso con rubro ilegal")
        }
        if (problema.estaConfirmado()) {
            throw new Exception("El problema ya fue confirmado")
        }
        Cotizacion cotizacion = new Cotizacion(costo: new Dinero(monto:costo), experto: this, problema: problema)
        problema.agregarCotizacion(cotizacion)
        cotizaciones << cotizacion
        cotizacion
    }
}
