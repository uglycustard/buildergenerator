package uk.co.buildergenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

// TODO: TDD me
class BuilderWriter {

    void generateBuilder(BuilderTemplateMap builderTemplateMap, File outputDirectory) {

        try {
            
            File targetPackageDirectory = new File(outputDirectory, builderTemplateMap.getBuilderPackage().replaceAll("\\.", "/"));
            
            if (!targetPackageDirectory.exists()) {
                boolean mkdirs = targetPackageDirectory.mkdirs();
                if (!mkdirs) {
                    throw new RuntimeException("failed to make output directory [" + targetPackageDirectory.getAbsolutePath() + "]");
                }
            }
            
            String targetClass = (String) builderTemplateMap.getTargetClassName();
            File f = new File(targetPackageDirectory, targetClass + "Builder.java");
            f.createNewFile();
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
