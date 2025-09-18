@import hexlet.code.model.Url
@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes

@param UrlsPage page

@template.layout.page(
    page = page,
    content = @`
        <div class="container-lg mt-5">
            <h1 class="mb-4">Сайты</h1>
            <table class="table table-bordered table-hover mt-3 w-100">
                <thead style="border-bottom: 1px solid #dee2e6 !important;">
                    <tr>
                        <th scope="col" class="col-1">ID</th>
                        <th scope="col" class="col-3">Имя</th>
                        <th scope="col" class="col-2">Последняя проверка</th>
                        <th scope="col" class="col-1">Код ответа</th>
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
        </div>
    `
)