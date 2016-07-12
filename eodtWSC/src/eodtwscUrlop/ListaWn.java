/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eodtwscUrlop;

import eodtwsc.WnUrlop;
import java.util.List;

/**
 *
 * @author arti01
 */
public class ListaWn {
    public List<WnUrlop> wywolaj(){
        return new EodtUrlopyClient("http://localhost:8080/eodt_j8").getMojeWnLista("aa@aa.pl");
    }
    
}
