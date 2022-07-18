package com.deeprooted.ordermanager.Utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static com.deeprooted.ordermanager.utils.Utility.convertTimeInMillis;

public class UtilityTest {
    @Test
    void givenStringTime_convertToMillis_thenReturnLongMillisecond(){
        String time = "10:15";
        Long actualTimeInMillis = convertTimeInMillis(time);
        Long expectedTimeInMillis = 36900000L;   //(hr*60*60+min*60)*1000(milliseconds)
        assertEquals(expectedTimeInMillis, actualTimeInMillis, "Some issues in millisecond Conversion");
    }
    @Test
    void givenStringTime_convertToMillis_thenThrowNumberFormatException(){
        String time = "10.15";       //Format should be hh:mm not hh.mm

        Long expectedTimeInMillis = 36900000L;   //(hr*60*60+min*60)*1000(milliseconds)
        assertThrows(NumberFormatException.class, ()-> convertTimeInMillis(time), "Expecting NumberFormatException as time is not in correct format");
    }
}
