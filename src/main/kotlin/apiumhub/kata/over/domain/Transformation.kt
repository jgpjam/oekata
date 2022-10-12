package apiumhub.kata.over.domain

sealed class Transformation(open val payload: String) {
    data class ValidTransformation(override val payload: String): Transformation(payload)
    object FailedTransformation: Transformation("")
}
