package au.com.redboxresearchdata.listener

import com.saucelabs.common.SauceOnDemandSessionIdProvider
import com.saucelabs.common.Utils
import com.saucelabs.junit.SauceOnDemandTestWatcher
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.TestListener
import org.gradle.api.tasks.testing.TestResult
import org.junit.runner.Description

/**
 * @author Matt Mulholland
 * @date 13/5/17
 */

class SauceTestListener extends SauceOnDemandTestWatcher implements TestListener {
    private static final long MAX_SIZE = 5

    static SauceOnDemandSessionIdProvider sauceOnDemandSessionIdProvider = new SauceOnDemandSessionIdProvider() {
        @Override
        public String getSessionId() {
            return Utils.readPropertyOrEnv("sessionId", null)
        }
    }

    SauceTestListener() {
        super(sauceOnDemandSessionIdProvider, false)
    }

    @Override
    void beforeSuite(TestDescriptor testDescriptor) {}

    @Override
    void afterSuite(TestDescriptor testDescriptor, TestResult testResult) {}

    @Override
    void beforeTest(TestDescriptor testDescriptor) {}

    @Override
    void afterTest(TestDescriptor testDescriptor, TestResult testResult) {
        def resultType = testResult.getResultType()
        //only needed for verbose reporting
        Description junitDescription = Description.createTestDescription(TestDescriptor.class, testDescriptor.getName())
        if ("SUCCESS" == resultType.toString()) {
            succeeded(junitDescription)
        } else {
            failed(new Exception("Test failed"), junitDescription)
        }
    }
}
