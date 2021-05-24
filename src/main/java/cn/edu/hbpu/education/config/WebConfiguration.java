package cn.edu.hbpu.education.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer{
	
	public WebConfiguration() {
		System.out.println("初始化正常");
	}
	/*
	 * @Override public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 * registry.addResourceHandler("/pic/**").addResourceLocations(
	 * "file:D:\\uploadFile2\\"); }
	 */
	static final String ORIGINS[] = new String[] {"GET","POST","PUT","DELETE"};

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		/* WebMvcConfigurer.super.addCorsMappings(registry); */
		registry.addMapping("/**")//所有的当前站点的请求地址，都支持访问
				.allowedOriginPatterns("*")//所有的外部与都可以跨域访问，如果localhost则很难配置，因为
				.allowCredentials(true)//是否支持跨域用户凭证
				.allowedMethods(ORIGINS)//当前站点支持的跨域请求类型是什么
				.maxAge(3600);
	}
}
