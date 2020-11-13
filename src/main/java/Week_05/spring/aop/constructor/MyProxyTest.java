/**
 * Copyright (C), 2015-2020, 京东
 * FileName: MyProxyTest
 * Author:   huangdan6
 * Date:     2020/11/13 下午2:44
 * Description: 测试类
 */
package Week_05.spring.aop.constructor;


import Week_05.spring.aop.ISchool;
import Week_05.spring.aop.impl.SchoolImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 *
 * 测试类
 *
 * @author huangdan6
 * @date 2020/11/13 14:44
 * @since 1.0.0
 */
public class MyProxyTest {
    public static void main(String[] args) {
        ISchool school = new SchoolImpl();

        InvocationHandler handler = new MyProxy(school) ;

        ClassLoader loader = school.getClass().getClassLoader();
        Class[] interfaces = school.getClass().getInterfaces();
        ISchool iSchool = (ISchool) Proxy.newProxyInstance(loader, interfaces, handler);

        System.out.println("动态代理对象类型：" + school.getClass().getName());
        String name = iSchool.getSchoolName("北京大学");


    }
}
