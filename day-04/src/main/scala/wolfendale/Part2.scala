package wolfendale

object Part2 extends App {

  val passwords = 264360 to 746325

  val validPasswords = for {
    password <- passwords
    if TweakedPasswordAnalyzer(password.toString).validate
  } yield password

  val result = validPasswords.size

  println(result)
}
