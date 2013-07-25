/**
 * 
 */
package com.project.converter;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.constant.Global;
import com.project.constant.WebConstant;
import com.project.domain.Privilege;
import com.project.domain.PrivilegePK;

/**
 * @author dingjs
 */
@SuppressWarnings("unchecked")
public class PrivilegeConverter extends StrutsTypeConverter {
	private Logger logger = LoggerFactory.getLogger(PrivilegeConverter.class);
	
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		logger.info("进入PrivilegeConverter转换器");
		logger.info("字符串------>对象");
		String privilegePKStr =  values[0];
		String[] privilegeStrs = privilegePKStr.split(WebConstant.WEB_LINK_CHAR);
		PrivilegePK privilegePK = null;
		if(privilegeStrs.length == 1){
			if(WebConstant.WEB_SELECT_DEFALUT.equals(privilegeStrs[0]))
				return null;
			else  privilegePK = new PrivilegePK(privilegeStrs[0],Global.STRING_EMPTY);
		}
		else privilegePK = new PrivilegePK(privilegeStrs[0],privilegeStrs[1]);
		return new Privilege(privilegePK);
	}

	@Override
	public String convertToString(Map context, Object o) {
		logger.info("进入PrivilegeConverter转换器");
		logger.info("对象------>字符串");
		Privilege privilege =  (Privilege) o;
		return privilege.getPrivilegePK().getModule() + 
			WebConstant.WEB_LINK_CHAR + privilege.getPrivilegePK().getPrivilege();
	}



	/*@Override
	public Object convertValue(Map<String, Object> context, Object target, Member member,
            String propertyName, Object value, Class toType) {
		logger.info("进入权限主键转换器");
		if(toType ==  PrivilegePK.class) {
		logger.info("字符串------>对象");
			String privilegePKStr =  ((String[])value)[0];
			String[] privilegeStrs = privilegePKStr.split(":");
			PrivilegePK privilegePK = new PrivilegePK(privilegeStrs[0],privilegeStrs[1]);
			return privilegePK;
		}else {
			logger.info("对象------>字符串");
			PrivilegePK privilegePK =  (PrivilegePK) value;
			return privilegePK.toString();
		}
    }*/
}
