package use_cases.user_action_use_case;

import org.junit.jupiter.api.Test;
import screens.user_like_screen.InMemoryUserLike;


import javax.management.InvalidAttributeValueException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class UserActInteractorTest {

    // new InMemoryUserLike() initialize two users, czz1 and czz2 for testing.
    @Test
    void like() {
        //1) UserActInteractor and prerequisite objects
        UserActDsGateway database = new InMemoryUserLike();

        UserActPresenterInterface presenter = new UserActPresenterInterface() {
            // 4) check User successfully liked another user
            @Override
            public String prepareSuccessView(String targetName){
                try {
                    // is czz1 liked czz2
                    assertTrue(database.isLiked("czz1", "czz2"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }

            @Override
            public String prepareMatchingView(String targetName, String contactInfo) {
                fail("wrongly went to matching vew!");
                return null;
            }

            @Override
            public String prepareFailView(String error) {
                fail("wrongly went to failed vew!");
                return null;
            }
        };
        //2) Input data
        UserActInputBoundary interactor = new UserActInteractor(database, presenter);
        UserActInputData inputData = new UserActInputData("czz1", "czz2");
        // 3) run the use case
        try {
            interactor.like(inputData);
        } catch (IOException | InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
     void testMatch() {
        //1) UserActInteractor and prerequisite objects
        UserActDsGateway database = new InMemoryUserLike();
        try {
            database.setLike("czz2", "czz1");
        } catch (IOException | InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }

        UserActPresenterInterface presenter = new UserActPresenterInterface() {
            // 4) check User successfully liked another user
            @Override
            public String prepareSuccessView(String targetName){
                fail("wrongly went to success view!");
                return null;
            }

            @Override
            public String prepareMatchingView(String targetName, String contactInfo) {
                try {
                    // is czz1 liked czz2
                    assertTrue(database.isLiked("czz1", "czz2"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return null;
            }

            @Override
            public String prepareFailView(String error) {
                fail("wrongly went to failed view!");
                return null;
            }
        };
        //2) Input data
        UserActInputBoundary interactor = new UserActInteractor(database, presenter);
        UserActInputData inputData = new UserActInputData("czz1", "czz2");
        // 3) run the use case
        try {
            interactor.like(inputData);
        } catch (IOException | InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testFail() {
        //1) UserActInteractor and prerequisite objects
        UserActDsGateway database = new InMemoryUserLike();
        try {
            database.setLike("czz1", "czz2");
        } catch (IOException | InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }

        UserActPresenterInterface presenter = new UserActPresenterInterface() {
            // 4) check User successfully liked another user
            @Override
            public String prepareSuccessView(String targetName){
                fail("wrongly went to success view!");
                return null;
            }

            @Override
            public String prepareMatchingView(String targetName, String contactInfo) {
                fail("wrongly went to matching view!");
                return null;
            }

            @Override
            public String prepareFailView(String error) {
                fail("failed as expected!");
                return null;
            }
        };
        //2) Input data
        UserActInputBoundary interactor = new UserActInteractor(database, presenter);
        UserActInputData inputData = new UserActInputData("czz1", "czz2");
        // 3) run the use case
        try {
            interactor.like(inputData);
        } catch (IOException | InvalidAttributeValueException e) {
            throw new RuntimeException(e);
        }
    }
}