package cn.jjxx.modules.codegen.codegenerator.utils.type;

import java.util.List;

import cn.jjxx.modules.codegen.codegenerator.xml.definition.Type;

public interface ITypeConvert {
	public Type getType(String type);

	public List<Type> getTypes();

	public List<String> getJavaTypes();

	public List<String> getFullTypes();

	public List<String> getDbTypes();
}
