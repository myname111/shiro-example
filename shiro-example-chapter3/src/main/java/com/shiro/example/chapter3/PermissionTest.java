package com.shiro.example.chapter3;

import org.junit.Test;

public class PermissionTest extends BaseTest{
    @Test
    public void isPermitted(){
        login("classpath:shiro-permission.ini","zhang","123");

        boolean permitted = subject().isPermitted("user:create");
        System.out.println("是否拥有某权限:"+permitted);

        boolean[] permitted1 = subject().isPermitted("user:create", "user:delete");
        for (boolean bool:permitted1) {
            System.out.println("ispermitted:"+bool);
        }

        boolean permittedAll = subject().isPermittedAll("user:create","user:save");
        System.out.println("是否拥有所有的权限:"+permittedAll);

    }

    @Test
    public void checkPermission(){
        login("classpath:shiro-permission.ini","zhang","123");
        //检查是否包含某权限,如果包含则无返回值,否则抛出异常
        subject().checkPermission("user:delete");
        subject().checkPermissions("user:crate","user:save");
    }
}
