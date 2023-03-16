package br.com.io.booksstore.books.application.utils;

import java.util.UUID;

public class GenerateUUID {

    public static String getUUID(){
        return UUID.randomUUID().toString();
    }

}
