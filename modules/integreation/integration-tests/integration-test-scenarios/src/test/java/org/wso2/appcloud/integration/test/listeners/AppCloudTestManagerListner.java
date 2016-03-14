/*
* Copyright (c) 2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.wso2.appcloud.integration.test.listeners;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.wso2.appcloud.integration.test.utils.AppCloudIntegrationTestConstants;
import org.wso2.appcloud.integration.test.utils.AppCloudIntegrationTestUtils;
import org.wso2.carbon.automation.engine.testlisteners.TestManagerListener;

public class AppCloudTestManagerListner extends TestManagerListener {

    private static final Log log = LogFactory.getLog(AppCloudTestManagerListner.class);

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        super.onTestFailedButWithinSuccessPercentage(iTestResult);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        super.onStart(iTestContext);
        try {
	        String defaultAdmin = AppCloudIntegrationTestUtils.getAdminUsername();
	        String defaultAdminPassword = AppCloudIntegrationTestUtils.getAdminPassword();
	        String serverUrl = AppCloudIntegrationTestUtils.getPropertyValue(AppCloudIntegrationTestConstants.URLS_APPCLOUD);
	        AppCloudIntegrationTestUtils.createDefaultApplication(serverUrl, defaultAdmin, defaultAdminPassword);
        } catch (Exception e) {
            final String msg = "Error occurred while creating default app ";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        super.onFinish(iTestContext);
        try {
	        String defaultAdmin = AppCloudIntegrationTestUtils.getAdminUsername();
	        String defaultAdminPassword = AppCloudIntegrationTestUtils.getAdminPassword();
	        String serverUrl = AppCloudIntegrationTestUtils.getPropertyValue(AppCloudIntegrationTestConstants.URLS_APPCLOUD);
	        AppCloudIntegrationTestUtils.deleteDefaultApplication(serverUrl, defaultAdmin, defaultAdminPassword);
        } catch (Exception e) {
            final String msg = "Error occurred while deleting default app ";
            log.error(msg, e);
            throw new IllegalStateException(msg, e);
        }
    }

}
