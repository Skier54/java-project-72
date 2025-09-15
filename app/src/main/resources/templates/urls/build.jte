@import hexlet.code.model.Url
@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes

@param UrlsPage page

@template.index(
    page = page,
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                <h1>Сайты</h1>
                <table class="table table-bordered table-hover mt-3 w-75">
                    <thead style="border-bottom: 1px solid #dee2e6 !important;">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Имя</th>
                            <th scope="col">Последняя проверка</th>
                            <th scope="col">Код ответа</th>
                        </tr>
                    </thead>
                    <tbody style="border-top: 1px solid #dee2e6 !important;">
                        @for(var url : page.getUrls())
                            <tr>
                                <td>
                                    ${url.getId()}
                                </td>
                                <td>
                                    <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                                </td>
                                <td>
                                    @if (page.getLastChecks().get(url.getId()) != null)
                                        ${page.getLastChecks().get(url.getId()).getCreatedAt().toString().substring(0, 16)}
                                    @endif
                                </td>
                                <td>
                                    @if (page.getLastChecks().get(url.getId()) != null)
                                        ${page.getLastChecks().get(url.getId()).getStatusCode()}
                                    @endif
                                </td>
                            </tr>
                        @endfor
                    </tbody>
                </table>
            </main>
        </div>
    `
)