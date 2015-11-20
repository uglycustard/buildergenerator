BuilderGenerator
================

[![Build Status](https://travis-ci.org/uglycustard/buildergenerator.svg?branch=master)](https://travis-ci.org/uglycustard/buildergenerator)

A tool to auto generate builders following the Builder pattern for an object graph of JavaBeans.

Simply specify the object graph root class and BuilderGenerator will generate a builder following a 
consistent pattern for each JavaBean in the entire object graph.

##Features

- Generate a complete set of builders for an entire object graph with a single line of code
- Non intrusive and time saving 
  - no need to annotate your code
  - no need to write interfaces
  - no boiler plate coding 
- Consistent pattern followed for all generated builders
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
- Handles Java collections in a consistent way which add to the underlying collection (see below)
- Handles initialisation of null collections directly in the generated builder
- Handles arrays and all other Java types, primitives and enums
- Handles inherited properties in a class hierarchy
- Handles cyclic/bi-directional object graphs
- Handles interface properties and collections of interface properties
- Ability to override the package for the generated builders
- Ability to specify the output folder for the generated builder source files
- Ability to ignore specified properties in a given class in the object graph
- Ability to ignore specified classes in the object graph
- Ability to configure BuilderGenerator to generate builders following the Generation Gap pattern (see "Best Practice" below)
- Ability to configure custom classes that a builder should extend, works with and without using the Generation Gap mode

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

##Java Collection Handling

BuilderGenerator attempts to make working with Java Collections easier as follows:

- Creates with methods that add to the collection, e.g. given bean:
```
    public class MyBean {
        private List<Thing> things = new ArrayList<Thing>();
        public List<Thing> getThings() { return things; }
    }
```
  BuilderGenerator will include the following with method:
```
    public MyBeanBuilder withThing(Thing thing) {
        target.getThings().add(thing);
        return this;
    }
```
- Initialises null collections, e.g. given bean:
```
    public class MyBean {
        private List<Thing> things;
        public List<Thing> getThings() { return things; }
        public void setThings(List<Thing> things) { this.things = things; }
    }
```
  BuilderGenerator will include the following with method:
```
    public MyBeanBuilder withThing(Thing thing) {
        if (target.getThings() == null) {
            target.setThings(new ArrayList<Thing>();
        }
        target.getThings().add(thing);
        return this;
    }
```
- Uses collection add method if found, e.g. given bean:
```
    public class MyBean {
        private List<Thing> things = new ArrayList<Thing>();
        public List<Thing> getThings() { return things; }
        public void addThing(Thing thing) { this.things.add(thing); }
    }
```
  BuilderGenerator will include the following with method:
```
    public MyBeanBuilder withThing(Thing thing) {
        target.addThing(thing);
        return this;
    }
```
- Handles unrecognised collections and non-parameterized collection types by treating the property as a simple type

##Best Practice

You should avoid modifying generated source code so that it can be re-generated without you losing your additions.
BuilderGenerator can be configured to follow [Martin Fowler's Generation Gap pattern](http://martinfowler.com/dslCatalog/generationGap.html)
such that it will generate a base builder containing all the generated methods which the builder then extends.  It is the sub class builder
that you can then customise and BuilderGenerator will not overwrite this file if it exists.

Generics are used so that all the generated "withXXX" methods that reside in the base builder super class, return an instance of the sub class, i.e. the actual builder
that your code interfaces with.  This is to allow chaining of methods in the super class base builder with customised methods in the actual builder sub class.
You should not code against the base builder super class, only the actual builder.

The generated factory methods are still implemented in the actual builder as for generation without using the Generation Gap pattern.

To configure BuilderGenerator to generate builders following this pattern, do the following:

```
    BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);
    bg.setGenerationGap(true);
    bg.generateBuilders();
``` 

You can also configure BuilderGenerator to output the base builders into a differnt package from the actual builders as follows:

```
    BuilderGenerator bg = new BuilderGenerator(MyObjectGraphRoot.class);
    bg.setGenerationGap(true);
    bg.setGenerationGapBaseBuilderPackage("com.example.mypackage.basebuilders");
    bg.generateBuilders();
``` 


##Issues

Please ensure you are running the latest release of BuilderGenerator before logging an issue.
If the problem still persists, please log an issue at https://github.com/uglycustard/buildergenerator/issues including a sample object graph that demonstrates the defect.
As a workaround while waiting for a response/fix to your issue, you could try using the ignore properties and/or ignore classes features of BuilderGenerator to ignore the
parts of your object graph that BuilderGenerator is having problems with.  Please do log an issue though so the problem can be addressed.
