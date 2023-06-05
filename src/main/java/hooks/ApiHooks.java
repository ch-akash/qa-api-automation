package hooks;

import io.cucumber.java.*;

public class ApiHooks {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Before all the scenarios");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("After all the scenarios");
    }

    @Before(value = "@requires-db-connection")
    public void before() {
        //Initialize DB connection
        System.out.println("Before each Api test which needs db connection");
    }

    @BeforeStep()
    public void beforeStep() {
        System.out.println("This is before step");
    }

    @AfterStep()
    public void afterStep() {
        System.out.println("This is after step");
    }

    @After()
    public void after() {
        System.out.println("After each scenario which has smoke tag");
    }
}
