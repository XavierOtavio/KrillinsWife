package com.example.app1;

import java.util.ArrayList;
import java.util.List;


public class Class1 {

    List<String> getPhoneNumber(String friend){
        List<String> phoneNumber = new ArrayList<>();

        if (friend.equals("Copos")) {
            phoneNumber.add("911111111");
            phoneNumber.add("911111111");
            phoneNumber.add("911111111");
        } else if (friend.equals("Bola")) {
            phoneNumber.add("922222222");
            phoneNumber.add("922222222");
            phoneNumber.add("922222222");
        } else if (friend.equals("Trabalho")) {
            phoneNumber.add("933333333");
            phoneNumber.add("933333333");
            phoneNumber.add("933333333");

        } else {
            phoneNumber.add("NA");
        }
        return phoneNumber;
    }

}
