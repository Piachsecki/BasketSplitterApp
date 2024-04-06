# BasketSplitterApp Kacper Piasecki

<p align="center">
  <img src="https://github.com/Piachsecki/BasketSplitterApp/assets/104682434/5d883fda-bf71-408b-b8ad-dbe1f9706289" alt="Sublime's custom image"/>
</p>

## About The Project
The project realised as a part of the recruitment process for the OCADO Tech company. Without clear recruitments specified I decided to create a more scalable application.
Instead of using Strings and List of Strings I decided to map the JSON objects into real Data Structures
* `Product.class`
* `Delivery.class`

At the first glance this change might look hilarious and unnecessary, but
in case of growth of the app, we are able to adapt faster and more efficient to upcoming changes and provide more real and reliable outcome.
Adding fields such as:  `BigDecimal price` or `String description` or `Date approximateDaysToDeliver` are now possible and with the usage of GSON Library
they can be easily mapped from the config file.

## Recruitments
* > Twoim zadaniem jest stworzenie biblioteki, która podzieli przedmioty w koszyku klienta na grupy dostaw. Zdefiniowaliśmy już API, które chcielibyśmy wykorzystać w naszym programie
  patrz “Struktura programu i testowanie”. W celu poprawnego działania, biblioteka musi
  wczytać plik konfiguracyjny, który zawiera możliwe sposoby dostawy wszystkich
  oferowanych w sklepie produktów. Jako że ta konfiguracja nie zmienia się często,
  przechowujemy ją w pliku, który ma zostać przeczytany przez Twoją implementację
  biblioteki
* >  W związku z tym, chcemy do algorytmu przekazać absolutną ścieżkę do pliku konfiguracyjnego w
  formacie JSON. Kluczem w mapie jest nazwa produktu, a wartością - lista z możliwymi
  sposobami dostawy danego produktu:
* > Algorytm w wyniku ma zwrócić podział produktów na grupy dostawy jako mapę. Jej kluczem
  być sposób dostawy, a wartością lista produktów. Chcielibyśmy, aby:
  Algorytm dzielił produkty na możliwie minimalną liczbę grup dostaw.
  Największa grupa zawierała możliwie najwięcej produktów.

## Built With

This section should list any major frameworks/libraries used to bootstrap your project. Leave any add-ons/plugins for the acknowledgements section. Here are a few examples.
* [![Gradle][Gradle.io]][Gradle-url]
* [![Java][Java.io]][Java-url]
* [![IntelliJ][IntelliJ.io]][IntelliJ-url]
* Lombok
* JUnit 5



## Setup 
* Navigate first to the ./BasketSplitterApp folder in the terminal and then run `gradlew build` to build our application. Then type `gradlew runApp` to run our application.
* If u want to run the tests written in the application type `gradlew clean` and then  `gradlew test`
* Run `gradlew jar` to create a Fat Jar off the application!


[JUnit-url]: https://junit.org/junit5/docs/current/user-guide/
[JUnit.io]: https://brandfetch.com/junit.org?library=default&collection=logos&asset=idqgrLwtK3&view=overview

[IntelliJ.io]: https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white
[IntelliJ-url]: https://www.jetbrains.com/idea/?var=1



[Lombok.io]: https://github.com/saksham2105/lombok/blob/main/feature-image-lombok.png
[Lombok-url]: https://projectlombok.org/

[Java.io]: https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white
[Java-url]: https://www.java.com/pl/

[Gradle.io]: https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white
[Gradle-url]: https://gradle.org/
