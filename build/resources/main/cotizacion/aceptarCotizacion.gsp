<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'certificado.label', default: 'Certificado')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <%@ page import="java.time.LocalDateTime" %>
    <div id="content" role="main">
        <div class="container">
            <section class="row">
                <a href="#create-certificado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
                <div class="nav" role="navigation">
                    <ul>
                        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                    </ul>
                </div>
            </section>
            <section class="row">
                <div id="aceptar-cotizacion" class="col-12 content scaffold-create" role="main">
                    <h1><g:message code="Aceptar {0}" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.cotizacion}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${this.cotizacion}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                    </g:hasErrors>
                    <g:form resource="${this.cotizacion}" method="POST">
                        <fieldset class="form">
                            <div class="fecha-field">
                                <br/>
                                <l1>Fecha de Reunión</l1>
                                <g:datePicker name="fechaReunion" value="${LocalDateTime.now()}"/>
                            </div>
                        </fieldset>
                        <fieldset class="buttons">
                            <g:submitButton name="aceptar" class="aceptar" value="aceptar" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
