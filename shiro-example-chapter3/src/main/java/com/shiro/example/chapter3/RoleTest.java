package com.shiro.example.chapter3;

import org.junit.Test;

import java.util.Arrays;

public class RoleTest extends BaseTest{
    //判断是否拥有某个/某些角色.返回boolean
    @Test
    public void hasRole(){
        login("classpath:shiro-role.ini","zhang","123");
        //hasRole判断是否拥有某个角色.有则返回true,反之返回false
        boolean role1 = subject().hasRole("role1");
        System.out.println("是否拥有某个角色:"+role1);
        //hasAllRoles判断是否拥有集合中所有的角色,如果都拥有则返回true,反之返回false
        boolean hasAllRoles = subject().hasAllRoles(Arrays.asList("role1", "role2", "role3"));
        System.out.println("是否拥有某些角色:"+hasAllRoles);
        //hasRoles,判断是否拥有集合中的角色.返回值是boolean的数组.有某角色则数组中响应元素就是true,反之则是false
        boolean[] booleans = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));
        for (boolean bool:booleans) {
            System.out.println("hasRoles:"+bool);
        }
    }
    //检查是否拥有角色,拥有则不返回任何内容,反之则抛出异常
    @Test
    public void checkRole(){
        login("classpath:shiro-role.ini","zhang","123");
        subject().checkRole("role1");
        subject().checkRoles("role1","role2");
    }
}
