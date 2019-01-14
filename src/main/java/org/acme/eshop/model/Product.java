package org.acme.eshop.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "PRODUCTS")
@SequenceGenerator(name = "idGenerator", sequenceName = "PRODUCTS_SEQ", initialValue = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {
	private static final long serialVersionUID = 3652014761173603947L;
	@NotNull
	@Column(length = 50, nullable = false)
	private String description;
	@NotNull
	@Column
	private BigDecimal price;
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;
}
