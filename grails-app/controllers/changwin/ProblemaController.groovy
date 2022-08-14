package changwin

class ProblemaController {

    ProblemaService problemaService

    static scaffold = Problema

    def save(Problema problema) {
        problemaService.save(problema, servletContext)
        redirect servletContext["usuarioActual"]
    }
}
