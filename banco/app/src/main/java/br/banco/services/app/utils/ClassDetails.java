package br.banco.services.app.utils;

import android.os.Build;

public final class ClassDetails {

   private String ClassName;
   private String MethodName;
   private String MethodType;


   public ClassDetails(){

      // this.ClassName = getClass().getName();
       //this.MethodName = getClass().getEnclosingMethod().getName();
   }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getMethodName() {
        return MethodName;
    }

    public void setMethodName(String methodName) {
        MethodName = methodName;
    }

    public String getMethodType() {
        return MethodType;
    }

    public void setMethodType(String methodType) {
        MethodType = methodType;
    }
}
