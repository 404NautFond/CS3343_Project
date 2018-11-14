package commonHandlerTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	AverageHandlerTest.class,
	CountHandlerTest.class,
	MaxHandlerTest.class,
	MinHandlerTest.class,
	SumHandlerTest.class
})
public class CommonUnitTest {

}
