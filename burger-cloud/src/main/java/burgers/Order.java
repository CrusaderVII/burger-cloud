package burgers;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.CreditCardNumber;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
	@JoinColumn(name="id", nullable=true)
	private User user;
	
	@Column(name = "price", nullable = false)
	private double price;
	
	@Column(name = "date", nullable = false)
	private LocalDateTime ordersDate;
	
	@NotBlank(message="Please enter addres, we dont wanna send our burgers to eternal void:(")
	@Column(name = "address", nullable = false)
	private String address;
	
	@Transient
	@CreditCardNumber
	private String ccNumber;
	
	@Transient
	@Pattern(regexp="^(0[1-9]|1[0-2])/(20(2[3-9]|[3-9]\\d))$")
	private String ccExperaion;
	
	@Transient
	@Pattern(regexp="^\\d\\d\\d$")
	private String ccCVV;
	
	@Transient
	List<String> cart = new ArrayList<>();
	
	
	public void addBurgerCodeName(Burger burger) {
		this.cart.add(burger.getCodeName());
	}
	
	public void setCart(String codeName) {
		
		this.cart.add(codeName);
	}
	
	public void setCartFull(List<String> cart) {

		this.cart = cart;
	}
	
	public double getPrice() {
		String res = String.format("%.2f",this.price);
		res = res.replaceAll(",", "\\.");
		return Double.parseDouble(res);
	}
	
	public String getOrdersDate() {
		return this.ordersDate.getDayOfMonth()+"."+this.ordersDate.getMonthValue()+"."+this.ordersDate.getYear();
	}
	
	
}
