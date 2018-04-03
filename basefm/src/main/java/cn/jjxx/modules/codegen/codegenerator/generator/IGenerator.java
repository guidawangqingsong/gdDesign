package cn.jjxx.modules.codegen.codegenerator.generator;

import java.io.IOException;
import java.util.Map;

import cn.jjxx.modules.codegen.codegenerator.data.GeneratorInfo;
import cn.jjxx.modules.codegen.codegenerator.exception.GenerationException;

public interface IGenerator {
	void generate(GeneratorInfo generatorInfo,Map<String, Object> dataMap) throws IOException,
			GenerationException;
}
