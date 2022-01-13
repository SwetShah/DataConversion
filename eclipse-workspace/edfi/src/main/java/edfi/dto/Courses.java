package edfi.dto;

import java.util.List;

import lombok.Data;

@Data
public class Courses {

	private String courseCode;
	private List<CourseIdentificationCode> identificationCodes;
	private List<EducationOrganizationReference> educationOrganizationReference;
	private String courseTitle;
	private int numberOfParts;

}
