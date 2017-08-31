package com.hsy.ssm.databaseUtil;

public class DatasourceNameSetter {
	
	private static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
	
	public static void setDatasourceName(String datasourceName){
		threadLocal.set(datasourceName);
	}
	
	
	public static String getDatasourceName( ){
		return threadLocal.get();
	}
	
	
	
	

}
