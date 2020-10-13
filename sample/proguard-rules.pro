# # -------------------------------------------
# #  ############### MODELS ###############
# # -------------------------------------------
-keepclassmembers class com.arlib.** { *; }
## -------------------------------------------
# #  ############### Gson ###############
# # -------------------------------------------
-keepattributes Annotation
-keepattributes EnclosingMethod
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }