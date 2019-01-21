package org.acme.eshop;

import java.util.Arrays;
import java.util.Locale;

import org.acme.eshop.enumeration.SupportedLanguage;
import org.acme.eshop.exhibit.PrototypeBean;
import org.acme.eshop.exhibit.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
@EnableScheduling
@PropertySource({"classpath:my.properties"})
public class EshopApplication {
	public static void main(final String[] args) {
		final ConfigurableApplicationContext context = SpringApplication.run(EshopApplication.class, args);

		final boolean exhibitMode = Arrays.stream(context.getEnvironment().getActiveProfiles()).anyMatch(
			"exhibit"::equals);
		if (exhibitMode) {
			final SingletonBean singletonBean1 = context.getBean(SingletonBean.class);
			final SingletonBean singletonBean2 = context.getBean(SingletonBean.class);

			final PrototypeBean prototypeBean1 = context.getBean(PrototypeBean.class);
			final PrototypeBean prototypeBean2 = context.getBean(PrototypeBean.class);
		}
	}

	@Bean
	public LocaleResolver acceptHeaderLocaleResolver() {
		final AcceptHeaderLocaleResolver localeResolver = new AcceptHeaderLocaleResolver();
		localeResolver.setDefaultLocale(Locale.US);
		localeResolver.setSupportedLocales(
			Arrays.asList(SupportedLanguage.FRENCH.getLocale(), SupportedLanguage.ENGLISH.getLocale()));
		return localeResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("content");
		return messageSource;
	}
}
