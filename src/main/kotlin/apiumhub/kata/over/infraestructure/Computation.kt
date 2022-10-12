package apiumhub.kata.over.infraestructure

import apiumhub.kata.over.domain.Transformation.*
import arrow.core.Either

interface Computation {
    fun transformVowels(input: String): Either<ValidTransformation, FailedTransformation>
    fun alternateLetters(input: String): Either<ValidTransformation, FailedTransformation>
}