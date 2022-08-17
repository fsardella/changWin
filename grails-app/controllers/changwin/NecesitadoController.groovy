package changwin

import grails.gorm.transactions.Transactional

@Transactional
class NecesitadoController {

    NecesitadoService necesitadoService

    static scaffold = Necesitado

    def save(Necesitado necesitado) {
        necesitadoService.save(necesitado, servletContext)
        redirect necesitado
    }

    def mostrarProblemasDeNecesitado() {
        Set<Problema> response = necesitadoService.obtenerProblemasDeNecesitado(params.id.toInteger())
        respond (response, model:[problemasDeUsuario: response, cantProblemasUsuario: response.size(), maxProblemasPerPage: 25])
    }

    def login() {
        respond new Necesitado()
    }

    def loginConfirmed(Necesitado necesitado) {
        if (servletContext["usuarioActual"]) {return}
        try {
            redirect necesitadoService.logNecesitado(necesitado, servletContext)
        } catch (Exception e) {
            respond necesitado.errors, view:'login'
        }
        
    }

    def logout() {
        servletContext.removeAttribute("usuarioActual")
        redirect uri:"/"
    }

}   
