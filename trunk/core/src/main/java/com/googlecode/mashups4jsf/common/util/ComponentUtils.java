/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.googlecode.mashups4jsf.common.util;

import java.io.IOException;

import javax.faces.component.ActionSource;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UICommand;
import javax.faces.component.UIComponent;
import javax.faces.component.UIForm;
import javax.faces.component.ValueHolder;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;
import javax.faces.convert.Converter;
import javax.faces.el.MethodBinding;
import javax.faces.el.ValueBinding;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.webapp.UIComponentTag;

public class ComponentUtils {

	public static String getStringValue(FacesContext context, ValueBinding vb) {
		return vb.getValue(context).toString();
	}

	public static boolean getBooleanValue(FacesContext context, ValueBinding vb) {
		return ((Boolean) vb.getValue(context)).booleanValue();
	}

	public static int getIntegerValue(FacesContext context, ValueBinding vb) {
		return ((Integer) vb.getValue(context)).intValue();
	}

	public static void setStringProperty(FacesContext context, UIComponent component, String attributeName, String value) {
		if(value != null) {
			if (isValueReference(value)) {
				ValueBinding vb = context.getApplication().createValueBinding(value);
				component.setValueBinding(attributeName, vb);
			} else {
				component.getAttributes().put(attributeName, value);
			}
		}
	}

	public static void setIntegerProperty(FacesContext context, UIComponent component, String attributeName, String value) {
		if(value != null) {
			if (isValueReference(value)) {
				ValueBinding vb = context.getApplication().createValueBinding(value);
				component.setValueBinding(attributeName, vb);
			} else {
				component.getAttributes().put(attributeName, Integer.valueOf(value));
			}
		}
	}

	public static void setDoubleProperty(FacesContext context, UIComponent component, String attributeName, String value) {
		if(value != null) {
			if (isValueReference(value)) {
				ValueBinding vb = context.getApplication().createValueBinding(value);
				component.setValueBinding(attributeName, vb);
			} else {
				component.getAttributes().put(attributeName, Double.valueOf(value));
			}
		}
	}

	public static void setBooleanProperty(FacesContext context, UIComponent component, String attributeName, String value) {
		if(value != null) {
			if (isValueReference(value)) {
				ValueBinding vb = context.getApplication().createValueBinding(value);
				component.setValueBinding(attributeName, vb);
			} else {
				component.getAttributes().put(attributeName, Boolean.valueOf(value));
			}
		}
	}

	public static void setValueProperty(FacesContext context, UIComponent component, String value) {
		if(value != null) {
			if (isValueReference(value)) {
				ValueBinding vb = context.getApplication().createValueBinding(value);
				component.setValueBinding("value", vb);
			} else {
				if (component instanceof ActionSource)
					((UICommand)component).setValue(value);
				else
					((ValueHolder)component).setValue(value);
			}
		}
	}

	public static void setConverterProperty(FacesContext context, UIComponent component, String converter) {
		if(converter != null) {
			if (isValueReference(converter)) {
				ValueBinding vb = context.getApplication().createValueBinding(converter);
				component.setValueBinding("converter", vb);
			} else {
				Converter _converter = context.getApplication().createConverter(converter);
				((ValueHolder)component).setConverter(_converter);
			}
		}
	}

	public static void setValidatorProperty(FacesContext context, UIComponent component, String validator) {
		if(validator != null) {
			if (isValueReference(validator)) {
				Class params [] = { FacesContext.class, UIComponent.class, Object.class };
				MethodBinding mb = context.getApplication().createMethodBinding(validator, params);
				((EditableValueHolder)component).setValidator(mb);
			} else {
				throw new IllegalArgumentException("Component with id:" + component.getId() + " has an invalid validator binding");
			}
		}
	}

	public static void setValueChangeListenerProperty(FacesContext context, UIComponent component, String valueChangeListener) {
		if(valueChangeListener != null) {
			if (isValueReference(valueChangeListener)) {
				Class params [] = { ValueChangeEvent.class };
				MethodBinding mb = context.getApplication().createMethodBinding(valueChangeListener, params);
				((EditableValueHolder)component).setValueChangeListener(mb);
			} else {
				throw new IllegalArgumentException("Component with id:" + component.getId() + " has an invalid validator");
			}
		}
	}

	public static void setRequiredProperty(FacesContext context, UIComponent component, String required) {
		if(required != null) {
			if (isValueReference(required)) {
				ValueBinding vb = context.getApplication().createValueBinding(required);
				component.setValueBinding("required", vb);
			} else {
				((EditableValueHolder)component).setRequired(Boolean.valueOf(required).booleanValue());
			}
		}
	}

	public static void setImmediateProperty(FacesContext context, UIComponent component, String immediate) {
		if(immediate != null) {
			if (isValueReference(immediate)) {
				ValueBinding vb = context.getApplication().createValueBinding(immediate);
				component.setValueBinding("immediate", vb);
			} else {
				((EditableValueHolder)component).setImmediate(Boolean.valueOf(immediate).booleanValue());
			}
		}
	}

	public static void setMethodBindingProperty(FacesContext context, UIComponent component, String attributeName, String mbValue) {
		if(mbValue != null) {
			if (isValueReference(mbValue)) {
				Class params [] = { String.class };
				MethodBinding mb = context.getApplication().createMethodBinding(mbValue, params);
				component.getAttributes().put(attributeName, mb);
			} else {
				throw new IllegalArgumentException("Component with id:" + component.getId() + " has an invalid method binding");
			}
		}
	}

	public static void setValueBindingProperty(FacesContext context, UIComponent component, String attributeName, String vbValue) {
		if(vbValue != null) {
			if (isValueReference(vbValue)) {
				ValueBinding vb = context.getApplication().createValueBinding(vbValue);
				component.getAttributes().put(attributeName, vb);
			} else {
				throw new IllegalArgumentException("Component with id:" + component.getId() + " has an invalid value binding");
			}
		}
	}

	public static void setActionProperty(FacesContext context, UIComponent component, String action) {
		if (action != null) {
			if (!(component instanceof ActionSource))
				throw new IllegalArgumentException("Component " + component.getClientId(context) + " is not an ActionSource");

			MethodBinding mb;
			if (isValueReference(action)) {
				mb = context.getApplication().createMethodBinding(action, null);
				((ActionSource) component).setAction(mb);
			}
			else {
				component.getAttributes().put("action", action);
			}
		}
	}

	public static void setActionListenerProperty(FacesContext context, UIComponent component, String actionListener) {
		if (actionListener != null) {
			if (!(component instanceof ActionSource)) {
				throw new IllegalArgumentException("Component " + component.getClientId(context) + " is not an ActionSource");
			}
			if (isValueReference(actionListener)) {
				MethodBinding mb = context.getApplication().createMethodBinding(actionListener, new Class[]{ActionEvent.class});
				((ActionSource) component).setActionListener(mb);
			}
		}
	}

	public static boolean isValueReference(String v) {
		return UIComponentTag.isValueReference(v);
	}

	public static UIComponent findComponentById(FacesContext context, UIComponent root, String id) {
		UIComponent component = null;

		for (int i = 0; i < root.getChildCount() && component == null; i++) {
			UIComponent child = (UIComponent) root.getChildren().get(i);
			component = findComponentById(context, child, id);
		}

		if (root.getId() != null) {
			if (component == null && root.getId().equals(id)) {
				component = root;
			}
		}
		return component;
	}

	/**
	 * Algorithm works as follows;
	 * - If the component is not a value holder, an exception is thrown
	 * - If it's an input component, submitted value is checked first since it'd be the value to be used in case validation erros
	 * terminates jsf lifecycle
	 * - Finally the value of the component is retrieved from backing bean and if there's a converter, converted value is returned
	 *
	 * @param context			FacesContext instance
	 * @param component			UIComponent instance whose value will be returned
	 * @return					Value of the component
	 */
	public static Object getValueToRender(FacesContext context, UIComponent component) {
		if (!(component instanceof ValueHolder))
			throw new IllegalArgumentException("Component : " + component.getId() + "is not a ValueHolder");

		if (component instanceof EditableValueHolder) {
			Object submittedValue = ((EditableValueHolder) component).getSubmittedValue();
			if (submittedValue != null) {
				return submittedValue;
			}
		}

		Object value = ((ValueHolder) component).getValue();
		if(((ValueHolder)component).getConverter() != null)
			return ((ValueHolder)component).getConverter().getAsString(context, component, value);
		else
			return value;
	}

	public static boolean isValueEmpty(String value) {
		if (value == null || "".equals(value))
			return true;

		return false;
	}

	public static String getMethodExpression(MethodBinding mb) {
		String methodExpression = mb.getExpressionString();

		return methodExpression.substring(2, methodExpression.length() - 1);
	}

	public static String getValueExpression(ValueBinding vb) {
		String valueExpression = vb.getExpressionString();

		return valueExpression.substring(2, valueExpression.length() - 1);
	}
	
	public static ValueBinding getValueBinding(FacesContext context, String value) {
		return context.getApplication().createValueBinding(value);		
	}

	/**
	 * Traverses the component tree beginning from the given component instance, stops when finds the enclosing form
	 *
	 * @param context
	 * @param component
	 */
	public static UIComponent findParentForm(FacesContext context, UIComponent component) {
		UIComponent parent = component;

		while(parent != null && (!(parent instanceof UIForm))) {
			parent = parent.getParent();
		}
        
        if (parent == null) {
            throw new RuntimeException("cannot find a standard parent form for gmaps4jsf component");
        }

		return parent;
	}
	
    public static String getComponentArrayVariable(UIComponent component) {
        return "component_" + component.getId() + "_Array";
    }        
    
	public static void endEncodingBrowserCompatabilityChecking(
			FacesContext facesContext, UIComponent component,
			ResponseWriter writer) throws IOException {
		
		writer.write("}");
	}	
}