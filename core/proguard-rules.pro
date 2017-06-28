# retain these to support reflection and meaningful stack traces
-keep class com.shatteredpixel.** { *; }
-keep class xyz.dlobi.** { *; }
-keepattributes SourceFile,LineNumberTable

# private, because openIAB is weird
-dontwarn org.onepf.**