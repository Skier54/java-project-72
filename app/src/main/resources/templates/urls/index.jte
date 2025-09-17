@import hexlet.code.dto.urls.BuildPage
@import hexlet.code.util.NamedRoutes

@param BuildPage page

@template.index(
    page = page,
    content = @`
        <section class="flex-grow-6 container-fluid bg-dark p-5">
            <div class="col-md-10 col-lg-8 mx-auto text-white">
                <form action="${NamedRoutes.urlsPath()}" method="post">
                    <h1 class="display-3 mb-0">Анализатор страниц</h1>
                    <p class="lead">Проверка сайта на SEO пригодность</p>
                    <div class="row">
                        <label class="col-10">
                            <input type="text" name="url" class="form-control-lg w-100 form-floating" value="${page.getName()}" placeholder="ссылка">
                        </label>
                        <input type="submit" class="col-2 btn btn-primary btn-lg" value="сохранить">
                        <p class="mt-2 mb-0 text-secondary">Пример: https://www.example.com</p>
                    </div>
                </form>
            </div>
        </section>
    `
)