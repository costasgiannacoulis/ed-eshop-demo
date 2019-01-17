package org.acme.eshop.enumeration;

import java.util.Locale;

import lombok.Getter;

public enum SupportedLanguage {
	ENGLISH("en", Locale.ENGLISH, new Locale("en", "US")),
	FRENCH("fr", Locale.FRENCH, new Locale("fr", "FR"));

	@Getter
	private String code;

	@Getter
	private Locale locale;

	@Getter
	private Locale regionalLocale;

	SupportedLanguage(final String code, final Locale locale, final Locale regionalLocale) {
		this.code = code;
		this.locale = locale;
		this.regionalLocale = regionalLocale;
	}

	public static SupportedLanguage getByCode(final String code) {
		for (final SupportedLanguage language : SupportedLanguage.values()) {
			if (language.getCode().equalsIgnoreCase(code)) {
				return language;
			}
		}

		return getDefault();
	}

	public static SupportedLanguage getDefault() {
		return SupportedLanguage.ENGLISH;
	}
}
