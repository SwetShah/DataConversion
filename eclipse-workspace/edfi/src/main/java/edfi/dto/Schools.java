package edfi.dto;

import java.util.List;

import lombok.Data;

@Data
public class Schools {
	private List<String> educationOrganizationCategories;
	private List<String> gradeLevels;
	private int schoolId;
	private String nameOfInstitution;

}
