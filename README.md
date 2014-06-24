buildergenerator
================

A tool to auto generate builders following the Builder pattern for an object graph of JavaBeans.

Simply specify the object graph root class and BuilderGenerator will generate a builder following a 
consistent pattern for each JavaBean in the entire object graph.

##Features

- Consistent pattern followed for all generated builders
- All builder methods follow the withXXX naming standard, e.g.: 
  - withCustomer(Customer customer)
- Generates a static factory method for each builder following the aXXX naming standard e.g.: 
 - aCustomer()
- Improves code readability by handling plurals and properties starting with vowels, e.g.: 
  - factory method for Customer -> aCustomer()
  - factory method for Employee -> anEmployeer()
  - with method that adds an Address to addresses property -> withAddress(Address address)
- Chains builders together for building complex types e.g.:
  - withCustomer(CustomerBuilder customer)
- Handles Java collections in a consistent way which add to the underlying collection    
- Handles initialisation of null collections directly in the generated builder
- Handles arrays and all other Java types and primitives
- Handles inherited properties in a class hierarchy
- Handles cyclic object graphs
- Ability to override the the package for the generated builders (default behaviour is to append "builder" to the package of the root class)
- Ability to specify the output folder for the generated builder source files


