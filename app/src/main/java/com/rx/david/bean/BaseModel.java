package com.rx.david.bean;

/**
 * 封装统一返回model
 *
 * @param <M>
 */
@SuppressWarnings("ALL")
public class BaseModel {

   private int code = 0;
   private String error;

   public int getCode() {
      return code;
   }

   public void setCode(int code) {
      this.code = code;
   }

   public String getError() {
      return error;
   }

   public void setError(String error) {
      this.error = error;
   }
}
