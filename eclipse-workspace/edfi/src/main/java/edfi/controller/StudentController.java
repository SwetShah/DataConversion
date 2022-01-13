package edfi.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;

import edfi.dto.Grades;
import edfi.dto.GradingPeriod;
import edfi.dto.Schools;
import edfi.dto.Section;
import edfi.dto.Student;
import edfi.dto.StudentSchoolAssociation;
import edfi.dto.StudentSectionAssociation;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__({ @Autowired }))
public class StudentController {

	@PostMapping("/import/student")
	public void importStudentDetails(@RequestParam("file") MultipartFile inputExcel) throws Exception {

		Workbook workbook = new XSSFWorkbook(inputExcel.getInputStream());

		ObjectMapper mapper = new ObjectMapper();

		// For Students table
		List<Student> students = new ArrayList<Student>();
		Sheet StudentSheet = workbook.getSheet("Students");
		for (Row row : StudentSheet) {
			String studentUniqueId = row.getCell(0).getStringCellValue();
			String birthDate = row.getCell(1).getStringCellValue();
			String firstName = row.getCell(2).getStringCellValue();
			String lastSurname = row.getCell(3).getStringCellValue();

			Student student = new Student(studentUniqueId, birthDate, firstName, lastSurname);
			students.add(student);
		}

		String json = mapper.writeValueAsString(students);
		System.out.println("Students data");
		System.out.println(json);

		// For StudentSchoolAssociations table
		List<StudentSchoolAssociation> studentSchoolAssociations = new ArrayList<StudentSchoolAssociation>();
		Sheet studentSchoolAssociationSheet = workbook.getSheet("StudentSchoolAssociations");
		for (Row row : studentSchoolAssociationSheet) {
			String entryDate = row.getCell(0).getStringCellValue();
			Schools schoolReference = (Schools) row.getCell(1);
			Student studentReference = (Student) row.getCell(2);
			String entryGradeLevelDescriptor = row.getCell(3).getStringCellValue();

			StudentSchoolAssociation studentSchoolAssociation = new StudentSchoolAssociation(entryDate, schoolReference,
					studentReference, entryGradeLevelDescriptor);
			studentSchoolAssociations.add(studentSchoolAssociation);
		}

		json = mapper.writeValueAsString(studentSchoolAssociations);
		System.out.println("StudentSchoolAssociations data");
		System.out.println(json);

		// For StudentSectionAssociations table
		List<StudentSectionAssociation> studentSectionAssociations = new ArrayList<StudentSectionAssociation>();
		Sheet studentSectionAssociationSheet = workbook.getSheet("StudentSectionAssociation");
		for (Row row : studentSectionAssociationSheet) {
			String beginDate = row.getCell(0).getStringCellValue();
			Section sectionReference = (Section) row.getCell(1);
			Student studentReference = (Student) row.getCell(2);

			StudentSectionAssociation studentSectionAssociation = new StudentSectionAssociation(beginDate,
					sectionReference, studentReference);
			studentSectionAssociations.add(studentSectionAssociation);
		}

		json = mapper.writeValueAsString(studentSectionAssociations);
		System.out.println("StudentSectionAssociation data");
		System.out.println(json);

		// For Grades table
		List<Grades> grades = new ArrayList<Grades>();
		Sheet gradesSheet = workbook.getSheet("Grades");
		for (Row row : gradesSheet) {
			String gradeTypeDescriptor = row.getCell(0).getStringCellValue();
			GradingPeriod gradingPeriodReference = (GradingPeriod) row.getCell(1);
			StudentSectionAssociation studentSectionAssociationReference = (StudentSectionAssociation) row.getCell(2);

			Grades grade = new Grades(gradeTypeDescriptor, gradingPeriodReference, studentSectionAssociationReference);
			grades.add(grade);
		}

		json = mapper.writeValueAsString(grades);
		System.out.println("Grades data");
		System.out.println(json);

	}

	@PostMapping("/import/student/school/association")
	public void importStudentSchoolAssociation() {

	}

	@PostMapping("/import/student/section/association")
	public void importStudentSectionAssociation() {

	}

	@PostMapping("/import/grades")
	public void importGrades() {

	}

}
