package uk.co.buildergenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class BuilderGenerator {


    private static String deriveDefaultBuilderPackage(Class<?> root) {

        return root.getPackage().getName() + "builder";
    }

    private final Class<?> rootClass;
    private final String builderPackage;
    private final String outputDirectory;

    public BuilderGenerator(String root) throws ClassNotFoundException {
        this(Class.forName(root));
    }

    public BuilderGenerator(String root, String builderPackage) throws ClassNotFoundException {
        this(Class.forName(root), builderPackage);
    }
    
    public BuilderGenerator(String root, String builderPackage, String outputDirectory) throws ClassNotFoundException {
        this(Class.forName(root), builderPackage, outputDirectory);
    }
    
    public BuilderGenerator(Class<?> root) {
        this(root, deriveDefaultBuilderPackage(root));
    }
    
    public BuilderGenerator(Class<?> root, String builderPackage) {
        this(root, builderPackage, "");
    }

    public BuilderGenerator(Class<?> root, String builderPackage, String outputDirectory) {
        this.rootClass = root;
        this.builderPackage = builderPackage;
        this.outputDirectory = outputDirectory;
    }

    // TODO: extract this method into class BuilderTemplateMapCollector
    private void collectBuilderTemplateMaps(List<BuilderTemplateMap> builderTemplateMapList, Class<?> targetClass, String builderPackage) throws ClassNotFoundException {

        for (BuilderTemplateMap builderTemplateMap : builderTemplateMapList) {
            if (builderTemplateMap.getFullyQualifiedTargetClassName().equals(targetClass.getName())) {
                return;
            }
        }
        
        BuilderTemplateMap builderTemplateMap = new BuilderTemplateMap(targetClass, builderPackage);
        builderTemplateMapList.add(builderTemplateMap);
        for (WithMethod withMethod : builderTemplateMap.getWithMethodList()) {
            if (withMethod.isBuilder()) {
                String builderTargetType = withMethod.getBuilderTargetType();
                collectBuilderTemplateMaps(builderTemplateMapList, Class.forName(builderTargetType), builderPackage);
            }
        }
    }
    
    public void generateBuilders() {

        try {
            File targetPackageDirectory = new File(outputDirectory + "/" + builderPackage.replaceAll("\\.", "/"));
            
            if (!targetPackageDirectory.exists()) {
                targetPackageDirectory.mkdirs();
            }

            List<BuilderTemplateMap> builderTemplateMapList = new ArrayList<BuilderTemplateMap>();
            collectBuilderTemplateMaps(builderTemplateMapList, rootClass, builderPackage);
            
            for (BuilderTemplateMap builderTemplateMap : builderTemplateMapList) {
                generateBuilder(builderTemplateMap, targetPackageDirectory);
            }
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void generateBuilder(BuilderTemplateMap builderTemplateMap, File targetPackageDirectory) throws IOException, TemplateException {
        String targetClass = (String) builderTemplateMap.getTargetClassName();
        File f = new File(targetPackageDirectory, targetClass + "Builder.java");
        f.createNewFile();
        FileWriter fw = new FileWriter(f);

        Configuration cfg = createFreemarkerConfiguration();
        Template template = cfg.getTemplate("BuilderTemplate.ftl");

        template.process(builderTemplateMap, fw);

        fw.flush();
        fw.close();
    }

    private Configuration createFreemarkerConfiguration() throws IOException {
        Configuration cfg = new Configuration();
        cfg.setDirectoryForTemplateLoading(new File(ClassLoader.getSystemResource("uk.co.buildergenerator").getFile()));
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        return cfg;
    }

    public static void main(String[] args) {
//        new BuilderGenerator(Target.class).generateBuilders();
    }

}
