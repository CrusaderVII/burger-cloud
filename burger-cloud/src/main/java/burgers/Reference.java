package burgers;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "reference_between_burgers_and_orders")
public class Reference {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ordered_burger_id", nullable = false)
	int orderedBurgerId;
	
	@ManyToOne
	@JoinColumn(name="id")
	private Order order;
	
	@ManyToOne
	@JoinColumn(name="code_name")
	private Burger burger;

}
