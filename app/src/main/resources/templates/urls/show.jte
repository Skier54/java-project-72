@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@param UrlPage page

@template.index(
    page = page,
    content = @`
        <div class="mb-3">
            <h1>${page.getUrl().getName()}</h1>
        </div>
    `,
)