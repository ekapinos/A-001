package local.kapinos;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import local.kapinos.config.RootConfig;
import local.kapinos.config.ServletConfig;
import local.kapinos.config.ServletWebSocketConfig;

public class WebXML extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { ServletConfig.class, ServletWebSocketConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}
}
