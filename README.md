# testshop

-	Gebruik geen standaard waardes
-	Hergebruik geen waardes
-       Gebruik ook edge cases (extreem lang e-mailadres bijvoorbeeld) 
-	Controleer de waarde van constanten
-	Vul collecties altijd met meer dan 1 object
-	Gebruik zoveel mogelijk vaste waardes (LocalDate.of(2024, 2 16) i.p.v. LocalDate.now())
-	Gebruik zo weinig mogelijk Mockito.any() (anyString(), anyAbc… enz)
-	Controleer of de verwachte aanroepen zijn gedaan
-	Controleer dat er niet méér dan de verwachte aanroepen zijn gedaan (verifyNoMoreInteractions, verifyNoInteractions)

Keuze maken tussen JUnit asserts (assertEquals(expected, actual) en assertj asserts (assertThat(actual).isEqualto(expected).


Naamgeving testmethodes:

https://medium.com/@stefanovskyi/unit-test-naming-conventions-dd9208eadbea

Before you choose the one naming convention you have to decide first why do you actually need it, what is the purpose of it?

There are few recommendations regarding test naming:

    Test name should express a specific requirement
    Test name could include the expected input or state and the expected result for that input or state
    Test name should be presented as a statement or fact of life that expresses workflows and outputs
    Test name could include the name of the tested method or class

I will present a few examples of existing test naming conventions with:

    MethodName_StateUnderTest_ExpectedBehavior

    cons: should be renamed if method change name

    example: isAdult_AgeLessThan18_False

    MethodName_ExpectedBehavior_StateUnderTest

    cons: should be renamed if method change name

    example: isAdult_False_AgeLessThan18

    testFeatureBeingTested

    cons: “test” prefix is redundant

    example: testIsNotAnAdultIfAgeLessThan18

    FeatureToBeTested

    cons: no clue what result is expected from name

    example: IsNotAnAdultIfAgeLessThan18

    Should_ExpectedBehavior_When_StateUnderTest

    cons: duplicates `should` and `when`, long name

    example: Should_ThrowException_When_AgeLessThan18

    When_StateUnderTest_Expect_ExpectedBehavior

    cons: duplicates `when` and `expect`

    example: When_AgeLessThan18_Expect_isAdultAsFalse

    Given_Preconditions_When_StateUnderTest_Then_ExpectedBehavior — Behavior-Driven Development (BDD)

    cons: duplicates `given`, `when`, `then`; really long names

    example: Given_UserIsAuthenticated_When_InvalidAccountNumberIsUsedToWithdrawMoney_Then_TransactionsWillFail
