package litecartTest.appTests.framework;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;

/**
 * Created by mashomri on 23.03.2017.
 */
public class MyAssertions extends SoftAssert{
    public <T> void assertThat(final T actual, final Matcher<? super T> matcher) {
        doAssert(new IAssert() {
            @Override
            public String getMessage() {
                return null;
            }

            @Override
            public void doAssert() {
                MatcherAssert.assertThat(actual, matcher);

            }

            @Override
            public Object getActual() {
                return actual;
            }

            @Override
            public Object getExpected() {
                return null;
            }
        });
    }

    public <T> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
        doAssert(new IAssert() {
            @Override
            public String getMessage() {
                return reason;
            }

            @Override
            public void doAssert() {
                MatcherAssert.assertThat(reason, actual, matcher);

            }

            @Override
            public Object getActual() {
                return actual;
            }

            @Override
            public Object getExpected() {
                return null;
            }
        });
    }
}
