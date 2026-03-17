package app.controllers;

import app.services.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {

    @Test
    void shouldRejectBlankUsername() {
        //Arrange
        //Act
        String error = Validator.validateUser("", "1234");
        //Assert
        assertEquals("Brugernavn skal udfyldes", error);
    }

    @Test
    void shouldAcceptValidUsernameAndPassword() {
        //Arrange
        //Act
        String error = Validator.validateUser("tine", "1234");
        //Assert
        assertNull(error);
        //assertEquals(null, error);
    }


}