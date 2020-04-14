package interview.skilling;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static java.util.UUID.randomUUID;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class Exercise2Test {
    private Exercise2Support testSupport;

    @BeforeEach
    void beforeEach() {
        testSupport = new Exercise2Support();
    }

    @AfterEach
    void afterEach() {
        testSupport.quit();
    }

    @Test
    void successful_insert_new_computer_model_search_and_delete() {
        String uuid = randomUUID().toString();
        testSupport.loadStartPage();
        testSupport.navigateToAddComputer();
        testSupport.waitForPage("Add a computer");
        testSupport.fillNewComputerForm(uuid);
        testSupport.saveNewComputer();
        testSupport.waitForMessageContains("created");
        testSupport.searchFor(uuid);
        testSupport.assertNewComputerSearchResult(uuid);
        testSupport.navigateToUpdateFoundComputer(uuid);
        testSupport.waitForPage("Edit computer");
        testSupport.deleteComputer();
        testSupport.waitForMessageContains("deleted");
    }

    @Test
    void insert_new_computer_fails_name_is_required() {
        testSupport.loadStartPage();
        testSupport.navigateToAddComputer();
        testSupport.waitForPage("Add a computer");
        testSupport.fillNewComputerForm();
        testSupport.clearComputerFormField("name");
        testSupport.saveNewComputer();
        testSupport.assertFieldError("name", "Required");
    }

    @Test
    void insert_new_computer_fails_introduced_date_has_bad_format() {
        testSupport.loadStartPage();
        testSupport.navigateToAddComputer();
        testSupport.waitForPage("Add a computer");
        testSupport.fillNewComputerForm();
        testSupport.setComputerFormFieldText("introduced", "bad-format");
        testSupport.saveNewComputer();
        testSupport.assertFieldError("introduced", "Date ('yyyy-MM-dd')");
    }

    @Test
    void insert_new_computer_fails_discontinued_date_has_bad_format() {
        testSupport.loadStartPage();
        testSupport.navigateToAddComputer();
        testSupport.waitForPage("Add a computer");
        testSupport.fillNewComputerForm();
        testSupport.setComputerFormFieldText("discontinued", "bad-format");
        testSupport.saveNewComputer();
        testSupport.assertFieldError("discontinued", "Date ('yyyy-MM-dd')");
    }

}
