package com.shiro.example.chapter2.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm4 implements Realm {
    public String getName() {
        return "MyRealm3";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //token.getPrincipal()底层返回的是一个String
        String  principal =(String) token.getPrincipal();
        //String credentials = (String) token.getCredentials();
        //token.getCredentials()底层返回的是一个字节数组,不能使用强制类型转换,将其转换为字符串,所以使用new String的方式
        String credentials =  new String((char[]) token.getCredentials());
        if (!principal.equals("zhang")){
            throw new UnknownAccountException("用户名不正确");
        }
        if(!credentials.equals("123")){
            throw new IncorrectCredentialsException("密码不正确");
        }
        System.out.println("第四个Realm");
        return new SimpleAuthenticationInfo(principal+"haha",credentials,getName());
    }
}
