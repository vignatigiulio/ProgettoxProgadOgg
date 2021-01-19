package com.ForecastApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.Forecast.Forecast.Model.Exceptions.ApiRequestException;
import com.Forecast.Forecast.Model.Stats.StatsMethod;
import com.Forecast.Forecast.Model.Utils.CalcErrorThreshold;

class ForecastTest {

    private float calcolo;
    private Vector<Double> temperature = null;
    private CalcErrorThreshold a = new CalcErrorThreshold();
    float  input = 0;
    @BeforeEach
    void setUp() throws Exception {
    input = -7;
    calcolo = a.Calcolo("ancona");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    @DisplayName("Test error threshold")
    void testError_threshold() {
	assertEquals((float) 3.79, calcolo);
    }
    
    /*@Test
    void test2() {
	ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {calcolo;}); */
    @Test
    @DisplayName("Test ApiRequestException ")
    void exceptionTesting() {
    		Throwable exception = assertThrows(ApiRequestException.class, () -> {
    	        throw new ApiRequestException(input);
    	    });
    	    assertEquals("this error threshold cannot be negative", exception.getMessage());
    }
   @Test
    @DisplayName("Test NullPointerException ")
    void exceptionCityTesting() { // The test method does not have to be public in JUnit5
    	
    	StatsMethod Df = new StatsMethod();
    	NullPointerException thrown = assertThrows(
    			NullPointerException.class, 
            () -> Df.check(temperature));
       
        assertEquals("Cannot invoke \"java.util.Vector.isEmpty()\" because \"temp\" is null", thrown.getMessage());} 
      
}

 


