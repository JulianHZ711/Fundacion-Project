package com.project.springboot.app.fundacion_project.fundacion_project.security;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class SimpleGrantedAuthorityJsonCreator {

    //Changing the parameter for the JSON so it knows what to search in the JSON using the "authority" name
    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority") String role){

    }
}
