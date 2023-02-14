package burgers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "orders")
@NoArgsConstructor
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_id", nullable = false)
	private int id;
	
	@ManyToOne
	@JoinColumn(name="id")
	private User user;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime ordersDate;
	
	@NotNull(message="Please enter addres, we dont wanna send our burgers to eternal void:(")
	@Column(name = "addres", nullable = false)
	private String addres;
	
	@Transient
	List<Burger> chart = new ArrayList<>();
	
	public void addBurger(Burger burger) {
		this.chart.add(burger);
	}
	
}
