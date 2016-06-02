package uk.co.buildergenerator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * {@link #setGenerationGap(boolean)}
 * {@link #addPropertyToIgnore(Class, String) addPropertyToIgnore}
 * {@link #addPropertyToIgnore(String, String) addPropertyToIgnore}
 * {@link #addClassToIgnore(Class) addClassToIgnore}
 * {@link #addClassToIgnore(String) addClassToIgnore}
 * {@link #addBuilderSuperClass(Class, String)}
 * {@link #addBuilderSuperClass(Class, Class)}
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
	private final Map<String, String> builderSuperClass = new HashMap<String, String>();
	private String builderPackage;
	private String outputDirectory;
    private boolean generationGap;
    private String generationGapBaseBuilderPackage;
	private String generationGapBaseBuilderOutputDirectory;
    private final Map<Class<?>, String> collectionInitialisationTypes = new HashMap<Class<?>, String>();

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
        this.collectionInitialisationTypes.putAll(CollectionInitialisationTypes.getDefaultMappings());
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
        BuilderTemplateMapCollector builderTemplateMapCollector = new BuilderTemplateMapCollector(getRootClass(), getBuilderPackage(), propertiesToIgnore, classesToIgnore, collectionInitialisationTypes);
        List<BuilderTemplateMap> builderTemplateMapList = builderTemplateMapCollector.collectBuilderTemplateMaps();
        
        if (generationGap) {
            builderWriter.generateBuilderInteraface(getGenGapOutputDire(outputDirectoryFile), getGenGapBaseBuilerPackage());
        } else {
            builderWriter.generateBuilderInteraface(outputDirectoryFile, getBuilderPackage());
        }
        
        for (BuilderTemplateMap builderTemplateMap : builderTemplateMapList) {
        	builderTemplateMap.setSuperClass(builderSuperClass.get(builderTemplateMap.getFullyQualifiedTargetClassName()));
            if (generationGap) {
                String generationGapBaseBuilderPackage = getGenGapBaseBuilerPackage();
                File generationGapBaseBuilderOutputDirectoryFile = getGenGapOutputDire(outputDirectoryFile);
                builderWriter.generateBuilderWithGenerationGap(builderTemplateMap, outputDirectoryFile, generationGapBaseBuilderPackage, generationGapBaseBuilderOutputDirectoryFile);
            } else {
            	builderWriter.generateBuilder(builderTemplateMap, outputDirectoryFile);
            }
        }
    }

    private File getGenGapOutputDire(File defaultOutputDirectoryFileIfNotSet) {
        return getGenerationGapBaseBuilderOutputDirectory() != null ? fileUtils.newFile(getGenerationGapBaseBuilderOutputDirectory()) : defaultOutputDirectoryFileIfNotSet;
    }

    private String getGenGapBaseBuilerPackage() {
        return getGenerationGapBaseBuilderPackage() != null ? getGenerationGapBaseBuilderPackage() : getBuilderPackage();
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
	
    String getGenerationGapBaseBuilderPackage() {
        return generationGapBaseBuilderPackage;
    }

    String getGenerationGapBaseBuilderOutputDirectory() {
    	return generationGapBaseBuilderOutputDirectory;
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

    /**
     * Configure whether <code>BuilderGenerator</code> will create builders following the "Generation Gap" pattern.
     * 
     * @see <a href="http://www.buildergenerator.co.uk">www.buildergenerator.co.uk</a>
     * @param generationGap <code>true</code> to create builders following the "Generation Gap" pattern, otherwise <code>false</code> (default).
     */
    public void setGenerationGap(boolean generationGap) {
        this.generationGap = generationGap;
    }

    /**
     * Set the package of the base builders when building using the Generation Gap pattern.
     * 
     * @param generationGapBaseBuilderPackage
     *            the package that generated base builders will be written to.
     * @see #setGenerationGap(boolean)
     * @see #setGenerationGapBaseBuilderOutputDirectory(String)
     */
    public void setGenerationGapBaseBuilderPackage(String generationGapBaseBuilderPackage) {
        this.generationGapBaseBuilderPackage = generationGapBaseBuilderPackage;
    }

    /**
     * Set the output directory of the base builders when building using the Generation Gap pattern.
     * If not specified, the base builders will be written to the same output directory as the builders
     * which can be set using the <code>{@link #setOutputDirectory(String)}</code> method.
     * 
     * @param generationGapBaseBuilderOutputDirectory
     *            the output directory that generated base builders will be written to.
     * @see #setGenerationGap(boolean)
     * @see #setGenerationGapBaseBuilderPackage(String)
     * @see #setOutputDirectory(String)
     */
    public void setGenerationGapBaseBuilderOutputDirectory(String generationGapBaseBuilderOutputDirectory) {
		this.generationGapBaseBuilderOutputDirectory = generationGapBaseBuilderOutputDirectory;
    }

    /**
     * Specify a fully qualified type statement to follow the extends keyword of the generated builder for the target class.
     * 
     * E.g. if you want the generated builder to be specified as extending "com.example.Foo<T extends com.example.Bar>" then pass that string
     * as the <code>superClassStatement</code> parameter. 
     * 
     * @param targetClass the target class whose generated builder should extend the given <code>superClassStatement</code>.
     * @param superClassStatement the statement to follow the extends keyword of the generated builder, e.g. com.example.SomeClass
     * @see <a href="http://www.buildergenerator.co.uk">www.buildergenerator.co.uk</a> for more details on this feature
     * <br />
     * {@link #addBuilderSuperClass(Class, Class)}
     */
	public void addBuilderSuperClass(Class<?> targetClass, String superClassStatement) {
		builderSuperClass.put(targetClass.getName(), superClassStatement);
	}
	
	/**
	 * Specify a custom class that the generated builder for the <code>targetClass</code> should extend.
	 * <p>
	 * This method is expected to be used if you want to add a custom base class to multiple builders that share common code.
	 * It also expects that the base class uses generics in the following way such that any "with" methods in the specified base class
	 * return an instance of the actual sub class builder so method chaining can be performed.  E.g.:
	 * <p>
	 * Custom base builders should be written as follows:
	 * <pre>
	 * public abstract class MyCustomBaseBuilder{@code <T extends MyBaseBuilder<T>>} {
	 *      
	 *     public T withMyCustomSettingMethod(...) { 
	 *         getTarget().setMyCustomSetting(...);
	 *         return (T) this;
	 *     }
	 *      
	 *     protected abstract MyTargetClass getTarget();
	 * } 
	 * </pre>
	 * Implementations for the abstract <code>getTarget</code> method shown are auto generated in the sub class builders.
	 *  <p>
	 * You can then tell BuilderGenerator to set this class as the base class of the builder for the target class as follows:
	 * <pre>
	 * BuilderGenerator bg = new BuilderGenerator(MyRootClassInHierarchy.class);
	 * bg.addBuilderSuperClass(MyTargetClass.class, MyCustomBaseBuilder.class);
	 * bg.generateBuilders();
	 * </pre>
	 * This will generate a builder for MyTargetClass with the following type signature:
	 * <pre>
	 * public class MyTargetClassBuilder extends MyCustomBaseBuilder{@code <MyTargetClassBuilder>}
	 * </pre>
	 * <b>NOTE: If using this feature with the Generation Gap feature, this method must be called AFTER the setGenerationGap has been called.</b>
	 * <p>
	 * If the base class you want to extend needs a different extends statement, see 
	 * {@link #addBuilderSuperClass(Class, String)} which allows you to specify any string to follow the extends keyword.
	 * <p>
     * @param targetClass the target class whose generated builder should extend the given <code>superClassStatement</code>.
	 * @param superClass
     * @see <a href="http://www.buildergenerator.co.uk">www.buildergenerator.co.uk</a> for more details on this feature
     * <br />
     * {@link #addBuilderSuperClass(Class, String)}
	 */
	public void addBuilderSuperClass(Class<?> targetClass, Class<?> superClass) {
	    if (generationGap) {
            addBuilderSuperClass(targetClass, superClass.getName() + "<T>");
	    } else {
	        addBuilderSuperClass(targetClass, superClass.getName() + "<" + targetClass.getSimpleName() +"Builder>");
	    }
	}
	
    /**
	 * Specify the concrete class used to initialise a collection property if it is not initialised.
	 * <p>
	 * The defaults are: 
	 * <br />
	 * {@link java.util.ArrayList} for a property of type {@link java.util.Collection}
	 * <br />
	 * {@link java.util.ArrayList} for a property of type {@link java.util.List}
	 * <br />
	 * {@link java.util.HashSet} for a property of type {@link java.util.Set}
	 * <br />
	 * {@link java.util.PriorityQueue} for a property of type {@link java.util.Queue}
	 * 
	 * @param collectionInterface the collection interface class. For example <code>java.util.Set.class</code>
	 * @param concreteCollectionClassName the fully qualified class name of the concrete collection class to be instantiated to initialise a property. 
	 * For example <code>"java.util.LinkedHashSet"</code>
	 */
    public void addConcreteCollectionTypeForCollectionInterface(Class<?> collectionInterface, String concreteCollectionClassName) {
        collectionInitialisationTypes.put(collectionInterface, concreteCollectionClassName);
    }


}
