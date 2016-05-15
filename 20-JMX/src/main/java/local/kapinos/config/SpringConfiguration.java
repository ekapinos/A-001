package local.kapinos.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;

import local.kapinos.beans._00_BeansMarkerInterface;
import local.kapinos.beans._01_ExposingByMethodNamesBean;
import local.kapinos.beans._02_ExposingByInterfaceBean;
import local.kapinos.common.WaitAnyKeyBean;

@Configuration
@ComponentScan(basePackageClasses = _00_BeansMarkerInterface.class)
public class SpringConfiguration {

	@Bean
	public MBeanExporter methodNameMbeanExporter(_01_ExposingByMethodNamesBean myBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_01_myBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(methodNameAssembler());
		return exporter;
	}
	@Bean
	public MethodNameBasedMBeanInfoAssembler methodNameAssembler() {
		MethodNameBasedMBeanInfoAssembler assembler = new MethodNameBasedMBeanInfoAssembler();
		assembler.setManagedMethods(new String[] { "getIntValue", "setIntValue" });
		return assembler;
	}

	@Bean
	public MBeanExporter interfaceMbeanExporter(_02_ExposingByInterfaceBean myBean) {
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_02_myBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(interfaceAssembler());
		return exporter;
	}
	@Bean
	public InterfaceBasedMBeanInfoAssembler interfaceAssembler() {
		InterfaceBasedMBeanInfoAssembler assembler = new InterfaceBasedMBeanInfoAssembler();
		assembler.setManagedInterfaces(new Class<?>[] { _02_ExposingByInterfaceBean.class });
		return assembler;
	}
	
	@Bean
	Object waitAnyKeyBean() {
		return new WaitAnyKeyBean();
	}
}
