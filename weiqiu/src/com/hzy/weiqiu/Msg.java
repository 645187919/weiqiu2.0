package com.hzy.weiqiu;

public class Msg {
	public static final int TYPE_RECEIVE=0;
	public static final int TYPE_SEND=1;
	 private String content;
	 private int type;
	 
	public  Msg(String content, int type){
		 this.content=content;
		 this.type=type;
	 }
	 public String getcontent(){
		 return content;
	 }
	 public int gettype(){
		 return type;
	 }
	 

}
