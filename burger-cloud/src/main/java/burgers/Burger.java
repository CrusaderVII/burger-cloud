package burgers;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Burger {
	
	@NotNull(message="Plz, name your burger masterpiece:3")
	private String name;
}
