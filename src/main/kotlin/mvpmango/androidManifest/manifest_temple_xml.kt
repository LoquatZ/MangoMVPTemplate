package mvpmango.androidManifest

fun manifestTemplateXml(packageName:String,activityPackageName:String,activityClass:String) = """
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
	package="$packageName">

    <application>
	    <activity android:name="${activityPackageName}.${activityClass}"
                  android:screenOrientation="portrait"/>
    </application>
</manifest>
        """