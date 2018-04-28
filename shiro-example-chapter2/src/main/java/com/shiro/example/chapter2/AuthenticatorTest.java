package com.shiro.example.chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniFactorySupport;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.List;

public class AuthenticatorTest {
    private void login(String file){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(file);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
        try{
          subject.login(token);
        }catch(UnknownAccountException e){
            e.printStackTrace();
        }
    }
    //认证策略:AllSuccessfulStrategy
    //只有当所有的认证都成功后才表示认证成功,只要有一个失败就是失败.
    //本方法测试所有认证都成功
    @Test
    public void testAllSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-all-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //返回所有的认证信息
        PrincipalCollection principals = subject.getPrincipals();
        if (principals!=null){
           List<String> list = principals.asList();
            for (String principal:  list) {
                System.out.println(principal);
            }
        }
    }
    //认证策略:AllSuccessfulStrategy
    //只有当所有的认证都成功后才表示认证成功,只要有一个失败就是失败.
    //本方法测试认证失败
    @Test
    public void testAllSuccessfulStrategyWithFail(){
        login("classpath:shiro-authenticator-all-fail.ini");
        Subject subject = SecurityUtils.getSubject();
        //认证失败,无法返回principal
        PrincipalCollection principals = subject.getPrincipals();
        if (principals!=null){
            List<String> list = principals.asList();
            for (String principal:  list) {
                System.out.println(principal);
            }
        }
    }
    //认证策略:AtLeastOneSuccessfulStrategy
    //只要有一个认证成功就表示成功了
    @Test
    public void testAtLeastOneSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-atLeastOne-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //返回所有认证成功的信息
        PrincipalCollection principals = subject.getPrincipals();
        if (principals!=null){
            List<String> list = principals.asList();
            for (String principal:  list) {
                System.out.println(principal);
            }
        }
    }
    //认证策略:FirstSuccessfulStrategy
    //只要有一个认证成功就表示成功了
    @Test
    public void testFirstOneSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-first-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //只返回第一个认证成功的信息  TODO:为什么????
        PrincipalCollection principals = subject.getPrincipals();
        if (principals!=null){
            List<String> list = principals.asList();
            for (String principal:  list) {
                System.out.println(principal);
            }
        }
    }
    //自定义认证策略:AtLeastTwoSAuthenticationtrategy.继承AbstractAuthenticationStrategy抽象类
    //至少有两个认证成功,才算成功
    @Test
    public void testAtLeastTwoStrategyWithSuccess(){
        login("classpath:shiro-authenticator-atLeastTwo-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //返回所有认证成功的信息
        PrincipalCollection principals = subject.getPrincipals();
        if (principals!=null){
            List<String> list = principals.asList();
            for (String principal:  list) {
                System.out.println(principal);
            }
        }
    }
    //自定义认证策略:OnlyOneAuthenticationStrategy.继承AbstractAuthenticationStrategy抽象类
    //只有一个认证成功,才算成功.多于一个或者少于一个都不行
    @Test
    public void testOnlyOneStrategyWithSuccess(){
        login("classpath:shiro-authenticator-onlyone-success.ini");
        Subject subject = SecurityUtils.getSubject();
        //返回所有认证成功的信息
        PrincipalCollection principals = subject.getPrincipals();
        if (principals!=null){
            List<String> list = principals.asList();
            for (String principal:  list) {
                System.out.println(principal);
            }
        }
    }
}
