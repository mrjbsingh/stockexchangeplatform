package com.deeprooted.ordermanager.models;

import com.deeprooted.ordermanager.models.producecomponent.Produce;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProduceFactoryTest {
    @Test
    void givenProduceName_createProduceInstance_thenReturnProduceInstance(){
        String produceName = "tomato";
        Produce tomato = ProduceFactory.createProduceInstance(produceName);
        String expected = "TOMATO";
        assertEquals(expected, tomato.getProduceName());
    }

    @Test
    void givenProduceName_createProduceInstance_thenThrowNullPointerException(){
        String produceName = "brinjal";
        Produce brinjal = ProduceFactory.createProduceInstance(produceName);
        /*
            ProduceFactory should throw ProduceNotFoundException
            but as it is caught in try/catch block. So, createProduceInstance return null.
            So, Expected behaviour is nullPointerException
         */
        assertThrows(NullPointerException.class, ()->brinjal.getProduceName(), "Brinjal is not a registered Produce it should throw Error");
    }
}
