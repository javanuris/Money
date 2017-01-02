package com.example.user.money.enums;

/**
 * Created by User on 02.01.2017.
 */

public enum OperationType {
    INCOME("1"),
    OUTCOME("2");

    private String id;

    private OperationType(String id){
        this.id = id;
    }
    public String getId(){
        return id;
    }

}
