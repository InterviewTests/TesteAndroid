package com.aline.teste.Models;

import com.google.gson.annotations.SerializedName;


public class Result {

	@SerializedName("screen")
	private Screen screen;

	public void setScreen(Screen screen){
		this.screen = screen;
	}

	public Screen getScreen(){
		return screen;
	}
}