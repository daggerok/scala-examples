package daggerok

object PrimeNumbers {

  def of(ns: Stream[Int]): Stream[Int] =
    ns.head #::
      of(ns.tail.filter(n => n % ns.head != 0))

  def streamNumbers(n: Int): Stream[Int] =
    n #:: streamNumbers(n + 1)
}
