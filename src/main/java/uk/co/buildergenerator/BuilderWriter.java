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

    void generateBuilder(BuilderTemplateMap builderTemplateMap, File outputDirectory) {

        try {
            
            File targetPackageDirectory = fileUtils.newFile(outputDirectory, builderTemplateMap.getBuilderPackage().replaceAll("\\.", "/"));
            fileUtils.createDirectoriesIfNotExists(targetPackageDirectory);
            
            String targetClass = (String) builderTemplateMap.getTargetClassName();
            File f = fileUtils.newFile(targetPackageDirectory, targetClass + "Builder.java");
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
