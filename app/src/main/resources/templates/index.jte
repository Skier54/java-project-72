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
    <body>
        <div class="mx-auto p-4 py-md-5">
            <main>
                <div style="display: flex; align-items: center;">
                    <h4><a href="/" style="text-decoration: none;">Анализатор страниц</a></h4>
                    <div style="display: inline-flex; margin-left: 20px;">
                        <a class="nav-link" href="${NamedRoutes.rootPath()}">Главная</a>
                        <a class="nav-link" href="${NamedRoutes.urlsPath()}" style="margin-left: 20px;">Сайты</a>
                    </div>
                </div>
            </main>
        </div>
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