/**
 * 
 */
package com.project.converter;

import java.lang.reflect.Member;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;
import com.project.constant.Global;
import com.project.constant.WebConstant;
import com.project.domain.Privilege;
import com.project.domain.PrivilegePK;

/**
 * @author dingjs
 */
@SuppressWarnings("unchecked")
public class PrivilegeSetConverter extends DefaultTypeConverter {
	private Logger logger = LoggerFactory.getLogger(PrivilegeSetConverter.class);
	
	@Override
	public Object convertValue(Map<String, Object> context, Object target, Member member,
            String propertyName, Object value, Class toType) {
		logger.info("进入PrivilegeSetConverter权限集合转换器");
		if(toType ==  Set.class) {
		logger.debug("字符串------>对象");
			Set<Privilege> privilegeSet = new HashSet<Privilege>();
			String[] values = (String[]) value;
			for(int i=0; i<values.length; i++) {
				String privilegeStr = values[i];
				String[] privilegeStrs = privilegeStr.split(WebConstant.WEB_LINK_CHAR);
				PrivilegePK privilegePK = null;
				if(privilegeStrs.length == 1) privilegePK = new PrivilegePK(privilegeStrs[0],Global.STRING_EMPTY);
				else privilegePK = new PrivilegePK(privilegeStrs[0],privilegeStrs[1]);
				privilegeSet.add(new Privilege(privilegePK));
			}
			return privilegeSet;
		}else {
			logger.debug("对象------>字符串");
			Set<Privilege> privilegeSet = (Set<Privilege>) value;
			Collection<String> returnValue = new HashSet<String>();
			for(Privilege privilege : privilegeSet ) {
				returnValue.add(privilege.getPrivilegePK().getModule() + 
						WebConstant.WEB_LINK_CHAR + privilege.getPrivilegePK().getPrivilege());
			}
			String[] strs = new String[returnValue.size()];
			return returnValue.toArray(strs);
		}
    }
}
