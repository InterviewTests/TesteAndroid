package com.aline.teste.eventbus;

import com.aline.teste.Models.Cells;

import java.util.ArrayList;
import java.util.List;

public class EventContato {

    private List<Cells> cellsList = new ArrayList<>();

    public EventContato(List<Cells> cellsList){
        this.cellsList = cellsList;
    }

   List<Cells> getCellsList(){
        return cellsList;
   }
}
