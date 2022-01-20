package edfi.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edfi.service.StudentService;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
@AllArgsConstructor(onConstructor = @__({ @Autowired }))
public class StudentController {

	@Autowired
	private StudentService service;

	@PostMapping("/import/{type}")
	public String importStudentDetails(@RequestParam("file") MultipartFile inputExcel, @PathVariable("type") String type)
			throws Exception {
		return service.readFile(inputExcel, type);
	}

//	@PostMapping("/import/student/school/association")
//	public void importStudentSchoolAssociation() {
//
//	}
//
//	@PostMapping("/import/student/section/association")
//	public void importStudentSectionAssociation() {
//
//	}
//
//	@PostMapping("/import/grades")
//	public void importGrades() {
//
//	}

}
