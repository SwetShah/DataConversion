package edfi.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Schools {
	private List<String> educationOrganizationCategories;
	private List<String> gradeLevels;
	private int schoolId;
	private String nameOfInstitution;

}
