package spotifyPlayListAPI.test;

import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeMethod
    public void methodName(Method method) {
        System.out.println("Methos name is --> "+method.getName());
        System.out.println("Starting Thread is --> " +Thread.currentThread());
    }
}
