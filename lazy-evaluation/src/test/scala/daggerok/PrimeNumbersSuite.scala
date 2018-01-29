package daggerok

import org.junit.runner.RunWith
import org.scalatest.{FunSuite, Matchers}
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PrimeNumbersSuite extends FunSuite with Matchers {

  val numbers = PrimeNumbers.streamNumbers(2)

  test("1st prime number should be equal to 2") {
    PrimeNumbers.of(numbers).head should be (2)
  }

  test("2nd prime number should be equal to 3") {
    PrimeNumbers.of(numbers).tail.head should be (3)
  }

  test("3rd prime number should be equal to 5") {
    PrimeNumbers.of(numbers).tail.tail.head should be (5)
  }
}
