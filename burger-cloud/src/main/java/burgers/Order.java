package burgers;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Order {
	
	@NotNull
	private int id;
	
	@NotNull
	private float price;
	
	@NotNull
	private List<Burger> burgers = new LinkedList<>();
	
	@NotNull
	private LocalDateTime ordersDate;
}
