/**
 * 
 */
package com.alliancetecnologia.converter;

import java.time.temporal.Temporal;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.alliancetecnologia.utils.DateUtils;

/**
 * @author Lucas P. Soares (lucasperes20@gmail.com)
 * @date 19 de mai de 2019
 *
 */
@FacesConverter(forClass = Temporal.class, value = "dateConverter")
public class DateConverter implements Converter {

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsObject(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.String)
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return DateUtils.convertDate(value);
	}

	/* (non-Javadoc)
	 * @see javax.faces.convert.Converter#getAsString(javax.faces.context.FacesContext, javax.faces.component.UIComponent, java.lang.Object)
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return DateUtils.formatterDate(value);
	}
	
}
