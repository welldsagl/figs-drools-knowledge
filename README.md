# Schindler Fixture - Drools Knowledge

`Drools Knowledge` is the service that allows us to match a given component configuration to a
list of different types of material codes.

A component configuration represents a set of features of a chosen component, for example:

```json
{
  "componentType": "COP",
  "section": "Indicator",
  "indicatorType": "DMI"
}
```

This configuration yields a list of materials codes that will be used later by the `Material rules engine` service
to retrieve the corresponding materials from the `Catalog` service.

## Prerequisites

-  Java 8
-  Maven

## Drools

[Drools](https://www.drools.org/) is a Business Rules Management System (BRMS) solution. It allows to write
rules with a DSL named `DRL`.

A DRL rule is so shaped:

```DRL
// define a rule named "indicator rule 1" which inherits from "indicator super rule"
rule "indicator rule 1" extends "indicator super rule"
    when // this is called `left part` of the rule
        // when an object of type IndicatorConfiguration has property "indicatorType" which is equal to "DMI"
        IndicatorConfiguration( indicatorType == "DMI" )
    then // this is called `right part` of the rule
        // create a new object of type Material with property "materialCode" set to "123456"
        Material result = new Material();
        result.setMaterialCode("123456");
        // insert that new material into the knowledge
        insert(result);
end
```

All materials are saved in a data space called `knowledge`, that can be queried at the end of the operation in order
to retrieve all newly inserted objects (materials).

Note that inheriting another rule means that the incoming object must match the conditions of both the current rule
and the super rule.

```
rule "super rule"
    when
        IndicatorConfiguration( componentType == "COP" )
    then 
        // we leave the right part of the super-rule empty
        // as we do not want to perform any common action
end

// to match the following rule the indicator configuration must have a COP component type and a DMI indicator type
// DMI indicators whose componentType is LOP will not match this rule
rule "inheriting rule" extends "super rule"
    when
        IndicatorConfiguration( indicatorType == "DMI" )
    then 
        ...
end
```

## Build

By running `mvn install` we compile through the `kie-maven-plugin` which produces a `kjar` package.

A `kjar` is a common `jar` with a `kmodule.xml` file and a set of knowledge files in its
`resources` directory.

The result of the build can be set as a dependency in any Kogito Maven project (in our case the
`Material rules engine`) by using the [Kogito Kjar Maven Plugin](https://github.com/welldsagl/kogito-kjar-maven-plugin). 

## Code organization

Project contains both java and drl code. 

Java code contains the definition of data types and the business logic
that converts the incoming key-value generic configuration map into these known data types. It also contains some 
utility methods used by drools code.

Drools code contains the definition of all the rules that match the incoming configuration to a list of materials
and cables. 

### Java code

Java code is split into 4 main packages:

-  `components` contains all DTOs for Drools-known component configurations, such as 'IndicatorConfiguration'.
-  `materials` contains all parts of the materials hierarchy that we can create in the right part of the rules: we can 
create materials, cables or families. A family is a group of materials identified by a common code.
-  `builders` contains all the business logic to convert a generic key-value `Map` into a Drools-known component 
configuration. Builders are divided by common builders, builders for COP components, for LOP components, etc.
Builders are annotated with `@ApplicationScoped` in order to register them as potential configuration builders
onto the `Material rules engine` service. This annotation is mandatory.
-  `types` contains a set of utility interfaces (with some default method implementations) that are implemented by the 
builders. We have an interface for LOP builders, one for COP ones, etc.

### Drools code

Drools code is placed in the `resources` directory and it contains a directory for each component: we have a directory 
for indicators, one for fixtures, etc.

You can find four kinds of knowledge files in our code:

-  plain `DRL` files
-  `.gdst` guided decision table files
-  `.template` guided rules template files
-  `.enumeration` files.

While DRL and enumeration files are easily readable, `.template` and `.gdst` files are customized `xml` files
that must be opened with an external tool. That tool is called [Business Central](./docs/BUSINESS_CENTRAL.md).

Please note that `.template` and `.gdst` files will be compiled to `.drl` files, they are just a different way
to define rules. `.enumeration` files are utility files for simplifying the usage of the Business Central.

## Rules

Rules can be triggered by two kinds of objects:

-  (as told before) Component Configurations
-  Cable Requests.

Component Configurations contain all the features of the component requested by the user. Rules matching those
objects can return three kinds of materials:

-  `Material` objects, that represent a unique pair of `family code` and `material code`. Inserting a Material
into the knowledge means `Get the material identified by those two codes`. In general,
`additional` materials are added this way.

-  `Family` objects, that represent a `family code` and a `price position` type (which is always set to
`basic` because additional materials are always added as Materials and not as Families). Inserting a 
family into the knowledge means `Get all the material of that family with that price position`.
In general, `basic` materials are added this way.
 
 -  `Cable` objects, similar to Materials, with a `family code` and a `material code`, with an additional `length`
 field.  

A CableRequest is sent when we want to get the "sibling version" of a given cable. Two sibling cables are
two cables that match the same component configuration but differ for being the `standard` and `on commission`
versions. So, a Cable request is sent when you have a standard version of a cable and you want to get
the on commission one, or vice-versa.
