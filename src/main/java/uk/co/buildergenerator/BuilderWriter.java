package uk.co.buildergenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

// TODO: TDD me
class BuilderWriter {

    private final FileUtils fileUtils;

    BuilderWriter(FileUtils fileUtils) {
        this.fileUtils = fileUtils;
    }

    void generateBuilderWithGenerationGap(BuilderTemplateMap builderTemplateMap, File outputDirectory, String generationGapBaseBuilderPackage) {
        builderTemplateMap.setAsGenerationGapBaseBuilder();
        builderTemplateMap.setGenerationGapBaseBuilderPackage(generationGapBaseBuilderPackage);
        generateBuilder(builderTemplateMap, outputDirectory);
        builderTemplateMap.setAsGenerationGapBuilder();
        generateBuilder(builderTemplateMap, outputDirectory);
    }

    void generateBuilder(BuilderTemplateMap builderTemplateMap, File outputDirectory) {

        try {
            
            String builderPackage = builderTemplateMap.isGeneratioGapBaseBuilder() ? builderTemplateMap.getGenerationGapBaseBuilderPackage() : builderTemplateMap.getBuilderPackage();
            File targetPackageDirectory = fileUtils.newFile(outputDirectory, builderPackage.replaceAll("\\.", "/"));
            fileUtils.createDirectoriesIfNotExists(targetPackageDirectory);
            
            String targetClass = (String) builderTemplateMap.getTargetClassName();
            String filename = targetClass + (builderTemplateMap.isGeneratioGapBaseBuilder() ? "Base" : "") + "Builder.java";
            File f = fileUtils.newFile(targetPackageDirectory, filename);
            
            if (builderTemplateMap.isGeneratioGapBuilder() && f.exists()) {
                // don't overwrite sub classes in generation gap mode, they are for customising
                return;
            }
            
            fileUtils.createFileIfNotExists(f);
            FileWriter fw = new FileWriter(f);
            
            Configuration cfg = createFreemarkerConfiguration();
            Template template = cfg.getTemplate("BuilderTemplate.ftl");
            
            template.process(builderTemplateMap, fw);
            
            fw.flush();
            fw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    
    private Configuration createFreemarkerConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(this.getClass(), "/uk.co.buildergenerator");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
    }

}
