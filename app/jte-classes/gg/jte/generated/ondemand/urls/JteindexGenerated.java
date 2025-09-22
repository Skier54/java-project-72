package gg.jte.generated.ondemand.urls;
import hexlet.code.dto.urls.BuildPage;
import hexlet.code.util.NamedRoutes;
@SuppressWarnings("unchecked")
public final class JteindexGenerated {
	public static final String JTE_NAME = "urls/index.jte";
	public static final int[] JTE_LINE_INFO = {0,0,1,3,3,3,3,5,5,8,8,11,11,11,11,11,11,11,11,11,16,16,16,16,16,16,16,16,16,24,24,24,24,24,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, BuildPage page) {
		jteOutput.writeContent("\r\n");
		gg.jte.generated.ondemand.layout.JtepageGenerated.render(jteOutput, jteHtmlInterceptor, page, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\r\n        <section class=\"flex-grow-6 container-fluid bg-dark p-5\">\r\n            <div class=\"col-md-10 col-lg-8 mx-auto text-white\">\r\n                <form");
				var __jte_html_attribute_0 = NamedRoutes.urlsPath();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_0)) {
					jteOutput.writeContent(" action=\"");
					jteOutput.setContext("form", "action");
					jteOutput.writeUserContent(__jte_html_attribute_0);
					jteOutput.setContext("form", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" method=\"post\">\r\n                    <h1 class=\"display-3 mb-0\">Анализатор страниц</h1>\r\n                    <p class=\"lead\">Проверка сайта на SEO пригодность</p>\r\n                    <div class=\"row\">\r\n                        <label class=\"col-10\">\r\n                            <input type=\"text\" name=\"url\" class=\"form-control-lg w-100 form-floating\"");
				var __jte_html_attribute_1 = page.getName();
				if (gg.jte.runtime.TemplateUtils.isAttributeRendered(__jte_html_attribute_1)) {
					jteOutput.writeContent(" value=\"");
					jteOutput.setContext("input", "value");
					jteOutput.writeUserContent(__jte_html_attribute_1);
					jteOutput.setContext("input", null);
					jteOutput.writeContent("\"");
				}
				jteOutput.writeContent(" placeholder=\"ссылка\">\r\n                        </label>\r\n                        <input type=\"submit\" class=\"col-2 btn btn-primary btn-lg\" value=\"сохранить\">\r\n                        <p class=\"mt-2 mb-0 text-secondary\">Пример: https://www.example.com</p>\r\n                    </div>\r\n                </form>\r\n            </div>\r\n        </section>\r\n    ");
			}
		});
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		BuildPage page = (BuildPage)params.get("page");
		render(jteOutput, jteHtmlInterceptor, page);
	}
}
