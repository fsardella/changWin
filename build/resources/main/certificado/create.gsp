<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'certificado.label', default: 'Certificado')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
    <div class="grails-logo-container-v2"></div>
    <asset:image src="certificado_new.png" class="imagen-contenida"/>
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
                <div id="create-certificado" class="col-12 content scaffold-create" role="main">
                    <h1><g:message code="Agregar {0}" args="[entityName]" /></h1>
                    <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                    </g:if>
                    <g:hasErrors bean="${this.certificado}">
                    <ul class="errors" role="alert">
                        <g:eachError bean="${this.certificado}" var="error">
                        <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                        </g:eachError>
                    </ul>
                    </g:hasErrors>
                    <g:form resource="${this.certificado}" method="POST">
                        <fieldset class="form">
                            <f:with bean="certificado">
                                <f:field property="numeroMatricula"/>
                                <f:field property="rubro"/>
                            </f:with>
                            <div class="fecha-field">
                                <br/>
                                <l1>Fecha de emisión</l1>
                                <g:datePicker name="fechaEmision" value="${LocalDateTime.now()}"/>
                            </div>
                            <div class="fecha-field">
                                <br/>
                                <l1>Fecha de vencimiento</l1>
                                <g:datePicker name="fechaVencimiento"  value="${LocalDateTime.now()}"/>
                            </div>
                        </fieldset>
                        <fieldset class="buttons">
                            <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                        </fieldset>
                    </g:form>
                </div>
            </section>
        </div>
    </div>
    </body>
</html>
