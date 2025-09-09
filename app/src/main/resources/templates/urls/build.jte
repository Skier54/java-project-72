@import hexlet.code.model.Url
@import hexlet.code.dto.urls.UrlsPage
@import hexlet.code.util.NamedRoutes

@param UrlsPage page

@template.index(
    page = page,
    content = @`
        <div class="mx-auto p-4 py-md-5">
            <main>
                <h1>Все сайты</h1>
                 <table class="table table-striped">
                     @for(var url : page.getUrls())
                         <tr>
                             <td>
                                 ${url.getId()}
                             </td>
                             <td>
                                 <a href="${NamedRoutes.urlPath(url.getId())}">${url.getName()}</a>
                             </td>
                         </tr>
                     @endfor
                 </table>
             </main>
         </div>
     `
 )