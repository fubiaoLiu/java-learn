package com.xiaoliu.learn.start;

/**
 * @description:
 * @author: FuBiaoLiu
 * @date: 2019/11/13
 */
public class SpringApplication {
	public static void run() {
		/*AnnotationConfigWebApplicationContext acw
				= new AnnotationConfigWebApplicationContext();
		acw.register(ApplicationConfig.class);
		acw.refresh();

		DispatcherServlet dispatcherServlet = new DispatcherServlet(acw);

		Tomcat tomcat = new Tomcat();
		tomcat.setPort(6666);

		File base = new File(System.getProperty("java.io.tmpdir"));
		System.out.println(System.getProperty("java.io.tmpdir") + ":" + base.getAbsolutePath());
		Context rootContext = tomcat.addContext("/", base.getAbsolutePath());
		Tomcat.addServlet(rootContext, "xiaoliu", dispatcherServlet)
				.setLoadOnStartup(1);
		try {
			rootContext.addServletMappingDecoded(UDecoder.URLDecode("/", B2CConverter.getCharset("UTF-8")), "xiaoliu");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
//		tomcat.addWebapp("/","");
//		tomcat.addContext("/","");
//		tomcat.addServlet(servlet);
		try {
			tomcat.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
		tomcat.getServer().await();*/
	}
}
