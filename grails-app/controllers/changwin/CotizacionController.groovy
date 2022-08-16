package changwin

class CotizacionController {
    CotizacionService cotizacionService

    static scaffold = Cotizacion

    def create() {
        println Problema.get(params.id)
        servletContext["problemaForLastCreatedCotizacion"] = Problema.get(params.id)
        respond new Cotizacion(params)
    }
    
    def save(Cotizacion cotizacion) {
        cotizacionService.save(cotizacion, servletContext)
        redirect servletContext["usuarioActual"]
    }
    
}
