package changwin

class RedirectController {
    def redirectFrontPage() {
        if (session["usuarioActual"] == null) {
            redirect uri:'/frontPage'
        } else if (session["usuarioActual"] instanceof changwin.Necesitado) {
            redirect Necesitado.get(session["usuarioActual"].id)
        } else {
            redirect Experto.get(session["usuarioActual"].id)
        }
    }
}