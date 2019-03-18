package com.rx.david.bean;

import java.util.List;

public class HomeDataBean {

   private String id;
   private String name;
   private int type;
   private boolean showAll;
   private boolean showBorder;
   private List<ContentsBean> contents;

   public String getId() {
      return id;
   }

   public void setId(String id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getType() {
      return type;
   }

   public void setType(int type) {
      this.type = type;
   }

   public boolean isShowAll() {
      return showAll;
   }

   public void setShowAll(boolean showAll) {
      this.showAll = showAll;
   }

   public boolean isShowBorder() {
      return showBorder;
   }

   public void setShowBorder(boolean showBorder) {
      this.showBorder = showBorder;
   }

   public List<ContentsBean> getContents() {
      return contents;
   }

   public void setContents(List<ContentsBean> contents) {
      this.contents = contents;
   }
}
