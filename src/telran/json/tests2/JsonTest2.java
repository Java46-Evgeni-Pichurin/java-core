package telran.json.tests2;

import telran.json.Json;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest2 {
	String json3 = """
            {
                "name":"John",
                "age":"23",
                "address":{
                    "city":"Haifa",
                    "street":"Herzel",
                    "house":"12",
                    "subAddress":{
                        "name":"Name",
                        "value":"Hello"
                    }
                },
                "salary":"100.00",
                "bday":"10/10/1991"
            }
            """;
	Json mapper = new Json();
	
    @Test
    void parseNestedObject_Success()  throws Exception {
    	AdvPerson expected = new AdvPerson(
    			"John", 23, new Address(
    					"Haifa","Herzel",12, new SubAddress(
    							"Name","Hello"
    					)
    			), 100.00, LocalDate.of(1991,10,10) 
    	);
    	assertEquals(expected, mapper.parse(json3,AdvPerson.class));
    }
    
}