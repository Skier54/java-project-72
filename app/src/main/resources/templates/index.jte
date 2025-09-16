@import gg.jte.Content
@import hexlet.code.util.NamedRoutes
@import hexlet.code.dto.BasePage

@param BasePage page = null
@param Content content

<!DOCTYPE html>
<html lang="ru">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Главная страница</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
    </head>
    <body class="d-flex flex-column min-vh-100">
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/">
                    <h4 class="text-decoration-none text-white">Анализатор страниц</h4>
                </a>
                <div class="collapse navbar-collapse">
                    <div class="navbar-nav">
                        <a class="nav-link text-gray" href="${NamedRoutes.rootPath()}">Главная</a>
                        <a class="nav-link text-gray" href="${NamedRoutes.urlsPath()}" style="margin-left: 20px;">Сайты</a>
                    </div>
                </div>
            </div>
        </nav>
        @if(page != null && page.getFlash() != null)
            <div>
                <p>${page.getFlash()}</p>
            </div>
        @endif
        <div>
            ${content}
        </div>
    </body>
</html>