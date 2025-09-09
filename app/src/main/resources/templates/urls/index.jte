@import hexlet.code.dto.urls.BuildPage
@import hexlet.code.util.NamedRoutes
@param BuildPage page

@template.index(
    page = page,
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <form action="${NamedRoutes.urlsPath()}" method="post">
                <h1>Анализатор страниц</h1>
                <p>Проверка сайта на SEO пригодность</p>
                <div class="mb-3">
                    <label class="form-label">
                        <input type="url" name="url" value="${page.getName()}" placeholder="ссылка">
                    </label>
                    <input type="submit" class="btn btn-primary" value="сохранить">
                    <p>Пример: https://www.example.com</p>
                </div>
            </form>
        </div>
    `
)