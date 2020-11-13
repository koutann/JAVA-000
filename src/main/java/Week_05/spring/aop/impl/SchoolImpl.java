/**
 * Copyright (C), 2015-2020, 京东
 * FileName: SchoolImpl
 * Author:   huangdan6
 * Date:     2020/11/12 下午11:07
 * Description: 校园实现类
 */
package Week_05.spring.aop.impl;


import Week_05.spring.aop.ISchool;
import Week_05.spring.aop.annotation.Custom;
import org.springframework.stereotype.Service;

/**
 *
 * 校园实现类
 *
 * @author huangdan6
 * @date 2020/11/12 23:07
 * @since 1.0.0
 */
@Service
public class SchoolImpl implements ISchool {
    @Override
    @Custom(value = "")
    public String getSchoolName(String name) {
        return "****" + name + "****";
    }
}
