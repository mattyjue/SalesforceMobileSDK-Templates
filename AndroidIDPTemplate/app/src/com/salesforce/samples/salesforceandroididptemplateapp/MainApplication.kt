/*
 * Copyright (c) 2017-present, salesforce.com, inc.
 * All rights reserved.
 * Redistribution and use of this software in source and binary forms, with or
 * without modification, are permitted provided that the following conditions
 * are met:
 * - Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * - Neither the name of salesforce.com, inc. nor the names of its contributors
 * may be used to endorse or promote products derived from this software without
 * specific prior written permission of salesforce.com, inc.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package com.salesforce.samples.salesforceandroididptemplateapp

import android.app.Application
import com.salesforce.androidsdk.auth.idp.SPConfig
import com.salesforce.androidsdk.mobilesync.app.MobileSyncSDKManager

/**
 * Application class for our application.
 */
class MainApplication : Application() {

    companion object {
        private const val FEATURE_APP_USES_KOTLIN = "KT"
    }

    override fun onCreate() {
        super.onCreate()
        MobileSyncSDKManager.initNative(applicationContext, MainActivity::class.java)
        MobileSyncSDKManager.getInstance().registerUsedAppFeature(FEATURE_APP_USES_KOTLIN)

        // Setting app as IDP for these allowed SP apps
        val mobileSDKSampleAppConsumerKey = "3MVG98dostKihXN53TYStBIiS8FC2a3tE3XhGId0hQ37iQjF0xe4fxMSb2mFaWZn9e3GiLs1q67TNlyRji.Xw"
        val mobileSDKSampleAppCallbackUrl = "testsfdc:///mobilesdk/detect/oauth/done"

        MobileSyncSDKManager.getInstance().setAllowedSPApps(listOf(
            SPConfig(
                "com.salesforce.samples.mobilesyncexplorer",
                "com.salesforce.samples.mobilesyncexplorer.MainActivity",
                mobileSDKSampleAppConsumerKey,
                mobileSDKSampleAppCallbackUrl,
                arrayOf("api", "web")
            ),
            SPConfig(
                "com.salesforce.samples.restexplorer",
                "com.salesforce.samples.restexplorer.ExplorerActivity",
                mobileSDKSampleAppConsumerKey,
                mobileSDKSampleAppCallbackUrl,
                arrayOf("api", "web")
            ),
            SPConfig(
                "com.salesforce.samples.accounteditor",
                "com.salesforce.samples.accounteditor.SalesforceDroidGapActivity",
                mobileSDKSampleAppConsumerKey,
                mobileSDKSampleAppCallbackUrl,
                arrayOf("api", "web")
            )
        ))

        /*
		 * Un-comment the line below to enable push notifications in this app.
		 * Replace 'pnInterface' with your implementation of 'PushNotificationInterface'.
		 * Add your Firebase 'google-services.json' file to the 'app' folder of your project.
		 */
        // MobileSyncSDKManager.getInstance().pushNotificationReceiver = pnInterface
    }
}
