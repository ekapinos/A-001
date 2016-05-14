package local.kapinos;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import local.kapinos.config.RawWebSocketConfig;
import local.kapinos.config.RootConfig;
import local.kapinos.config.SockJsConfig;
import local.kapinos.config.StompConfig;

public class WebXML extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { //JspServletConfig.class,
			                    RawWebSocketConfig.class,
			                    SockJsConfig.class,
			                    StompConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/spring/*" };
	}
}
