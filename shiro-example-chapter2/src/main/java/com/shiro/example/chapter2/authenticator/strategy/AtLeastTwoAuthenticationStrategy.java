package com.shiro.example.chapter2.authenticator.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.util.CollectionUtils;
// TODO:AbstractAuthenticationStrategy中的四个方法是什么作用.它的子类分别重写了一些方法,是为了什么
public class AtLeastTwoAuthenticationStrategy extends AbstractAuthenticationStrategy {
    @Override
  public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo info) throws AuthenticationException{
        if (info == null || CollectionUtils.isEmpty(info.getPrincipals()) || info.getPrincipals().getRealmNames().size() < 2) {
            throw new AuthenticationException("Authentication token of type [" + token.getClass() + "] " +
                    "could not be authenticated by any configured realms.  Please ensure that at least two realm can " +
                    "authenticate these tokens.");
        }
        return info;
  }
}
