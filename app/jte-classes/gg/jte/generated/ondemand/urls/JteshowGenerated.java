package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.UrlPage;
import hexlet.code.util.NamedRoutes;
import hexlet.code.model.UrlCheck;
import java.time.format.DateTimeFormatter;
@SuppressWarnings("unchecked")
public final class JteshowGenerated {
	public static final String JTE_NAME = "urls/show.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,2,3,5,5,5,5,7,7,10,10,12,12,12,17,17,17,21,21,21,25,25,25,33,33,33,33,33,33,33,33,33,49,49,50,50,52,52,52,53,53,53,54,54,54,55,55,55,56,56,56,57,57,57,59,59,60,60,64,64,64,64,64,5,5,5,5};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, UrlPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <div class=\"container-lg mt-5\">\r\n            <h1>Сайт: ");
				jteOutput.setContext("h1", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</h1>\r\n            <table class=\"table table-bordered table-hover mt-3 w-100\">\r\n                <tbody style=\"border-top: 1px solid #dee2e6 !important;\">\r\n                    <tr>\r\n                        <td scope=\"col\">ID</td>\r\n                        <td class=\"col\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getId());
				jteOutput.writeContent("</td>\r\n                    </tr>\r\n                    <tr>\r\n                        <td scope=\"col\">Имя</td>\r\n                        <td class=\"col\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getName());
				jteOutput.writeContent("</td>\r\n                    </tr>\r\n                    <tr>\r\n                        <td scope=\"col\">Дата создания</td>\r\n                        <td class=\"col\">");
				jteOutput.setContext("td", null);
				jteOutput.writeUserContent(page.getUrl().getCreatedAt().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
				jteOutput.writeContent("</td>\r\n                    </tr>\r\n                </tbody>\r\n            </table>\r\n            <div>\r\n                <h2 class=\"mt-5\">Проверки</h2>\r\n            </div>\r\n            <div>\r\n                <form");
				var __jte_html_attribute_0 = NamedRoutes.urlChecksPath(page.getUrl().getId());
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n                    <input type=\"submit\" class=\"btn btn-primary\" value=\"Запустить проверку\">\r\n                </form>\r\n            </div>\r\n            <table class=\"table table-bordered table-hover mt-3 w-100\">\r\n                <thead style=\"border-bottom: 1px solid #dee2e6 !important;\">\r\n                    <tr>\r\n                        <th scope=\"col\" class=\"col-1\">ID</th>\r\n                        <th scope=\"col\" class=\"col-1\">Код ответа</th>\r\n                        <th scope=\"col\" class=\"col-3\">title</th>\r\n                        <th scope=\"col\" class=\"col-3\">h1</th>\r\n                        <th scope=\"col\" class=\"col-2\">description</th>\r\n                        <th scope=\"col\" class=\"col-2\">Дата проверки</th>\r\n                    </tr>\r\n                </thead>\r\n                <tbody style=\"border-top: 1px solid #dee2e6 !important;\">\r\n                    ");
				if (page.getUrl() != null && page.getUrl().getUrlCheck() != null) {
					jteOutput.writeContent("\r\n                        ");
					for (var urlCheck : page.getUrl().getUrlCheck()) {
						jteOutput.writeContent("\r\n                            <tr>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getId());
						jteOutput.writeContent("</td>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getStatusCode());
						jteOutput.writeContent("</td>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getTitle());
						jteOutput.writeContent("</td>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getH1());
						jteOutput.writeContent("</td>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getDescription());
						jteOutput.writeContent("</td>\r\n                                <td>");
						jteOutput.setContext("td", null);
						jteOutput.writeUserContent(urlCheck.getCreatedAt().toString().substring(0, 16));
						jteOutput.writeContent("</td>\r\n                            </tr>\r\n                        ");
					}
					jteOutput.writeContent("\r\n                    ");
				}
				jteOutput.writeContent("\r\n                </tbody>\r\n            </table>\r\n        </div>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		UrlPage page = (UrlPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
