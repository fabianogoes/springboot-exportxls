package com.example.demoxls;

import org.jxls.template.SimpleExporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@SpringBootApplication
public class DemoxlsApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoxlsApplication.class, args);
	}


	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(HttpServletResponse response) {
		List<Person> persons = Arrays.asList(
				Person.builder().firstName(UUID.randomUUID().toString()).lastName(UUID.randomUUID().toString()).build(),
				Person.builder().firstName(UUID.randomUUID().toString()).lastName(UUID.randomUUID().toString()).build(),
				Person.builder().firstName(UUID.randomUUID().toString()).lastName(UUID.randomUUID().toString()).build(),
				Person.builder().firstName(UUID.randomUUID().toString()).lastName(UUID.randomUUID().toString()).build()
		);

		List<String> headers = Arrays.asList("First Name", "Last Name");
		try {
			response.addHeader("Content-disposition", "attachment; filename=People.xlsx");
			response.setContentType("application/vnd.ms-excel");
			new SimpleExporter().gridExport(headers, persons, "firstName, lastName, ", response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
