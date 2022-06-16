package changwin

class Necesitado {
    private String nombre
    List problemas = []

    static constraints = {
        nombre ([blank:false, nullable:false])
    }

    def cambiarNombre(String nombre) {
        this.nombre = nombre
    }

    def obtenerNombre() {
        return this.nombre
    }

    def obtenerProblemas() {
        return this.problemas.clone()
    }

    def crearProblema(String descripcion,
                    String rubro,
                    String ubicacion,
                    List multimedia = [],
                    Boolean emergencia = false) {
        problemas << new Problema(descripcion:descripcion, rubro:rubro,
                                necesitado:this, emergencia:emergencia, ubicacion:ubicacion,
                                multimedia:multimedia)
    }

    def eliminarProblema(Problema problema) {
        if (this.problemas.contains(problema)) {
            this.problemas.removeElement(problema)
        }
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
    
    def iniciarChat(Cotizacion cotizacion) {
        cotizacion.getExperto().iniciarChat(cotizacion.getProblema());
    }
    
    def chatear(Cotizacion cotizacion, String mensaje) {
        cotizacion.getProblema().chatear(cotizacion.getExperto().getNombre(),
                                         this.nombre, mensaje)
    }
    
    def seeChat(Cotizacion cotizacion) {
        cotizacion.getProblema().seeChat(cotizacion.getExperto().getNombre())
    }
    
}
