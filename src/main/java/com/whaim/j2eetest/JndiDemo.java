package com.whaim.j2eetest;

import javax.ejb.Stateless;

@Stateless
public class JndiDemo {

    public String sayHello(){
        return "Hello jndi World!";
    }

}
