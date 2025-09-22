package gg.jte.generated.ondemand.urls;
import hexlet.code.model.Url;
import hexlet.code.dto.urls.UrlsPage;
import hexlet.code.util.NamedRoutes;
import java.time.format.DateTimeFormatter;
@SuppressWarnings("unchecked")
public final class JtebuildGenerated {
	public static final String JTE_NAME = "urls/build.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,5,7,7,10,10,23,23,26,26,26,29,29,29,29,29,29,29,29,29,29,29,29,32,32,33,33,33,34,34,37,37,38,38,38,39,39,42,42,46,46,46,46,46,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlsPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <div class=\"container-lg mt-5\">\r\n            <h1 class=\"mb-4\">Сайты</h1>\r\n            <table class=\"table table-bordered table-hover mt-3 w-100\">\r\n                <thead style=\"border-bottom: 1px solid #dee2e6 !important;\">\r\n                    <tr>\r\n                        <th scope=\"col\" class=\"col-1\">ID</th>\r\n                        <th scope=\"col\" class=\"col-3\">Имя</th>\r\n                        <th scope=\"col\" class=\"col-2\">Последняя проверка</th>\r\n                        <th scope=\"col\" class=\"col-1\">Код ответа</th>\r\n                    </tr>\r\n                </thead>\r\n                <tbody style=\"border-top: 1px solid #dee2e6 !important;\">\r\n                    ");
				for (var url : page.getUrls()) {
					jteOutput.writeContent("\r\n                        <tr>\r\n                            <td>\r\n                                ");
					jteOutput.setContext("td", null);
					jteOutput.writeUserContent(url.getId());
					jteOutput.writeContent("\r\n                            </td>\r\n                            <td>\r\n                                <a");
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
					jteOutput.writeContent("</a>\r\n                            </td>\r\n                            <td>\r\n                                ");
					if (page.getLastChecks().get(url.getId()) != null) {
						jteOutput.writeContent("\r\n                                    ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getLastChecks().get(url.getId()).getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
						jteOutput.writeContent("\r\n                                ");
					}
					jteOutput.writeContent("\r\n                            </td>\r\n                            <td>\r\n                                ");
					if (page.getLastChecks().get(url.getId()) != null) {
						jteOutput.writeContent("\r\n                                    ");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(page.getLastChecks().get(url.getId()).getStatusCode());
						jteOutput.writeContent("\r\n                                ");
					}
					jteOutput.writeContent("\r\n                            </td>\r\n                        </tr>\r\n                    ");
				}
				jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n        </div>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlsPage page = (UrlsPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
