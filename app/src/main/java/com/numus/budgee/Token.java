package com.numus.budgee;

import java.util.ArrayList;
import java.util.Random;

public class Token {

    public int siez;
    public String characters;
    public Token() {
        this.siez = 32;
        this.characters = "QWERTYUIOPASDFGHJKLZXCVBNMasdfghjklqwertyuiopzxcvbnm1234567890";
    }

    public String generate(){
        String[] tokenArray = this.characters.split("");
        String token = "";
        for (int i = 0; i <= siez; i++){
            int idx = (int) ((Math.random() * (0 - tokenArray.length)) + tokenArray.length);
            token += tokenArray[idx];
        }
        return token;
    }
}
