<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'cotizacion.label', default: 'Cotizacion')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="grails-logo-container-v2"></div>
    <asset:image src="cotizacion.png" class="imagen-contenida"/>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-cotizacion" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="show-cotizacion" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="{0}" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:display bean="cotizacion" />
                    <g:if test="${session.usuarioActual != null && session.usuarioActual instanceof changwin.Necesitado && this.cotizacion.estado == changwin.EstadoCotizacion.EN_ESPERA}">
                        <fieldset class="buttons">
                            <button onclick="location.href='../../cotizacion/aceptarCotizacion/${this.cotizacion.id}';">Aceptar cotización</button>
                        </fieldset>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
