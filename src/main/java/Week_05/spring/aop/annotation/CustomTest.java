/**
 * Copyright (C), 2015-2020, 京东
 * FileName: CustomTest
 * Author:   huangdan6
 * Date:     2020/11/13 下午4:27
 * Description: 测试
 */
package Week_05.spring.aop.annotation;


import Week_05.spring.aop.ISchool;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * 测试
 *
 * @author huangdan6
 * @date 2020/11/13 16:27
 * @since 1.0.0
 */
@ComponentScan("Week_05.spring.aop")
public class CustomTest {
//    @Autowired
//    private ISchool school;
//
//    @Test
//    public void getSchoolName(){
//        school.getSchoolName("");
//    }
    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        ISchool school = context.getBean(ISchool.class);
        System.out.println(school.getSchoolName(""));
        System.out.println("ISchool接口的对象AOP代理后的实际类型："+school.getClass());
    }
}
