BuilderGenerator
================

A tool to auto generate builders following the Builder pattern for an object graph of JavaBeans.

Simply specify the object graph root class and BuilderGenerator will generate a builder following a 
consistent pattern for each JavaBean in the entire object graph.

##Features

- Consistent pattern followed for all generated builders
- Non intrusive and time saving 
  - no need to annotate your code
  - no need to write interfaces
  - no boiler plate coding 
- Generate a complete set of builders for an entire object graph with a single line of code
- All builder methods follow the withXXX naming standard, e.g.: 
  - withCustomer(Customer customer)
- Generates a static factory method for each builder following the aXXX naming standard e.g.: 
 - aCustomer()
- Improves code readability by handling beans starting with vowels and/or with pluralised properties, e.g.: 
  - factory method for Customer -> aCustomer()
  - factory method for Employee -> anEmployee()
  - with method that sets an Address to address property -> withAddress(Address address)
  - with method that adds an Address to addresses collection property -> withAddress(Address address)
- Chains builders together for building complex types e.g.:
  - withCustomer(CustomerBuilder customer)
- Handles Java collections in a consistent way which add to the underlying collection    
- Handles initialisation of null collections directly in the generated builder
- Handles arrays and all other Java types, primitives and enums
- Handles inherited properties in a class hierarchy
- Handles cyclic/bi-directional object graphs
- Handles interface properties and collections of interface properties
- Ability to override the package for the generated builders
- Ability to specify the output folder for the generated builder source files
- Ability to ignore specified properties in a given class in the object graph
- Ability to ignore specified classes in the object graph

##Usage

To generate builders using the default builder package and output folder chosen by BuilderGenerator call one of the following static utility methods:

```
    BuilderGenerator.generateBuilders(MyObjectGraphRoot.class);
    BuilderGenerator.generateBuilders("com.example.MyObjectGraphRoot");
```

To generate builders in either a package and/or output folder other than those chosen by BuilderGenerator:

```
    BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);
    bg.setBuilderPackage("com.example.mypackage");  // optional operation
    bg.setOutputDirectory("/my/output/folder");     // optional operation
    bg.generateBuilders();
```

To ignore properties in a specified class (choose only one of the addPropertyToIgnore methods):

```
    BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);
    bg.addPropertyToIgnore(MyObjectGraphRoot.class, "thePropertyToIgnore");          // optional operation
    bg.addPropertyToIgnore("com.example.MyObjectGraphRoot", "thePropertyToIgnore");  // optional operation
    bg.generateBuilders();
```

To ignore classes in the object graph:

```
    BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);
    bg.addClassToIgnore(MyObjectToIgnore.class);
    bg.generateBuilders();
```

##Setup

Add BuilderGenerator as a Maven dependency:

```
    <dependency>
        <groupId>uk.co.buildergenerator</groupId>
        <artifactId>buildergenerator</artifactId>
        <version>LATEST</version>
    </dependency>
```
[Search Maven Central for BuilderGenerator releases](http://search.maven.org/#search%7Cga%7C1%7Cuk.co.buildergenerator.buildergenerator)

Alternatively, you can download the source from https://github.com/uglycustard/buildergenerator

##Best Practice

You should avoid modifying generated source code so that it can be re-generated without you losing your additions.
If you want to extend the capabilities of the generated builders, follow [Martin Fowler's Generation Gap pattern](http://martinfowler.com/dslCatalog/generationGap.html).

##Issues

Please ensure you are running the latest release of BuilderGenerator before logging an issue.
If the problem still persists, please log an issue at https://github.com/uglycustard/buildergenerator/issues including a sample object graph that demonstrates the defect.
As a workaround while waiting for a response/fix to your issue, you could try using the ignore properties and/or ignore classes features of BuilderGenerator to ignore the
parts of your object graph that BuilderGenerator is having problems with.  Please do log an issue though so the problem can be addressed.
