import cats._
import cats.data._
import cats.implicits._

package object wolfendale {

  implicit val foldableSet: Foldable[Set] =
    new Foldable[Set] {
      override def foldLeft[A, B](fa: Set[A], b: B)(f: (B, A) => B): B =
        fa.foldLeft(b)(f)
      override def foldRight[A, B](fa: Set[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] =
        fa.foldRight(lb)(f)
    }
}
