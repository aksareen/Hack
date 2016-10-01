

package com.ibm.mobileappbuilder.businessdirectory20150916145512;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;
import android.support.multidex.MultiDexApplication;
import ibmmobileappbuilder.analytics.injector.AnalyticsReporterInjector;
import ibmmobileappbuilder.cloudant.factory.CloudantDatabaseSyncerFactory;
import java.net.URI;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
        AnalyticsReporterInjector.analyticsReporter(this).init(this);
        //Syncing cloudant ds
        CloudantDatabaseSyncerFactory.instanceFor(
            "contest-database",
            URI.create("https://8686dfe4-0f82-4076-85b2-f913867b94a6-bluemix:4cc3822ee1be0c837889b02242eebb034ac6fbd53e5490e5077362dc37f6ac49@8686dfe4-0f82-4076-85b2-f913867b94a6-bluemix.cloudant.com/contest-database")
        ).sync(null);
      }
}

