package pl.tinylinden.squigglydemo.cucumber

import io.cucumber.junit.Cucumber
import io.cucumber.junit.CucumberOptions
import io.cucumber.spring.CucumberContextConfiguration
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.DEFINED_PORT

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:features", plugin = ["html:build/reports/tests/cucumber/index.html"])
class RunnerIT extends SpringIntegrationTest {}

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = DEFINED_PORT)
@ActiveProfiles(["integration"])
abstract class SpringIntegrationTest {}
