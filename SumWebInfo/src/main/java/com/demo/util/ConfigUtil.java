package com.demo.util;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class ConfigUtil {
	//public static final String FILEPATH ="../inMoney.properties";
	//public static final String FILEPATH =ConfigUtil.class.getClassLoader().getResource("inMoney.properties").toString().replace("file:", "");
	//public static final String FILEPATH ="./src/inMoney.properties";
	//public static final String KEYT = "tel";
	private static final Log log = LogFactory
			.getLog(ConfigUtil.class);
	/**
	 * <p>
	 * 根据KEY读取配置文件内容
	 * <p>
	 * 
	 * @param key
	 *            :key
	 * @author cy
	 * */
	public static String redata(String key,String filePath) {
		log.info("配置文件路径"+filePath);
		String val = "";
		Properties pro = new Properties();
		InputStream in;
		try {
			in = new BufferedInputStream(new FileInputStream(filePath));
			pro.load(in);
			in.close();
			val = pro.getProperty(key).trim();
		} catch (FileNotFoundException e) {
			System.out.println("配置文件打开失败请检查路径是否正确");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("配置文件修改失败");
			e.printStackTrace();
		}
		log.info("key::::::"+key+"   val:::::::::::::"+val);
		return val;
	}
	public static void main(String[] args) {
	//	redata("currency", FILEPATH);
	}
}
