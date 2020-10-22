package com.ishumei.spring.boot;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;


@Configuration
@AutoConfigureAfter(WebMvcAutoConfiguration.class)
@EnableConfigurationProperties(PoiProperties.class)
public class PoiAutoConfiguration {
	
	private static final Logger logger = LoggerFactory.getLogger(PoiAutoConfiguration.class);

	private final ApplicationContext applicationContext;

	private final PoiProperties properties;

	public PoiAutoConfiguration(ApplicationContext applicationContext, PoiProperties properties) {
		this.applicationContext = applicationContext;
		this.properties = properties;
	}

/*	@PostConstruct
	public void checkTemplateLocationExists() {
		if (this.properties.isCheckTemplateLocation()) {
			TemplateLocation templatePathLocation = null;
			List<TemplateLocation> locations = new ArrayList<TemplateLocation>();
			for (String templateLoaderPath : this.properties.getTemplateLoaderPath()) {
				TemplateLocation location = new TemplateLocation(templateLoaderPath);
				locations.add(location);
				if (location.exists(this.applicationContext)) {
					templatePathLocation = location;
					break;
				}
			}
			if (templatePathLocation == null) {
				logger.warn("Cannot find template location(s): " + locations
						+ " (please add some templates, "
						+ "check your Beetl configuration, or set "
						+ "spring.Beetl.checkTemplateLocation=false)");
			}
		}
	}
*/
	protected static class BeetlConfiguration {

		@Autowired
		protected PoiProperties properties;

		protected void applyProperties(BeetlConfiguration factory) {
			/*factory.setTemplateLoaderPaths(this.properties.getTemplateLoaderPath());
			factory.setPreferFileSystemAccess(this.properties.isPreferFileSystemAccess());
			factory.setDefaultEncoding(this.properties.getCharsetName());*/
			Properties settings = new Properties();
			settings.putAll(this.properties.getSettings());
			//factory.setFreemarkerSettings(settings);
		}

	}
	  
	
}
