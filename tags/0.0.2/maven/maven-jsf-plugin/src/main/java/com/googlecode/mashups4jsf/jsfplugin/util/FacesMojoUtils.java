/*
 * Copyright 2007 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.googlecode.mashups4jsf.jsfplugin.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.googlecode.mashups4jsf.jsfplugin.digester.Attribute;
import com.googlecode.mashups4jsf.jsfplugin.digester.Component;

import org.apache.commons.lang.StringUtils;

/**
 * @author Latest modification by $Author: cagatay_civici $
 * @version $Revision: 465 $ $Date: 2007-06-21 15:33:27 +0300 (Thu, 21 Jun 2007) $
 */
public class FacesMojoUtils {

	public static Map wrapperMap;					//primitives and corresponding wrappers
	public static Map toPrimitiveMap;				//Method calls to converter wrappers to primitives

	static {
		wrapperMap = new HashMap();
		wrapperMap.put("int", "Integer");
		wrapperMap.put("boolean", "Boolean");

		toPrimitiveMap = new HashMap();
		toPrimitiveMap.put("int", ".intValue()");
		toPrimitiveMap.put("boolean", ".booleanValue()");
	}

	public static String getWrapperType(String type) {
		String wrapperType = (String) wrapperMap.get(type);

		if(StringUtils.isBlank(wrapperType))
			return type;					//if none found just return the same type
		else
			return wrapperType;
	}

	public static String getPrimitiveMethod(String type) {
		String toPrimitiveMethod = (String) toPrimitiveMap.get(type);

		if(StringUtils.isBlank(toPrimitiveMethod))
			return StringUtils.EMPTY;					//if none found just return the same type
		else
			return toPrimitiveMethod;
	}

	public static boolean shouldWrap(String attributeType) {
		return wrapperMap.containsKey(attributeType);
	}

	/**
	 * Calculates array size to be allocated when saving the state
	 *
	 * @param component
	 * @return Array size to be used for state saving
	 */
	public static int getStateAllocationSize(Component component) {
		int size = 0;

		for (Iterator iterator = component.getAttributes().iterator(); iterator.hasNext();) {
			Attribute attribute= (Attribute) iterator.next();

			if(!attribute.isIgnored())
				size++;
		}

		return size;
	}
}