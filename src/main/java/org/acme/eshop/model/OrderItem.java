package org.acme.eshop.model;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class OrderItem extends BaseEntity {
	private String description;
	private BigDecimal price;
	private Integer quantity;
}
