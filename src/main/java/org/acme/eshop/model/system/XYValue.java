package org.acme.eshop.model.system;

import lombok.Value;

@Value
public class XYValue<X, Y, V> {
	private final X hkey;
	private final Y vkey;
	private final V value;
}
