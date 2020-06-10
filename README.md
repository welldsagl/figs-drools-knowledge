# Schindler Fixture - Drools Knowledge

`Drools Knowledge` allows us to match a component configuration to a list of material codes.

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

For a more detailed technical overview of the project check the [Overview of the project](./docs/OVERVIEW.md).

## Prerequisites

-  Java 8
-  Maven

## Build

By running `mvn package` we compile through the `kie-maven-plugin` which produces a `kjar` package.

A `kjar` is a common `jar` with a `kmodule.xml` file and a set of knowledge files in its
`resources` directory.

The result of the build can be set as a dependency in any Kogito Maven project (in our case the
`Material rules engine`) by using the [Kogito Kjar Maven Plugin](https://github.com/welldsagl/kogito-kjar-maven-plugin). 

## Test

To execute unit tests: `mvn test`.

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of
conduct, and the process for submitting pull requests to us.

## Authors

* **Ignazio Secci** - *Developer* - [isecci](https://gitlab.welld.io/isecci)
* **Amedeo Zucchetti** - *Developer* - [azucchetti](https://gitlab.welld.io/azucchetti)
* **Matteo Codogno** - *Configuration* - [mcodogno](https://gitlab.welld.io/mcodogno)
* **Simone D'Avico** - *Configuration* - [sdavico](https://gitlab.welld.io/sdavico)

## License

The license for this project is private.
