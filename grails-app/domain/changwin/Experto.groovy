package changwin

class Experto {
    String nombre
    List rubros = []
    List problemasInteractuados = []

    static constraints = {
        nombre ([blank:false, nullable:false])
    }

    def getNombre() {
        return this.nombre
    }

    def getRubros() {
        return this.rubros.clone()
    }

    def getProblemasInteractuados() {
        return this.problemasInteractuados.clone()
    }

    def agregarRubro(String nuevoRubro) {
        this.rubros << nuevoRubro
    }

    def eliminarRubro(String rubroAEliminar) {
        if (this.rubros.contains(rubroAEliminar)) {
            this.rubros.removeElement(rubroAEliminar)
        }
    }

    def cotizarProblema(Problema problema, BigDecimal costo) {
        if (!this.rubros.contains(problema.getRubro())) {
            throw new Exception("Acceso con rubro ilegal")
        }
        Cotizacion cotizacion = new Cotizacion(costo:costo, experto:this)
        problema.cotizar(cotizacion)
        problemasInteractuados << problema
    }
}
