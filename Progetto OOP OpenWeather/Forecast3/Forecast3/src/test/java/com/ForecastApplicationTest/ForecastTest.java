package com.ForecastApplicationTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.Forecast.Forecast.Model.Utils.CalcErrorThreshold;

class ForecastTest {

    private float calcolo, x;
    private CalcErrorThreshold a = new CalcErrorThreshold();
/*    private StatsMethod c = new StatsMethod();
    private StatsForecast b = new StatsForecast(); */
    
    @BeforeEach
    void setUp() throws Exception {
/*	temp.add(3.50);
	temp.add(4.50);
	temp.add(3.0); */
	x = -4;
	calcolo = a.Calcolo("ancona");
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
	assertEquals((float) 3.79, calcolo);
    }
    
    @Test
    void test2() {
//	ApiRequestException exception = assertThrows(ApiRequestException.class, () -> {calcolo;});
    }

}
