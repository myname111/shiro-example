package com.shiro.example.chapter3;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

import java.security.Security;

public class BaseTest {
   public void login(String file,String username,String password){
       Factory<SecurityManager> factory  = new IniSecurityManagerFactory(file);
       SecurityManager securityManager = factory.getInstance();
       SecurityUtils.setSecurityManager(securityManager);
       Subject subject = SecurityUtils.getSubject();
       UsernamePasswordToken token = new UsernamePasswordToken(username,password);
       subject.login(token);
   }
   protected Subject subject(){
       return SecurityUtils.getSubject();
   }
   @After
   public  void tearDown(){
       ThreadContext.unbindSubject();
   }
}
