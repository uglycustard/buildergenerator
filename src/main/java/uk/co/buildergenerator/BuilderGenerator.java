package uk.co.buildergenerator;

import java.io.File;
import java.util.List;

/**
 * A tool to auto generate builders following the Builder pattern for an object
 * graph of JavaBeans.
 * <p>
 * Specify the object graph root class and <code>BuilderGenerator</code> will
 * generate a builder following a consistent pattern for each JavaBean in the
 * entire object graph.
 * 
 * <h3>Usage</h3>
 * To generate builders using the default builder package and
 * output folder chosen by <code>BuilderGenerator</code> call one of the following static utility methods:
 * <p>
 * <code>
 * BuilderGenerator.generateBuilders(MyObjectGraphRoot.class);<br/>
 * BuilderGenerator.generateBuilders("com.example.MyObjectGraphRoot");
 * </code>
 * <p>
 * To generate builders in either a package and/or output folder other than those chosen by <code>BuilderGenerator</code>:
 * <p>
 * <code>
 * BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);<br/>
 * bg.setBuilderPackage("com.example.mypackage");<br/>    
 * bg.setOutputDirectory("/my/output/folder");<br/>    
 * bg.generateBuilders();
 * </code>
 * <p>
 * To ignore properties in a specified class:
 * <p>
 * <code>
 * BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);<br/>
 * bg.addPropertyToIgnore(MyObjectGraphRoot.class, "thePropertyToIgnore");<br/>    
 * bg.generateBuilders();
 * </code>
 * <p>
 * To ignore classes in the object graph:
 * <p>
 * <code>
 * BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);<br/>
 * bg.addClassToIgnore(MyObjectToIgnore.class);<br/>    
 * bg.generateBuilders();
 * </code>
 * <p>
 * See: <br/>
 * {@link #generateBuilders(Class) generateBuilders}
 * {@link #generateBuilders(String) generateBuilders}
 * {@link #setOutputDirectory(String) setOutputDirectory}
 * {@link #setBuilderPackage(String) setBuilderPackage}
 * {@link #addPropertyToIgnore(Class, String) addPropertyToIgnore}
 * {@link #addPropertyToIgnore(String, String) addPropertyToIgnore}
 * {@link #addClassToIgnore(Class) addClassToIgnore}
 * {@link #addClassToIgnore(String) addClassToIgnore}
 * 
 * 
 * @see <a href="http://www.buildergenerator.co.uk">www.buildergenerator.co.uk</a>
 * @author uglycustard@buildergenerator.co.uk
 * 
 */
public class BuilderGenerator {

	/**
	 * The default output directory where generated builder sources will be
	 * written.
	 * 
	 * This is the current directory the Java process is executed from.
	 */
	public static final String DEFAULT_OUTPUT_DIRECTORY = ".";

	/**
	 * Generate builders for an object graph whose graph root is
	 * <code>rootClassName</code>.
	 * <p>
	 * Use this utility method when the default builder package and default
	 * output directory are sufficient.
	 * </p>
	 * See: <br/>
	 * {@link #setOutputDirectory(String) setOutputDirectory}
	 * {@link #setBuilderPackage(String) setBuilderPackage}
	 * 
	 * @param rootClassName
	 *            the object graph root class name
	 * @throws ClassNotFoundException if the root class cannot be found on the classpath
	 */
	public static void generateBuilders(String rootClassName)
			throws ClassNotFoundException {
		new BuilderGenerator(rootClassName).generateBuilders();
	}

	/**
	 * Generate builders for an object graph whose graph root is
	 * <code>rootClass</code>.
	 * <p>
	 * Use this utility method when the default builder package and default
	 * output directory are sufficient.
	 * </p>
	 * See: <br/>
	 * {@link #setOutputDirectory(String) setOutputDirectory}
	 * {@link #setBuilderPackage(String) setBuilderPackage}
	 * 
	 * @param rootClass
	 *            the object graph root class
	 */
	public static void generateBuilders(Class<?> rootClass) {
		new BuilderGenerator(rootClass).generateBuilders();
	}

	private final Class<?> rootClass;
	private final BuilderWriter builderWriter;
	private final FileUtils fileUtils;
	private final PropertiesToIgnore propertiesToIgnore = new PropertiesToIgnore();
	private final ClassesToIgnore classesToIgnore = new ClassesToIgnore();
	private String builderPackage;
	private String outputDirectory;

	/**
	 * Construct a <code>BuilderGenerator</code> for an object graph whose graph
	 * root is <code>rootClassName</code>.
	 * 
	 * @param rootClassName
	 *            the object graph root class name
	 * @throws ClassNotFoundException if the root class cannot be found on the classpath
	 */
	public BuilderGenerator(String rootClassName) throws ClassNotFoundException {
		this(Class.forName(rootClassName));
	}

	/**
	 * Construct a <code>BuilderGenerator</code> for an object graph whose graph
	 * root is <code>rootClass</code>.
	 * 
	 * @param rootClass
	 *            the object graph root class
	 */
	public BuilderGenerator(Class<?> rootClass) {
		this(rootClass, new BuilderWriter(new FileUtils()), new FileUtils());
	}

	BuilderGenerator(Class<?> rootClass, BuilderWriter builderWriter, FileUtils fileUtils) {
	    this.rootClass = rootClass;
        this.builderWriter = builderWriter;
        this.fileUtils = fileUtils;
        setBuilderPackage(deriveDefaultBuilderPackage(rootClass));
        setOutputDirectory(DEFAULT_OUTPUT_DIRECTORY);
    }

    private String deriveDefaultBuilderPackage(Class<?> rootClass) {

		return rootClass.getPackage().getName() + "builder";
	}

	/**
	 * Generate the builders.
	 */
    public void generateBuilders() {

        File outputDirectoryFile = fileUtils.newFile(getOutputDirectory());
        fileUtils.createDirectoriesIfNotExists(outputDirectoryFile);
        BuilderTemplateMapCollector builderTemplateMapCollector = new BuilderTemplateMapCollector(getRootClass(), getBuilderPackage(), propertiesToIgnore, classesToIgnore);
        List<BuilderTemplateMap> builderTemplateMapList = builderTemplateMapCollector.collectBuilderTemplateMaps();
        
        for (BuilderTemplateMap builderTemplateMap : builderTemplateMapList) {
            builderWriter.generateBuilder(builderTemplateMap, outputDirectoryFile);
        }
    }

	Class<?> getRootClass() {
		return rootClass;
	}

	String getBuilderPackage() {
		return builderPackage;
	}

	String getOutputDirectory() {
		return outputDirectory;
	}

	/**
	 * Override the default builder package by calling this method.
	 * 
	 * @param builderPackage
	 *            the package that generated builders will be written to.
	 */
	public void setBuilderPackage(String builderPackage) {
		this.builderPackage = builderPackage;
	}

	/**
	 * Override the default output directory by calling this method.
	 * 
	 * @param outputDirectory
	 *            the output directory that generated builders will be written
	 *            to.
	 */
	public void setOutputDirectory(String outputDirectory) {
		this.outputDirectory = outputDirectory;
	}
	
	/**
     * @deprecated see {@link #addPropertyToIgnore(String, String) addPropertyToIgnore}
	 * 
	 * @param targetClassName the name of the class in which the property is to be ignored
	 * @param propertyName the name of the property in the target class to ignore
	 * @throws ClassNotFoundException if the target class cannot be found on the classpath
	 */
	@Deprecated
	public void setPropertyToIgnore(String targetClassName, String propertyName) throws ClassNotFoundException {
	    addPropertyToIgnore(targetClassName, propertyName);
	}

   /**
     * Specify a property to ignore in a given class.  
     * 
     * Ignored properties will not appear in the generated builder.
     * 
     * @param targetClassName the name of the class in which the property is to be ignored
     * @param propertyName the name of the property in the target class to ignore
     * @throws ClassNotFoundException if the target class cannot be found on the classpath
     */
	public void addPropertyToIgnore(String targetClassName, String propertyName) throws ClassNotFoundException {
	    addPropertyToIgnore(Class.forName(targetClassName), propertyName);
	}

	/**
	 * @deprecated see {@link #addPropertyToIgnore(Class, String) addPropertyToIgnore}
	 * 
     * @param targetClass the class in which the property is to be ignored
     * @param propertyName the name of the property in the target class to ignore
	 */
	@Deprecated
	public void setPropertyToIgnore(Class<?> targetClass, String propertyName) {
	    addPropertyToIgnore(targetClass, propertyName);
	}

    /**
     * Specify a property to ignore in a given class.  
     * 
     * Ignored properties will not appear in the generated builder.
     * 
     * @param targetClass the class in which the property is to be ignored
     * @param propertyName the name of the property in the target class to ignore
     */
    public void addPropertyToIgnore(Class<?> targetClass, String propertyName) {
        propertiesToIgnore.addPropertyToIgnore(targetClass, propertyName);
    }

    /**
	 * Add classes that <code>BuilderGenerator</code> will ignore.  I.e. no builder will
	 * be generated for the given class.
	 * <p>
	 * Classes with properties of the ignored types will still have a <code>with<i>Property</i></code> method generated
	 * to set the property, but the parameter to the <code>with<i>Property</i></code> method will be the property type, 
	 * not a builder. 
	 * 
	 * @param classToIgnore class to ignore
	 */
    public void addClassToIgnore(Class<?> classToIgnore) {
        addClassToIgnore(classToIgnore.getCanonicalName());
    }

    /**
     * Add classes that <code>BuilderGenerator</code> will ignore.  I.e. no builder will
     * be generated for the given class.
     * <p>
     * Classes with properties of the ignored types will still have a <code>with<i>Property</i></code> method generated
     * to set the property, but the parameter to the <code>with<i>Property</i></code> method will be the property type, 
     * not a builder.
     * <p>
     * Wild cards can be used to ignore groups of classes or entire packages (and sub packages), e.g:
     * <p>
     * <code>com.example.MyCla*</code> - will ignore all classes starting with com.example.MyCla<br>
     * <code>com.example*</code> - will ignore all classes in the com.example package (and sub packages)<br>
     * 
     * @param classNameToIgnore The class name (with or without trailing wild card) to ignore
     */
    public void addClassToIgnore(String classNameToIgnore) {
        classesToIgnore.add(classNameToIgnore);
    }
}
