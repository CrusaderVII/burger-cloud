package burgers;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.SecondaryTable;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="burgers_menu")
public class Burger {
	
	@Id
	@Column(name="code_name", nullable = false)
	private String codeName;
	
	@Column(name="burger_name", nullable = false)
	private String name;
	
	@Column(name="price", nullable = false)
	private double price;
	
	@Column(name="name_for_image")
	private String imageName;

	public String getName() {
		return this.name.toUpperCase();
	}
	
	public double getPrice() {
		String res = String.format("%.2f",this.price);
		res = res.replaceAll(",", "\\.");
		return Double.parseDouble(res);
	}
}
