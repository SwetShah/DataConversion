package edfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSchoolAssociation {

	private String entryDate;
	private Schools schoolReference;
	private Student studentReference;
	private String entryGradeLevelDescriptor;

}
