package edfi.service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import edfi.dto.Grades;
import edfi.dto.GradingPeriod;
import edfi.dto.Schools;
import edfi.dto.Section;
import edfi.dto.Student;
import edfi.dto.StudentSchoolAssociation;
import edfi.dto.StudentSectionAssociation;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__({ @Autowired }))
public class StudentService {

	@Autowired
	ObjectMapper mapper;

	public String readFile(MultipartFile inputExcel, String type) throws IOException {
		
		String json = null;

		CSVReader csvReader = new CSVReaderBuilder(new InputStreamReader(inputExcel.getInputStream())).withSkipLines(1)
				.build();
		type = type.toUpperCase();
		switch (type) {
		case "STUDENT":
			List<String[]> studentData = csvReader.readAll();
			List<Student> students = new ArrayList<Student>();
			for (String[] eachRow : studentData) {
				String studentUniqueId = eachRow[0];
				String birthDate = eachRow[1];
				String firstName = eachRow[2];
				String lastSurname = eachRow[3];
				Student student = new Student(studentUniqueId, birthDate, firstName, lastSurname);
				students.add(student);
			}
			json =  mapper.writeValueAsString(students);
			break;

		case "STUDENTSCHOOLASSOCIATIONS":
			List<StudentSchoolAssociation> studentSchoolAssociations = new ArrayList<StudentSchoolAssociation>();
			List<String[]> studentSchoolAssociationSheet = csvReader.readAll();
			for (String[] eachRow : studentSchoolAssociationSheet) {
				String entryDate = eachRow[0];
				String schoolId = eachRow[1];
				Schools schoolReference = new Schools(null, null, Integer.valueOf(schoolId), null); // Need to fetch
																									// these from ODS
				String studentUniqueId = eachRow[2];
				Student studentReference = new Student(studentUniqueId, null, null, null);// Need to fetch these from
																							// ODS
				String entryGradeLevelDescriptor = eachRow[3];

				StudentSchoolAssociation studentSchoolAssociation = new StudentSchoolAssociation(entryDate,
						schoolReference, studentReference, entryGradeLevelDescriptor);
				studentSchoolAssociations.add(studentSchoolAssociation);
			}
			json = mapper.writeValueAsString(studentSchoolAssociations);
			break;

		case "STUDENTSECTIONASSOCIATIONS":
			List<StudentSectionAssociation> studentSectionAssociations = new ArrayList<StudentSectionAssociation>();
			List<String[]> studentSectionAssociationSheet = csvReader.readAll();
			for (String[] eachRow : studentSectionAssociationSheet) {
				String beginDate = eachRow[0];

				String sectionIdentifier = eachRow[1];
				Section sectionReference = new Section(sectionIdentifier, null);

				String studentUniqueId = eachRow[2];
				Student studentReference = new Student(studentUniqueId, null, null, null);// Need to fetch these from
																							// ODS

				StudentSectionAssociation studentSectionAssociation = new StudentSectionAssociation(beginDate,
						sectionReference, studentReference);
				studentSectionAssociations.add(studentSectionAssociation);
			}
			json = mapper.writeValueAsString(studentSectionAssociations);
			break;

		case "GRADES":
			List<Grades> grades = new ArrayList<Grades>();
			List<String[]> gradesSheet = csvReader.readAll();
			for (String[] eachRow : gradesSheet) {
				String gradeTypeDescriptor = eachRow[0];

				String gradingPeriodDescriptor = eachRow[1];
				GradingPeriod gradingPeriodReference = new GradingPeriod(gradingPeriodDescriptor, 0, null, null, null,
						null, 0); // Need to fetch these from ODS

				String beginDate = eachRow[2];
				StudentSectionAssociation studentSectionAssociationReference = new StudentSectionAssociation(beginDate,
						null, null); // Need to fetch these from ODS

				Grades grade = new Grades(gradeTypeDescriptor, gradingPeriodReference,
						studentSectionAssociationReference);
				grades.add(grade);
			}
			json = mapper.writeValueAsString(grades);
			break;
		}
		return json;
	}

}
