package stepdefs;

import io.cucumber.java.en.Given;

import java.util.List;
import java.util.Map;

public class ApiStepDefs {

    @Given("a new student has been created with following request")
    public void aNewStudentHasBeenCreatedWithFollowingRequest(List<Map<String, String>> requestMapsFromFeatureFile) {
        requestMapsFromFeatureFile.forEach(map -> {
            System.out.println(map.get("name"));
            System.out.println(map.get("email"));
            System.out.println(map.get("id"));
        });
    }
}
