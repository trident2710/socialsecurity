/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inria.socialsecurity.constants;

/**
 *Represents the risk source.
 * @see Documentation page 18
 * @author adychka
 */
public enum RiskSource {
    A1("A1"), //user friends 
    A2("A2"), //friends of friends
    A3("A3"), // friends of friends of friends
    A4("A4"); // strangers
    
    private String value;
    
    private RiskSource(String value){
        this.value = value;
    }
}