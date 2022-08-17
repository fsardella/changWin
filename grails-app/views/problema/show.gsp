<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'problema.label', default: 'Problema')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-problema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="show-problema" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="{0}" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:display bean="problema" />
                    <g:if test="${session.usuarioActual != null && session.usuarioActual instanceof changwin.Experto && !changwin.Problema.get(params.id).estaConfirmado()}">
                        <fieldset class="buttons">
                            <button onclick="location.href='../../cotizacion/create/${this.problema.id}';">Cotizar</button>
                        </fieldset>
                    </g:if>
                    <g:if test="${session.usuarioActual != null && session.usuarioActual instanceof changwin.Necesitado}">
                        <fieldset class="buttons">
                            <button onclick="location.href='../../problema/mostrarCotizacionesDeProblema/${this.problema.id}';">Mostrar cotizaciones</button>
                        </fieldset>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
