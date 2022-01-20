package edfi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GradingPeriod {

	private String gradingPeriodDescriptor;
	private int periodSequence;
	private Schools schoolReference;
	private SchoolYearType schoolYearTypeReference;
	private String beginDate;
	private String endDate;
	private int totalInstructionalDays;

}
