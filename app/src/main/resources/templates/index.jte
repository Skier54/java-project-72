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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="mx-auto p-4 py-md-5">
            <main>
                <h1>Добро пожаловать на главную страницу</h1>
                <a href="/">Анализатор страниц</a>
                <ul>
                    <li>
                        <a class="nav-link" href="${NamedRoutes.rootPath()}">Анализатор страниц</a>
                    </li>
                    <li>
                        <a class="nav-link" href="${NamedRoutes.urlsPath()}">Сайты</a>
                    </li>
                </ul>
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