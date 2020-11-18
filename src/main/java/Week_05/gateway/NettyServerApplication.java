package Week_05.gateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
public class NettyServerApplication extends SpringBootServletInitializer {


    /**
     * main
     * @param args
     */
    public static void main(String[] args) {
        configureApplication(new SpringApplicationBuilder()).run(args);
    }
    /**
     * configure
     *
     * @author bjmowen
     * @date 2020/3/14 8:31 下午
     * @param builder
     * @return org.springframework.boot.builder.SpringApplicationBuilder
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return configureApplication(builder);
    }
    /**
     * configureApplication
     *
     * @author bjmowen
     * @date 2020/3/14 8:31 下午
     * @param builder
     * @return org.springframework.boot.builder.SpringApplicationBuilder
     */
    private static SpringApplicationBuilder configureApplication(SpringApplicationBuilder builder) {
        return builder.sources(NettyServerApplication.class);
    }
}
