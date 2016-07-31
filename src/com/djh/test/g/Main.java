package com.djh.test.g;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//		MyFactory.register(Test.class);
//		MyFactory.exe("say");
		System.out.println(new DecimalFormat("0.00").format(12));;
	}

}
