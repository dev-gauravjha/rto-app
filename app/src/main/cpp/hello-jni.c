#include <string.h>
#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_rankexam_rtoinfo_Data_MainURl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "86b5144127e20d1d64a8b55d83f3207a77621df6255637f1315f3355c23ed3b645840ca5406a6a9442414125e088bd4a");
}

JNIEXPORT jstring JNICALL
Java_com_rankexam_rtoinfo_Data_rskey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "2b7e151628aed2a6");
}

JNIEXPORT jstring JNICALL
Java_com_rankexam_rtoinfo_Data_rikey(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "gs_business__gtu");
}

JNIEXPORT jstring JNICALL
Java_com_rankexam_rtoinfo_Data_opid(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "f45733ee22ead1d73eb442d6065ca8958bdf3e8106692cc1dd7e8636d83531f0a41dee149850cc5b6c80ace4512cdff1a8ce37ea38e89bf71bd6d89aec9bfbd7");
}

JNIEXPORT jstring JNICALL
Java_com_rankexam_rtoinfo_Data_opversion(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "a406d052ad707165375162cd7c725540");
}

JNIEXPORT jstring JNICALL
Java_com_rankexam_rtoinfo_Data_opversioncode(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "9f4255afde6b13229e4b8c6c48e06edf");
}
