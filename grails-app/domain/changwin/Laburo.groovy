package changwin
import java.time.LocalDate

class Laburo {
    private LocalDate horaDeReunion
    private Integer calificacion
    private Experto experto

    static constraints = {
        horaDeReunion ([blank:false, nullable:false])
    }

    def getHoraDeReunion() {
        return this.horaDeReunion
    }

    def getCalificacion() {
        return this.calificacion
    }
    
    def getExperto() {
        return this.experto
    }

    def calificar(Integer calificacion) {
        this.calificacion = calificacion
    }
}
