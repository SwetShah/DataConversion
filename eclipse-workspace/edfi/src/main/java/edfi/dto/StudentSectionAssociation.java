package edfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentSectionAssociation {

	private String beginDate;
	private Section sectionReference;
	private Student studentReference;
}
