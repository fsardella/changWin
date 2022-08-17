package changwin

class BootStrap {

    def init = { servletContext ->
        Rubro plomeria = new Rubro(nombre: "Plomeria")
        Rubro electricidad = new Rubro(nombre: "Electricidad")
        Rubro pintor = new Rubro(nombre: "Pintor")
        plomeria.save()
        electricidad.save()
        pintor.save()
        EnteCertificador enteCertificador = new EnteCertificador()
        enteCertificador.save()
        servletContext["enteCertificador"] = enteCertificador
        Necesitado cristo = new Necesitado(nombre: "Cristo", mail:"asdf@gmail.com", contrasenia: "123",
                                          metodoDePago: MetodoDePago.EFECTIVO)
        cristo.save()
        servletContext["usuarioActual"] = cristo
    }
    def destroy = {
    }
}
