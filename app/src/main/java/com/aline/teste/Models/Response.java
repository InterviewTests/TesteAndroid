package com.aline.teste.Models;

import java.util.List;
import com.google.gson.annotations.SerializedName;


public class Response{

	@SerializedName("cells")
	private List<Cells> cells;

	public void setCells(List<Cells> cells){
		this.cells = cells;
	}

	public List<Cells> getCells(){
		return cells;
	}
}