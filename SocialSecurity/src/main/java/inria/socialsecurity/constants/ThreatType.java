/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inria.socialsecurity.constants;

/**
 * Representation of the thret types.
 *
 * @see Documentation p.19
 * @author adychka
 */
public enum ThreatType {
    FE1,
    FE2;
    
    public String getValue() {
        return name();
    }

}
