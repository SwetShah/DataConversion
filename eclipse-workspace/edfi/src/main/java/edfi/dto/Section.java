package edfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Section {
	private String sectionIdentifier;
	private CourseOfferings courseOfferingReference;

}
