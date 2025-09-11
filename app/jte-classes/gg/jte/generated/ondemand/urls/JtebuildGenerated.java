package gg.jte.generated.ondemand.urls;
import hexlet.code.model.Url;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.util.NamedRoutes;
@SuppressWarnings("unchecked")
public final class JtebuildGenerated {
	public static final String JTE_NAME = "urls/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,4,4,4,4,6,6,9,9,14,14,17,17,17,20,20,20,20,20,20,20,20,20,20,20,20,23,23,27,27,27,27,27,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.JteindexGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <div class=\"mx-auto p-4 py-md-5\">\r\n            <main>\r\n                <h1>Все сайты</h1>\r\n                 <table class=\"table table-striped\">\r\n                     ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\r\n                         <tr>\r\n                             <td>\r\n                                 ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\r\n                             </td>\r\n                             <td>\r\n                                 <a");
					var __jte_html_attribute_0 = NamedRoutes.urlPath(url.getId());
					if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
						jteOutput.writeContent(" href=\"");
						jteOutput.setContext("a", "href");
						jteOutput.writeUserContent(__jte_html_attribute_0);
						jteOutput.setContext("a", null);
						jteOutput.writeContent("\"");
					}
					jteOutput.writeContent(">");
					jteOutput.setContext("a", null);
					jteOutput.writeUserContent(url.getName());
					jteOutput.writeContent("</a>\r\n                             </td>\r\n                         </tr>\r\n                     ");
				}
				jteOutput.writeContent("\r\n                 </table>\r\n             </main>\r\n         </div>\r\n     ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
