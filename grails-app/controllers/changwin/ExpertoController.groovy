package changwin

class ExpertoController {
    ExpertoService expertoService

    static scaffold = Experto

    def save(Experto experto) {
        expertoService.save(experto, servletContext)
        redirect experto
    }

    def login() {
        respond new Experto()
    }

    def loginConfirmed(Experto experto) {
        if (servletContext["usuarioActual"]) {return}
        try {
            redirect expertoService.logExperto(experto, servletContext)
        } catch (Exception e) {
            respond experto.errors, view:'login'
        }
    }

    def logout() {
        servletContext.removeAttribute("usuarioActual")
        redirect uri:"/"
    }

    def mostrarCotizaciones() {
        Set<Cotizacion> response = expertoService.obtenerCotizacionesDeExperto(params.id.toInteger())
        respond (response, model:[cotizacionesDeExperto: response, cantCotizacionesUsuario: response.size(), maxCotizacionesPerPage: 25])
    }
}
