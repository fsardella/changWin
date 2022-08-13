<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
</head>
<body>
<content tag="nav">
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Application Status <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Environment: ${grails.util.Environment.current.name}</a></li>
            <li class="dropdown-item"><a href="#">App profile: ${grailsApplication.config.grails?.profile}</a></li>
            <li class="dropdown-item"><a href="#">App version:
                <g:meta name="info.app.version"/></a>
            </li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Grails version:
                <g:meta name="info.app.grailsVersion"/></a>
            </li>
            <li class="dropdown-item"><a href="#">Groovy version: ${GroovySystem.getVersion()}</a></li>
            <li class="dropdown-item"><a href="#">JVM version: ${System.getProperty('java.version')}</a></li>
            <li role="separator" class="dropdown-divider"></li>
            <li class="dropdown-item"><a href="#">Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Artefacts <span class="caret"></span></a>
        <ul class="dropdown-menu">
            <li class="dropdown-item"><a href="#">Controllers: ${grailsApplication.controllerClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Domains: ${grailsApplication.domainClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Services: ${grailsApplication.serviceClasses.size()}</a></li>
            <li class="dropdown-item"><a href="#">Tag Libraries: ${grailsApplication.tagLibClasses.size()}</a></li>
        </ul>
    </li>
    <li class="dropdown">
        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Installed Plugins <span class="caret"></span></a>
        <ul class="dropdown-menu dropdown-menu-right">
            <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
                <li class="dropdown-item"><a href="#">${plugin.name} - ${plugin.version}</a></li>
            </g:each>
        </ul>
    </li>
</content>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <%-- <asset:image src="plomero.jpeg" class="grails-logo"/> --%>
    </div>
</div>

<div id="content" role="main">
    <div class="container">
        <section class="row colset-2-its">
            <h1>Bienvenido/a a Chang-win</h1>

            <p>
                Conseguir un electricista, plomero o realmente cualquier experto de confianza es 
                un problema muy común y difícil de resolver en la vida del ser humano moderno, con 
                tanta escasez de tiempo. Más aún si se considera que debe estar disponible en las franjas 
                horarias donde podemos recibirlo; y que, aún teniendo certificaciones verificables, no 
                existe información centralizada sobre la eficiencia con la que realizará su labor.
            </p>
            <p>
                Chang-win es una alternativa al boca-boca al cual estamos acostumbrados para la resolución 
                de dichos “laburos” en diferentes rubros (como pueden ser la plomería, electricidad o limpieza). 
                Es un servicio que se encarga de llevar la eficiencia del mundo moderno a las problemáticas 
                típicas que pueden surgir por defectos cotidianos en busca de arreglo.

            </p>

        </section>
    </div>
            <%-- <div id="controllers" role="navigation"> --%>
                <%-- <h2>Available Controllers:</h2>
                <ul> --%>
                    <%-- <g:each var="c" in="${grailsApplication.controllerClasses.sort { it.fullName } }">
                        <li class="controller">
                            <g:link controller="${c.logicalPropertyName}">${c.fullName}</g:link>
                        </li>
                    </g:each> --%>
                    
                <%-- </ul> --%>
            <%-- </div> --%>
</div>

<div class="role-selector">
    <button type="button" style="background:#aaaaaa">Registrarse como necesitado</button>
    <button type="button" style="background:#880016">Registrarse como experto</button>
</div>
<div class="role-selector">
    <button type="button" style="background:#7f7f7f">Loguearse como necesitado</button>
    <button type="button" style="background:#54000d">Loguearse como experto</button>
</div>

</body>
</html>
