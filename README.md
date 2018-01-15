# Sample REST API with Spring Boot export xls


### Dependency

```xml
    <dependency>
        <groupId>org.jxls</groupId>
        <artifactId>jxls-poi</artifactId>
        <version>1.0.11</version>
    </dependency>
``` 

### Example Code

```java
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
```   

