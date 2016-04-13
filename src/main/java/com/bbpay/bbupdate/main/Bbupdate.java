package com.bbpay.bbupdate.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.dubbo.governance.service.ProviderService;
import com.alibaba.dubbo.registry.common.domain.Provider;


public class Bbupdate {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String ProjectName = null;
		String IPAddr = null;
		String Flag = null;
		if (args.length != 3) {
			System.out.println("程序使用错误，请按下规则使用。");
			System.out.println("程序名        args[1]    args[2]     args[3]");
			System.out.println("程序名        项目名称        IP地址        enable/disable");
			return;
		}
		ProjectName = args[0];
		IPAddr = args[1];
		Flag = args[2];
		
		System.out.println("--------------------------------");
		System.out.println("项目名称：" + ProjectName);
		System.out.println("IP地址：" + IPAddr);
		System.out.println("Flag：" + Flag);
		System.out.println("--------------------------------");
		
//		先定义ProviderService的 实例变量ps;
		ProviderService ps;
		
//		利用spring加载配置文件
//		配置文件为spring/dubbo-admin.xml
		ClassPathXmlApplicationContext ac=new ClassPathXmlApplicationContext(new String[] { "classpath:dubbo-admin.xml"});
		ac.start();
//		把从spring容器获得的实例赋值给ps;
		ps = (ProviderService) ac.getBean("providerService");

		System.out.println("--------------------------------------------------");
//		List<Provider> lsp = ps.findAll();
//		String IPAddr = "192.168.15.8";
//		String ProjectName = "bbpay-api";
		
//		根据项目名来查找该项目所提供的服务
		List<Provider> lsp = ps.findByApplication(ProjectName);

//		把查找出来的服务由一个List<Provider>泛型
//		并根据地址查找一遍，把地址为我们要的服务
//		转换成 一个ArrayList <Provider>
		List<Provider> nlsp = new ArrayList<Provider>();
		Iterator ite = lsp.iterator();
		while(ite.hasNext()) {
			Provider element = (Provider) ite.next();
			System.out.println(element.getService() + "       " + element.getId() + "       " + element.getAddress());
			String[] sa = element.getAddress().split(":");
			System.out.println("sa[0]:" + sa[0]);
			if(IPAddr.equals(sa[0])) {
				nlsp.add(element);
			}
			
		}
		
		System.out.println("--------------------------------------------------");
		
		if(Flag.equals("disable")) {
			System.out.println("--------执行关闭--------");
			Iterator nite = nlsp.iterator();
			while(nite.hasNext()) {
				Provider element = (Provider) nite.next();
				System.out.println(element.getService() + "       " + element.getId() + "       " + element.getAddress());
				System.out.println("开始关闭：" + element.getId());
				ps.disableProvider(Long.valueOf(element.getId()));
				System.out.println("关闭完成：" + element.getId());
			}
		} else if(Flag.equals("enable")) {
			System.out.println("--------执行开启--------");
			Iterator nite = nlsp.iterator();
			while(nite.hasNext()) {
				Provider element = (Provider) nite.next();
				System.out.println(element.getService() + "       " + element.getId() + "       " + element.getAddress());
				System.out.println("开始开启：" + element.getId());
				ps.enableProvider(Long.valueOf(element.getId()));
				System.out.println("开启完成：" + element.getId());
			}
		} else {
			System.out.println("--------输入错误--------");
			System.exit(1);
		}
		
	}

}
