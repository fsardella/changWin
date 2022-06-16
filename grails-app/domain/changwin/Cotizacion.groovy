package changwin

class Cotizacion {
    private BigDecimal costo
    private Experto experto
    private Problema problema = null

    static constraints = {
        costo ([blank:false, nullable:false])
        experto ([blank:false, nullable:false])   
    }

    def getCosto() {
        return costo
    }

    def getExperto() {
        return this.experto
    }
    
    def agregarProblema(Problema newProblema) {
        this.problema = newProblema
    }
    
    def getProblema() {
        return this.problema
    }
}

