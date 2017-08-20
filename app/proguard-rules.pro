
-dontskipnonpubliclibraryclasses
-dontpreverify
-optimizationpasses 5
-overloadaggressively
-allowaccessmodification

-repackageclasses "_"
-renamesourcefileattribute "_"

-assumenosideeffects class kotlin.jvm.internal.Intrinsics {
  static void checkParameterIsNotNull(java.lang.Object, java.lang.String);
}

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgent
-keep public class * extends android.preference.Preference

-keepclassmembers class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator CREATOR;
}

-dontwarn okio.**
-dontwarn com.squareup.picasso.**

-keepclassmembers enum * {
    public static **[] values();
#    public static ** valueOf(java.lang.String);
}


-dontwarn okio.**
-dontwarn javax.annotation.**

# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
#-keepattributes Exceptions

-useuniqueclassmembernames # Getting crash on coroutine code without this line
