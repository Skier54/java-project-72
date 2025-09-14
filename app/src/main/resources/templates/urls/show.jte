@import hexlet.code.dto.urls.UrlPage
@import hexlet.code.util.NamedRoutes
@import hexlet.code.model.UrlCheck

@param UrlPage page

@template.index(
    page = page,
    content = @`
        <div class="mb-3">
            <h1>Сайт: ${page.getUrl().getName()}</h1>
        </div>
        <table class="table table-bordered table-hover mt-3 w-75">
            <tbody style="border-top: 1px solid #dee2e6 !important;">
                <tr>
                    <td scope="col">ID</td>
                    <td class="col">${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <td scope="col">Имя</td>
                    <td class="col">${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <td scope="col">Дата создания</td>
                    <td class="col">${page.getUrl().getCreatedAt().toString().substring(0, 16)}</td>
                </tr>
            </tbody>
        </table>
        <div>
            <h2>Проверки</h2>
        </div>
        <div>
            <form action="${NamedRoutes.urlChecksPath(page.getUrl().getId())}" method="post">
                <input type="submit" class="btn btn-primary" value="Запустить проверку">
            </form>
        </div>
        <table class="table table-bordered table-hover mt-3 w-75">
            <thead style="border-bottom: 1px solid #dee2e6 !important;">
                <tr>
                    <th scope="col">ID</th>
                    <th scope="col">Код ответа</th>
                    <th scope="col">title</th>
                    <th scope="col">h1</th>
                    <th scope="col">description</th>
                    <th scope="col">Дата проверки</th>
                </tr>
            </thead>
            <tbody style="border-top: 1px solid #dee2e6 !important;">
                @if(page.getUrl() != null && page.getUrl().getUrlCheck() != null)
                    @for(var urlCheck : page.getUrl().getUrlCheck())
                        <tr>
                            <td>${urlCheck.getId()}</td>
                            <td>${urlCheck.getStatusCode()}</td>
                            <td>${urlCheck.getTitle()}</td>
                            <td>${urlCheck.getH1()}</td>
                            <td>${urlCheck.getDescription()}</td>
                            <td>${urlCheck.getCreatedAt().toString().substring(0, 16)}</td>
                        </tr>
                    @endfor
                @endif
            </tbody>
        </table>
    `,
)