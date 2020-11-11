package org.example.clone;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: czm, PC of Chenzhimei
 * @time: 2020/11/5 17:14
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Cloneable{
    private String city;
    private String street;

    public Address clone(){
        try {
            return (Address)super.clone();
        }catch (CloneNotSupportedException e){
            return null;
        }

    }
}
