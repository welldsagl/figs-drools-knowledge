# Business Central

`Business Central` is a full featured web application for the visual composition of custom business rules and processes.

The tool allows to control and version the whole knowledge project, however it has some limitations that
lead us to decide using it just as a tool for creating and editing single files and then export them to our 
development environment.

## How to start

You can start a Business Central Docker container by running the following command. Note that we may have to 
increase Docker memory to 4GB (preferences > resources > memory):

```
docker run -p 8083:8080 -p 8001:8001 -d -e "JAVA_OPTS=-Xms2048m -Xmx4096m -XX:MetaspaceSize=96M -XX:MaxMetaspaceSize=2048m -Djava.net.preferIPv4Stack=true -Dfile.encoding=UTF-8" --name jbpm-workbench jboss/business-central-workbench-showcase:latest
```

You can then open the business-central on the browser visiting http://localhost:8083/business-central and access
the admin console with both username and password `admin`.

Take a look at https://hub.docker.com/r/jboss/business-central-workbench-showcase/ for further information.

PS: you can see container logs running the command

```
docker logs -f <container_id>
```

## How to import a project

Login, then click on the `Design` icon.

At first, you have to create a `Space` where your projects will be saved. Click on
`Add Space` and type, for example, 'Fixture', then confirm.

Go into the newly created space and choose `Import project` option.

Paste https://github.com/welldsagl/figs-drools-knowledge on the URL field and click on `Import` button, then
select the `Drools Knowledge` project and click on `OK` in the upper-right corner of the view.

We use a mirrored GitHub repository instead of the GitLab one, because the Business Central does not support
importing projects from our GitLab space. The two repositories are automatically synchronized, but sometimes
the synchronization is not immediate.

A success message will be prompted, wait some minutes for file indexing to complete (maybe take a look at the logs for
keeping track of the progress).

By default, you will be placed on the `master` branch, by clicking on the breadcrumb on the top of the view you can 
switch to another branch.

## Navigating the Project

The list of assets will be prompted, we can click on one asset in order to see its implementation.

After opening an asset, you can open the left drawer in order to navigate through the knowledge packages.

You can also import an asset, or create a new one. We use five kinds of assets:

-  Data objects
-  DRL files
-  Enumeration files
-  Guided decision tables
-  Guided rule templates

## Data objects

Data objects are the Java POJOs placed in the `component` package. The Business Central allows you to manage
their properties, but it is much easier to handle that kind of files on your IDE. 

If you decide to handle POJOs manually (via IDE), remember that for each property you have to:

-  Define the variable
-  Define getter and setter
-  Set the property in the constructor. 

## DRL files (super-rules and metadata)

We used DRL files in two cases:

-  To define super-rules.
-  To define simple Java-like utility functions that are used on the right side of the rules.

```
package ch.welld.schindler.fixture.droolsknowledge.components.fixtures;

import java.util.Map;
import java.util.HashMap; // imports used in the utility function

// a super rule
rule "DT Buttons"
    when
        FixtureConfiguration( fixtureFamily == "DT" )
    then
end

// a utility function
function Map<String, Object> createLabelMetadata(FixtureConfiguration config) {
    Map<String, Object> m = new HashMap<String, Object>();
    m.put("label", config.getLabel());
    return m;
}
```

It is quite the same to write this files in the IDE or in the Business Central. IDE will only help you with imports
and autocomplete of Java code.

## Enumeration files

Enumeration files are utility files where we can save all possible values of POJOs properties. This is very useful
because they will allow us to select property values on decision tables and guided rule templates, instead of typing
free text. This prevents us from typing invalid property values.

Both Business central and IDE can be used to edit these files.

Note that enumeration files are package specific. They are not available outside the package
where they are defined.

Every row defines the possible values of a property.

```
'BuzzerConfiguration.buzzerType' : ['HAIRLINE','MIRROR']
'BuzzerConfiguration.fixtureFamily' : ['DT','AT','MT','GST']
'BuzzerConfiguration.cableType' : ['PVC/CCC','UL','HLF']
```

## Decision tables

Decision tables help us in creating a set of similar rules that differ only for some variable specific values.

We use decision tables to define:

-  `Basic` family rules.
-  `Additional` material rules.
-  (rarely) `Cable` rules.

In order to create a decision table you have to select `Add asset` option on the project page of the Business Central
and then (obviously) `Guided Decision Table`. Give a name to your asset, choose the package where you want to save it
(if you want to create a decision table for `buzzer` component, select `ch.welld...components.buzzer`), check
`Use Wizard` checkbox and select a `Rule order` hit policy (it means that all the matching rules will be triggered).

### Creation wizard

#### Step 1 - Summary

Just leave everything as is.

#### Step 2 - Imports

Generally we want to create a set of rules saying: `if a ComponentConfiguration matches some constraints, then add
a Material`. We do not need to import the specific `ComponentConfiguration` class, because it is present in the same 
package of the decision table, but we need to import the `Material`, `Cable` or `Family` class that we want to insert 
into the knowledge, in order to create the right part of the rules.

#### Step 3 - Add Fact Patterns

Here is where we select the objects we will consider for pattern matching. Rules will check properties of the
ComponentConfiguration, so we move that to the list of chosen patterns. Optionally we can give a name to the
object (`f1` by default).

#### Step 3 - Add Constraints

Here is were we define the constraint that will be present in the left part of the rule. To do so, we have
to select the previously added ComponentConfiguration, select the field we want to set a constraint on and define
the specific condition.

We have to define a mandatory column header and operator (`equal to`, `contained in a list of values`, ...).

See that if you have already defined that property in an enumeration file, the list of possible values is already
defined (see `default value` select).

#### Step 4 - Add actions to update Facts

We do not need this step. You can directly jump to step 5.

#### Step 5 - Add actions to insert Facts

Here we can define the right part of the rule. Move `Material` (or the class you want to insert into the knowledge) 
to the list of chosen patterns and move to the list of chosen fields that properties you want to set.

As an example, if you want to insert a Material, select at least `familyCode` and `materialCode`. You only have to
set the column header of each column and then click on Finish button (you can skip step 6).

### Decision table environment

Once the decision table has been created, you can fill the cells as you need. You can select a common super-rule 
in the columns tab. In every moment you can also see the DRL resulting from the compilation of the decision table
by opening the source tab. Click on save and then download to get the `.gdst` file to paste into the project folder.
 
Empty cells will be skipped in rules creation, that means that rules will not consider that constraint during pattern 
matching.
 
![indicators](./docs/images/indicators.png)

As an example in the previous image we have two rules where

-  indicator type needs to be match in both rules
-  display size is considered only in the second rule
-  mounting type is skipped in both rules.

Those two rules compile as:

```
rule "Row 1 CopIndicatorsBasic"
  dialect "mvel"
  when
    f1 : IndicatorConfiguration( indicatorType == "TFT" )
  then
    ...
end

rule "Row 2 CopIndicatorsBasic"
  dialect "mvel"
  when
    f1 : IndicatorConfiguration( indicatorType == "EDS" , displaySize == "4.3" )
  then
    ...
end
```

### Tricks, bugs and limitations

We faced some problems during the usage of guided decision tables, sometimes we found a solution, sometimes a 
workaround, sometimes we had to migrate to Guided rule templates.

- `Metadata field`: Metadata is a `Map` class attribute of the materials. There is not an easy way to 
set its value inside the decision table. We solved this problem by defining a function in a package
specific DRL file whose job is to return a map containing the metadata, given the component configuration.
It is quite easy to use that function in the decision table by putting the call to the function in the
value list for the metadata column. As an example, to use the following function, we will write 
`createLabelMetadata(f1)` in the value list field (remember that `f1` is the default name of the
ComponentConfiguration object).

```
// Here we create a metadata map containing the label of a button
function Map<String, Object> createLabelMetadata(FixtureConfiguration config) {
    Map<String, Object> m = new HashMap<String, Object>();
    m.put("label", config.getLabel());
    return m;
}
```

-  `Boolean empty values`: Business central does not allow to leave boolean cells empty. Boolean cells are
displayed as checked or unchecked checkboxes, meaning that the property has to be true or false, but you cannot
skip its evaluation. You have two ways to resolve this problem: the first way is to define a custom 
`true,false` value list in the value option property of the column configuration: instead of seeing the checkbox you 
will see a select with those two values, and you will also be able to delete the value in the cell. 
Otherwise, you can use the self-made `NullableBoolean` enum (this is useful in guided rule templates, where you
cannot use the default values trick).

-  In general, guided decision tables are not the right tool to handle logical disjunctions (`or`). We need
that kind of pattern matching for getting the cables (remember that their insert can be triggered both from
a ComponentConfiguration OR a CableRequest), so we decided to use the guided rule templates to handle them.
To be more precise, we could use the decision tables to define cable rules, but we would have to duplicate the
provided information: we would have to define a rule for matching ComponentConfiguration and one for
matching the CableRequest. We decided to avoid this redundancy.

## Guided rule templates

As told before, guided decision tables do not allow to define logical disjunctions. Instead guided rule templates
give us a suitable tool for doing so.

Almost all cable rules have been defined by this tool. 

In order to create a new guided rule template you have to select `Add asset` option on the project page of the 
Business Central and then `Guided rule template`. Give a name to your asset, choose the package where you want to save 
it and you will directly go to the specific environment.
Once we ends editing you rules, click on save and then download to get the `.template` file to paste into 
the project folder.

### Rules creation

Here you can define the template that will be filled by each rule. At first, you can decide if you want to extend a 
common super-rule.

By clicking on the plus icon on the right of the `when` label you can add a new object to the pattern matching set.
You can also use more complicated patterns, such as `Any of the following are true` (which is the one we used to define
cable rules template).

By clicking on the added object you can add a restriction on a specific field or a multiple field constraint, choose
the constraint type and decide to evaluate the constraint against a constant literal value or a template value. If you 
choose the template key option (choose an adequate name, maybe starting with `$`) you will see a new column in the data 
tab. In every cell of the column you can set the value to evaluate the constraint against.

You can use the same pattern to create the right part of the rule. We often used the free drl option because we wanted
to use a helper method we defined previously, in order to create the new cable object to be returned after matching both 
ComponentConfiguration or CableRequest. Take a look at any cable rules file and at `CableHelper` Java class in order 
to better understand this process.

![cables](./docs/images/cables.png)

In the previous image we defined a rule which matches

- A BuzzerConfiguration whose cableType is equal to `$cableType` variable value

OR

- A CableRequest whose familyCode equal is to `$cableFamilyCode` and materialCode is equal to `$cableStandardCode` or
`$cableOnCommissionCode`.

In the right part of the rule to get the matching object (`Tuple`) and insert a cable created by the helper method.
Note that we can still use template variable in the free DRL area by using the `@{<variable_name>}` signature.

So in the Data tab table we have 5 columns. Four from the left part of the rule (some of them reused in the right
part) and one (`$cableStandardLength`) from the right part.

![cables data](./docs/images/cables-data.png)

The first rule will compile as:

```
rule "BuzzerCables_0"
  dialect "mvel"
  when 
    ( 
      BuzzerConfiguration( cableType == "PVC/CCC" )
      or 
      CableRequest( familyCode == "57634502" , materialCode == "57630754" || materialCode == "57630782" ) 
    )
  then
    Tuple tuple = drools.getTuple();
    insert(
      CableHelper.createCable(tuple, "57634502",  "57630754", 900, "57630782")
    );
end
```
