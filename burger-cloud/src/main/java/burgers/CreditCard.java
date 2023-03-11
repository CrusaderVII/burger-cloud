package burgers;

import org.hibernate.validator.constraints.CreditCardNumber;

import burgers.validation.ValidUserPasswordConfirm;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@Entity
@Table(name = "credit_cards")
public class CreditCard {
	
	@OneToOne
	@JoinColumn(name="id", nullable=false)
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cc_id", nullable = false)
	private int ccId;
	
	@CreditCardNumber
	@Column(name="cc_number", nullable = false)
	private String ccNumber;
	
	@Pattern(regexp="^(0[1-9]|1[0-2])/(20(2[3-9]|[3-9]\\d))$")
	@Column(name="cc_experation", nullable = false)
	private String ccExperation;
	
	@Pattern(regexp="^\\d\\d\\d$")
	@Column(name="cc_CVV", nullable = false)
	private String ccCVV;
}
