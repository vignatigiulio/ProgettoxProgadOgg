package com.ForecastApplicationTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.Forecast.Forecast.Model.Exceptions.ApiRequestException;

class ForecastTest2 {
    
    private float x;
    @BeforeEach
    void setUp() throws Exception {
	x = -4;
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    void test() {
	if (x<0) throw new ApiRequestException(x);
    }

}
