/**
 * Copyright (C), 2015-2020, 京东
 * FileName: HttpEndpointRouter
 * Author:   huangdan6
 * Date:     2020/11/3 下午2:14
 * Description: 路由器
 */
package Week_05.gateway.router;


import org.springframework.stereotype.Component;

import java.util.List;

/**
 *
 * 路由器
 *
 * @author huangdan6
 * @date 2020/11/03 14:14
 * @since 1.0.0
 */
@Component
public class HttpEndpointRouter {
    public String route(List<String> endpoints){
        if (endpoints == null || endpoints.size() == 0) {
            return null;
        }
        int number = (int)(Math.random()*endpoints.size());
        System.out.println("route"+number +","+ endpoints.get(number));
        return endpoints.get(number);
    }
}
