package edfi.dto;

import lombok.Data;

@Data
public class CourseOfferings {

	private String localCourseCode;
	private Courses courseReference;
	private Schools schoolReference;
	private Sessions sessionReference;
}
