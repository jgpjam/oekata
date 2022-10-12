package apiumhub.kata.over.infraestructure

import apiumhub.kata.over.domain.Transformation.*
import arrow.core.Either
import arrow.core.Either.*
import java.lang.Exception
import java.lang.StringBuilder
import java.util.*

class LocalMachineComputation: Computation {

    override fun transformVowels(input: String): Either<ValidTransformation, FailedTransformation> {
        return try {
            Left(ValidTransformation(
                input.replace("a","i")
                     .replace("e","i")
                     .replace("o","i")
                     .replace("u","i")
            ))
        } catch (e: Exception) {
            Right(FailedTransformation)
        }
    }

    override fun alternateLetters(input: String): Either<ValidTransformation, FailedTransformation>  {
        val result = StringBuilder()
        input.forEachIndexed { index, c ->
            if (index % 2 == 0) {
                result.append(c.toString().uppercase(Locale.getDefault()))
            } else {
                result.append(c.toString().lowercase(Locale.getDefault()))
            }
        }
        return Left(ValidTransformation(result.toString()))
    }

}