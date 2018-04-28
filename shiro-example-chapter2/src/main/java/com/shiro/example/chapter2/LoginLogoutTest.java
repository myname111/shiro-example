package com.shiro.example.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class LoginLogoutTest {
    //测试登录
    @Test
    public void test(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("用户名或者密码错误");
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);


    }
    //单个Realm
    @Test
    public void singleRealmtest(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("用户名或者密码错误");
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
    }
    //多个Realm
    @Test
    public void multiRealmtest(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-multirealm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
        }catch (UnknownAccountException e){
            System.out.println("用户名或者密码错误");
        }
        boolean authenticated = subject.isAuthenticated();
        System.out.println(authenticated);
    }
}
