package apiumhub.kata.over.domain

import apiumhub.kata.over.domain.Transformation.*
import apiumhub.kata.over.infraestructure.LocalMachineComputation
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TransformationServiceTest {
    private val localMachineComputation = LocalMachineComputation()

    @Test
    fun should_transform_vowels_to_i() {
        val input = "DDD is basically structuring folders"
        val output = "DDD is bisicilly strictiring fildirs"

        val result = TransformationService(localMachineComputation).transform(input)

        result.mapLeft {
            assertThat(it).isEqualTo(ValidTransformation(output))
        }
    }

    @Test
    fun should_alternate_uppercase_letters() {
        val input = "test"
        val output = "TeSt"

        val result = TransformationService(localMachineComputation).alternate(input)

        result.mapLeft {
            assertThat(it).isEqualTo(ValidTransformation(output))
        }
    }
}