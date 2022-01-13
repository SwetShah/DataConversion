package edfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Grades {

	private String gradeTypeDescriptor;
	private GradingPeriod gradingPeriodReference;
	private StudentSectionAssociation studentSectionAssociationReference;

}
