package edfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
	
	private String studentUniqueId;
	private String birthDate;
	private String firstName;
	private String lastSurname;

}
