
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
