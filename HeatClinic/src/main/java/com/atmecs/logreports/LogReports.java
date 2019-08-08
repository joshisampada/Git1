package com.atmecs.logreports;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import com.atmecs.constants.ProjectPathConstants;

import bsh.org.objectweb.asm.Constants;

public class LogReports
{
Logger logger = null;
	
	public LogReports() {
		getlogger();
		logger = Logger.getLogger(LogReports.class.getName());
	}
	
	public void getlogger() {
		PropertyConfigurator.configure(ProjectPathConstants.log4j);
	}

	public void info(String message){
		logger.info(message);
	}
	
	public void debug(String message){
		
	}
}
