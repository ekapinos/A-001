package local.kapinos.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.AnnotationJmxAttributeSource;
import org.springframework.jmx.export.assembler.InterfaceBasedMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MetadataMBeanInfoAssembler;
import org.springframework.jmx.export.assembler.MethodNameBasedMBeanInfoAssembler;

import local.kapinos.beans._00_BeansMarkerInterface;
import local.kapinos.beans._01_ExposingByMethodNamesBean;
import local.kapinos.beans._02_ExposingByInterfaceBean;
import local.kapinos.beans._03_ExposingByAnnotationsBean;
import local.kapinos.common.WaitAnyKeyBean;

@Configuration
@ComponentScan(basePackageClasses = _00_BeansMarkerInterface.class)
public class SpringConfiguration {

	@Bean
	public MBeanExporter methodNameMbeanExporter(_01_ExposingByMethodNamesBean myBean) {
		MethodNameBasedMBeanInfoAssembler assembler = new MethodNameBasedMBeanInfoAssembler();
		assembler.setManagedMethods(new String[] { "getIntValue", "setIntValue" });

		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_01_ExposingByMethodNamesBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(assembler);
		return exporter;
	}

	@Bean
	public MBeanExporter interfaceMbeanExporter(_02_ExposingByInterfaceBean myBean) {
		InterfaceBasedMBeanInfoAssembler assembler = new InterfaceBasedMBeanInfoAssembler();
		assembler.setManagedInterfaces(new Class<?>[] { _02_ExposingByInterfaceBean.class });
 
		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_02_ExposingByInterfaceBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(assembler);
		return exporter;
	}
	
	@Bean
	public MBeanExporter annotationMbeanExporter(_03_ExposingByAnnotationsBean myBean) {
		MetadataMBeanInfoAssembler assembler = new MetadataMBeanInfoAssembler();
		assembler.setAttributeSource(new AnnotationJmxAttributeSource());

		MBeanExporter exporter = new MBeanExporter();
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("myFolder:name=_03_ExposingByAnnotationsBean", myBean);
		exporter.setBeans(beans);
		exporter.setAssembler(assembler);
		return exporter;
	}
	
	@Bean
	Object waitAnyKeyBean() {
		return new WaitAnyKeyBean();
	}
}
