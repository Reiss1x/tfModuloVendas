package com.pucrs.modulovendas.entities;

import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {
    private String name;
    private ArrayList<ItemDTO> items;
    
    
    public String getName() {
        return name;
    }
    public List<ItemDTO> getItems() {
        return items;
    }

    
}
