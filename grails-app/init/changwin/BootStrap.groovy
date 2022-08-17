package changwin

import java.time.LocalDateTime

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
        Problema problema = cristo.crearProblema("AAAAAAYUUUUDAAAA",
                                                plomeria,
                                                "Tu vieja",
                                                BarrioProblema.PALERMO,
                                                [],
                                                EstadoEmergencia.REGULAR)
        problema.save()
        Experto flor = new Experto(nombre: "Flor", mail:"asdf@gmail.com", contrasenia: "123")
        Certificado certificado = new Certificado(rubro: plomeria, numeroMatricula: 123546,
                                      fechaEmision: LocalDateTime.of(2022, 7, 29, 19, 30, 40),
                                      fechaVencimiento: LocalDateTime.of(2030, 7, 29, 19, 30, 40),
                                      experto: flor)
        flor.agregarRubro(certificado, enteCertificador)
        flor.save()
        certificado.save()
        servletContext["usuarioActual"] = flor
    }
    def destroy = {
    }
}
