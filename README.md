# Build guide
## Gradle

### Testing
First check that you are able to compile and pass the tests:
```
gradle test
```

For test report and code coverage: 

```
open build/reports/tests/test/index.html

gradle build jacocoTestReport
open build/reports/jacoco/test/html/index.html
```

### Start

To run the backend API locally: 

```
gradle bootRun
```

Otherwise, to build it as a fat jar and execute it:

```
gradle bootJar
java -jar build/libs/spring-boot-junit5-example-1.0-SNAPSHOT.jar
```

## Maven

### Testing
First check that you are able to compile and pass the tests:
```
mvn clean test
```

For test report and code coverage: 

```
mvn surefire-report:report
open target/site/surefire-report.html

mvn jacoco:report
open target/site/jacoco/index.html
```

### Start

To run the backend API locally: 

```
mvn spring-boot:run
```

Otherwise, to build it as a fat jar and execute it:

```
mvn clean install 
java -jar target/spring-boot-junit5-1.0-SNAPSHOT.jar
```

# Server check

To access to the database (H2) on dev mode:

```
open http://localhost:8080/h2-console 
```

API Documentation (Swagger): 

```
open http://localhost:8080/v3/api-docs
open http://localhost:8080/swagger-ui.html
```
## Jenkins War
- https://www.jenkins.io/doc/book/installing/war-file/

##  Sample JUNIT Integration  Spring Boot
```java

/**
 * 
 */
package example.service;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import example.dto.BookDto;

/**
 * @author SOULEYSANOGO
 *
 */
@SpringBootTest
class MyBookServiceTest {
	
	@Autowired
	BookService  bookService;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void testListBook() {
		
		// Given
		
		// When
		List<BookDto> listBook = bookService.list();
		for (BookDto bookDto : listBook) {
			System.out.println(bookDto.toString());
		}
		
		//Then
		assertFalse(listBook.isEmpty());
		
		
	}

}


```
