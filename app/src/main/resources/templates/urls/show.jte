@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.index(
    page = page,
    content = @`
        <div class="mb-3">
            <h1>${page.getUrl().getName()}</h1>
        </div>
        <div>
            <h2>Проверки</h2>
        </div>
        <div>
            <form action="${NamedRoutes.urlChecksPath(page.getUrl().getId())}" method="post">
                <input type="submit" class="btn btn-primary" value="Запустить проверку">
            </form>
        </div>
    `,
)