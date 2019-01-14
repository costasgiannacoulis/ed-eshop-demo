package org.acme.eshop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "USERS")
@SequenceGenerator(name = "idGenerator", sequenceName = "USERS_SEQ", initialValue = 1)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
	private static final long serialVersionUID = -4425408128237333774L;
	@NotNull
	@Column(length = 20)
	private String firstname;
	@NotNull
	@Column(length = 50)
	private String lastname;
	@NotNull
	@Column(length = 100)
	private String email;
}
