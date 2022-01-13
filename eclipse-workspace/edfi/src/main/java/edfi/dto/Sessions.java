package edfi.dto;

import lombok.Data;

@Data
public class Sessions {

	private String sessionName;
	private Schools schoolReference;
	private SchoolYearType schoolYearTypeReference;
	private String beginDate;
	private String endDate;
	private String termDescriptor;
	private int totalInstructionalDays;

}
