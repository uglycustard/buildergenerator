package uk.co.buildergenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

// TODO: TDD me
class BuilderWriter {

    private final FileUtils fileUtils;

    BuilderWriter(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }
    
    void generateBuilderInteraface(File outputDirectory, String builderPackage) {
        File targetPackageDirectory = getTargetPackageDirectory(outputDirectory, builderPackage);
        fileUtils.createDirectoriesIfNotExists(targetPackageDirectory);
        File file = fileUtils.newFile(targetPackageDirectory, "Builder.java");
        Map<String, String> params = new HashMap<String, String>();
        params.put("builderPackage", builderPackage);
        writeToFile(file, "BuilderInterfaceTemplate.ftl", params);
    }

    void generateBuilderWithGenerationGap(BuilderTemplateMap builderTemplateMap, File outputDirectory, String generationGapBaseBuilderPackage, File generationGapBaseBuilderOutputDirectory) {
        builderTemplateMap.setAsGenerationGapBaseBuilder();
        builderTemplateMap.setGenerationGapBaseBuilderPackage(generationGapBaseBuilderPackage);
        generateBuilder(builderTemplateMap, generationGapBaseBuilderOutputDirectory);
        builderTemplateMap.setAsGenerationGapBuilder();
        generateBuilder(builderTemplateMap, outputDirectory);
    }

    void generateBuilder(BuilderTemplateMap builderTemplateMap, File outputDirectory) {

        String builderPackage = builderTemplateMap.isGeneratioGapBaseBuilder() ? builderTemplateMap.getGenerationGapBaseBuilderPackage() : builderTemplateMap.getBuilderPackage();
        File targetPackageDirectory = getTargetPackageDirectory(outputDirectory, builderPackage);
        fileUtils.createDirectoriesIfNotExists(targetPackageDirectory);

        String targetClass = (String) builderTemplateMap.getTargetClassName();
        String filename = targetClass + (builderTemplateMap.isGeneratioGapBaseBuilder() ? "Base" : "") + "Builder.java";
        File file = fileUtils.newFile(targetPackageDirectory, filename);

        if (builderTemplateMap.isGeneratioGapBuilder() && file.exists()) {
            // don't overwrite sub classes in generation gap mode, they are for customising
            return;
        }

        fileUtils.createFileIfNotExists(file);
        String templateName = "BuilderTemplate.ftl";
        writeToFile(file, templateName, builderTemplateMap);
    }

    private void writeToFile(File file, String templateName, Object templateDataModel) {
        try {
            FileWriter fw = new FileWriter(file);
            Configuration cfg = createFreemarkerConfiguration();
            Template template = cfg.getTemplate(templateName);
            template.process(templateDataModel, fw);
            fw.flush();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private File getTargetPackageDirectory(File outputDirectory, String builderPackage) {
        return fileUtils.newFile(outputDirectory, builderPackage.replaceAll("\\.", "/"));
    }
    
    private Configuration createFreemarkerConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), "/uk.co.buildergenerator");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
    }

}
