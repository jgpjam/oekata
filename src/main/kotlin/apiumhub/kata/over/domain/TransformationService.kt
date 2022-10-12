package apiumhub.kata.over.domain

import apiumhub.kata.over.domain.Transformation.*
import apiumhub.kata.over.infraestructure.Computation
import arrow.core.Either
import mu.KotlinLogging

class TransformationService(private val computation: Computation) {

    private val logger = KotlinLogging.logger {}

    fun kata(input: String): Either<ValidTransformation, FailedTransformation> {
        val transformed = transform(input)
        transformed.fold(
            { return alternate(it.payload) },
            { return Either.Right(FailedTransformation) }
        )
    }

    fun transform(input: String): Either<ValidTransformation, FailedTransformation> {
        logger.info { "Initiating vowel transformation" }
        return computation.transformVowels(input)
    }

    fun alternate(input: String): Either<ValidTransformation, FailedTransformation> {
        logger.info { "Initiating letter alternation" }
        return computation.alternateLetters(input)
    }
}
