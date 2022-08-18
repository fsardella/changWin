<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'experto.label', default: 'Experto')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#show-experto" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
            </section>
            <section class="row">
                <div id="show-experto" class="col-12 content scaffold-show" role="main">
                    <h1><g:message code="Información personal"/></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <f:display bean="experto" except="contrasenia, cotizaciones, certificados"/>
                    <g:if test="${session.usuarioActual != null && params.id.toInteger() == session.usuarioActual.id}">
                        <fieldset class="buttons">
                            <button onclick="location.href='../../certificado/create';">Agregar rubro</button>
                            <button onclick="location.href='../../experto/mostrarProblemas/${this.experto.id}';">Buscar problemas</button>
                            <button onclick="location.href='../../experto/mostrarCotizaciones/${this.experto.id}';">Mostrar cotizaciones</button>
                            <button onclick="location.href='../../experto/mostrarCertificados/${this.experto.id}';">Mostrar certificados</button>
                            <button onclick="location.href='../logout';">Cerrar sesión</button>
                        </fieldset>
                    </g:if>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
