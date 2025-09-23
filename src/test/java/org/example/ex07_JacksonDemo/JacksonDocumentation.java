package org.example.ex07_JacksonDemo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;

public class JacksonDocumentation {
@Test
public void testJackson() throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    Booking booking = new Booking();
    //Serialization
    //String jsonStringValue = objectMapper.writeValueAsString(booking);

    //Deserialization
    //BookingResponse b=objectMapper.readValue(res.asString,BookingResponse.class);
   }
}
