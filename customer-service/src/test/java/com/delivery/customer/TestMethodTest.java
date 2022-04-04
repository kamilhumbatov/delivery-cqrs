package com.delivery.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TestMethodTest {


    @InjectMocks
    TestMethod testMethod;

    @Test
    void given_when_then() {
        int test = testMethod.test();
        assertEquals(test, 5);

    }
}