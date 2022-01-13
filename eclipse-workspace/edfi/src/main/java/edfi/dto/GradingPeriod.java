package edfi.dto;

import lombok.Data;

@Data
public class GradingPeriod {

	private String gradingPeriodDescriptor;
	private int periodSequence;
	private Schools schoolReference;
	private SchoolYearType schoolYearTypeReference;
	private String beginDate;
	private String endDate;
	private int totalInstructionalDays;

}
