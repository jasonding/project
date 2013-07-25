package com.project.util.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.domain.Privilege;
import com.project.domain.mapping.Role;
import com.project.enumeration.ResourceType;
import com.project.util.CollectionToMapUtils;
import com.project.util.NullUtil;


public class PrivilegeTreeUtil {
	public static  List<Privilege> getPrivilegeTreeList(List<Privilege>  topLevelList) {
		List<Privilege> privilegeList = new ArrayList<Privilege>();
		if(topLevelList == null || topLevelList.isEmpty()) return privilegeList;
		for (Privilege privilege : topLevelList) {
			getAll(privilege ,"", privilegeList);
		}
		return privilegeList;
	}
	
	private static void getAll(Privilege privilege,String level,List<Privilege> privilegeList) {
		Privilege newPrivilege = new Privilege();
		newPrivilege.setPrivilegePK(privilege.getPrivilegePK());
		newPrivilege.setName(level + privilege.getName());
		privilegeList.add(newPrivilege);
		
		for(Privilege childPrivilege : privilege.getChildPrivilegeSet()) {
			getAll(childPrivilege ,level + "--", privilegeList);
		}
	}
	
	public static Privilege getTopLevel(Privilege privilege) {
		if(NullUtil.isNull(privilege.getParentPrivilege())) {
			Privilege newPrivilege = new Privilege();
			newPrivilege.setPrivilegePK(privilege.getPrivilegePK());
			newPrivilege.setName(privilege.getName());
			newPrivilege.setOrderView(privilege.getOrderView());
			return newPrivilege;
		}else {
			return getTopLevel(privilege.getParentPrivilege());
		}
	}
	
	public static List<Privilege> getPrivilegeTreeMenuList(Set<Role> roleSet) {
		if(NullUtil.isNullOrEmpty(roleSet)) return Collections.emptyList();
		Map<Privilege,Privilege> privilegeMap = new LinkedHashMap<Privilege, Privilege>();
		for (Role role : roleSet) {
			if(NullUtil.isNull(role)) continue;
			Set<Privilege> privilegeSet = role.getPrivilegeSet();
			
			for (Privilege privilege : privilegeSet) {
				if(!ResourceType.LINK.equals(privilege.getPrivilegeType())) continue; 
				Privilege topLevel = PrivilegeTreeUtil.getTopLevel(privilege);
				if(privilege.equals(topLevel)) {
					CollectionToMapUtils.getKVMap(topLevel, topLevel, privilegeMap);
				}else {
					if(privilegeMap.containsKey(topLevel)) {
						privilegeMap.get(topLevel).addChildPrivilege(privilege);
					}else {
						topLevel.addChildPrivilege(privilege);
						CollectionToMapUtils.getKVMap(topLevel, topLevel, privilegeMap);
					}
				}
			}
		}
		//按照orderview字段进行排序
		ArrayList<Privilege> topPrivilegeList = new ArrayList<Privilege>(privilegeMap.values());
		Collections.sort(topPrivilegeList);
		for (Privilege privilege : topPrivilegeList) {
			ArrayList<Privilege> childPrivilegelist = new ArrayList<Privilege>(privilege.getChildPrivilegeSet());
			Collections.sort(childPrivilegelist);
			privilege.setChildPrivilegeSet(new LinkedHashSet<Privilege>(childPrivilegelist));
		}
		return topPrivilegeList;
	}
}
