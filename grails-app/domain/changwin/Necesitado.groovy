package changwin

import java.time.LocalDateTime

public enum MetodoDePago {
    EFECTIVO,
    CREDITO,
    DEBITO,
    TRANSFERENCIA
}

class Necesitado extends Usuario {
    MetodoDePago metodoDePago
    List<Problema> problemas = []

    static constraints = {
        nombre blank: false, nullable: false
    }

    def obtenerProblemas() {
        return this.problemas.clone()
    }

    Problema crearProblema(String descripcion,
                    Rubro rubro,
                    String ubicacion,
                    BarrioProblema barrio,
                    List multimedia = [],
                    Boolean emergencia = false) {
        Problema problema = new Problema(descripcion:descripcion, rubro:rubro,
                                necesitado:this, emergencia:emergencia, ubicacion:ubicacion,
                                barrio:barrio, multimedia:multimedia)
        problemas << problema
        return problema
    }

    def eliminarProblema(Problema problema) {
        if (!this.problemas.contains(problema)) {
            throw new Exception("El problema no se encuentra")
        }
        if (problema.estaConfirmado()) {
            throw new Exception("No se puede eliminar un problema confirmado")
        }
        this.problemas.removeElement(problema)
        problema.eliminar()
    }

    def aceptarCotizacion(Problema problema, Cotizacion cotizacion, LocalDateTime horaDeReunion) {
        if (!this.problemas.contains(problema)) {
            throw new Exception("El problema no se encuentra")
        }
        problema.aceptarCotizacion(cotizacion, horaDeReunion)
    }

    def cambiarDescripcionProblema(Problema problema, String descripcionNueva) {
        if (!this.problemas.contains(problema)) {
            throw new Exception("Acceso ilegal a problema")
        }
        problema.cambiarDescripcion(descripcionNueva)
    }

    def agregarImagenProblema(Problema problema, String imagen) {
        if (!this.problemas.contains(problema)) {
            throw new Exception("Acceso ilegal a problema")
        }
        problema.agregarMultimedia(imagen)
    }

    def calificar(Problema problema, Integer calificacion) {
        if (!this.problemas.contains(problema)) {
            throw new Exception("Acceso ilegal a problema")
        }
        problema.calificar(calificacion)
    }
}
