<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Chang-Win</title>
    <g:if test="${application.usuarioActual != null}">
        <g:if test="${application.usuarioActual instanceof changwin.Necesitado}">
            <meta http-equiv="Refresh" content="0; url='http://localhost:8080/necesitado/show/${application.usuarioActual.id}'" />
        </g:if>
        <g:else>
            <meta http-equiv="Refresh" content="0; url='http://localhost:8080/experto/show/${application.usuarioActual.id}'" />
        </g:else>
    </g:if>
</head>
<body>

<div class="svg" role="presentation">
    <div class="grails-logo-container">
        <asset:image src="changWin.png" class="grails-logo"/>
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
</div>

<div class="role-selector">
    <button type="button" style="background:#aaaaaa"
    onclick="location.href='necesitado/create';">Registrarse como necesitado</button>
    <button type="button" style="background:#880016"
    onclick="location.href='experto/create';">Registrarse como experto</button>
</div>
<div class="role-selector">
    <button type="button" style="background:#7f7f7f"
    onclick="location.href='necesitado/login';">Loguearse como necesitado</button>
    <button type="button" style="background:#54000d"
     onclick="location.href='experto/login';">Loguearse como experto</button>
</div>

</body>
</html>
