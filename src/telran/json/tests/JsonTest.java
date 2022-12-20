package telran.json.tests;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import telran.json.Json;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {
    String jsonSimple = "{\"name\":\"John\",\"age\":\"23\",\"address\":\"Haifa Herzel 12\",\"salary\":\"100.00\",\"bday\":\"10/10/1991\"}";
    String jsonAdv = "{\"name\":\"John\",\"age\":\"23\",\"address\":{\"city\":\"Haifa\",\"street\":\"Herzel\",\"house\":\"12\"},\"salary\":\"100.00\",\"bday\":\"10/10/1991\"}";
    String jsonAdvArr = "[" + jsonAdv + "," + jsonAdv + "]";
    String jsonCompany = """
{
  "companyName":"Google",
  "address":"New York",
  "companyCountry":"USA",
  "open_date":"25 12 2003"
}""";
    
    SimplePerson expectedPerson = new SimplePerson("John", 23, "Haifa Herzel 12", 100.00, LocalDate.of(1991, 10, 10));
    AdvPerson expectedAdvPerson = new AdvPerson("John", 23, new Address("Haifa", "Herzel", 12), 100.00, LocalDate.of(1991, 10, 10));
    AdvPerson[] expectedAdvPersonArr = {expectedAdvPerson, expectedAdvPerson};
    Company expectedCompany = new Company("Google", "New York", "USA", LocalDate.of(2003, 12, 25));
    
    Json mapper = new Json();

    @Test
    void parseSimpleQuoted_Success()  throws Exception {
    	assertEquals(123, mapper.parse("\"123\"", int.class));
    }
    
    @Test
    void parseNotNested_Success() throws Exception {
        SimplePerson actualPerson = mapper.parse(jsonSimple, SimplePerson.class);
        assertEquals(expectedPerson, actualPerson);
    }

    @Test
    void parseNested_Success() throws Exception {
        AdvPerson actualAdvPerson = mapper.parse(jsonAdv, AdvPerson.class);        
        assertEquals(expectedAdvPerson, actualAdvPerson);
    }
    
    @Test
    void parseArray_Success() throws Exception {    	
        AdvPerson[] actualAdvPersonArr = mapper.parse(jsonAdvArr, AdvPerson[].class);
        assertArrayEquals(expectedAdvPersonArr, actualAdvPersonArr);
    }
    
    @Test 
    void parseAnnotatedObject_Success() throws Exception {
    	Company actualCompany = mapper.parse(jsonCompany, Company.class);
        assertEquals(expectedCompany, actualCompany);
    }
}