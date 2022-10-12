package apiumhub.kata.over

import apiumhub.kata.over.domain.TransformationService
import apiumhub.kata.over.infraestructure.LocalMachineComputation
import arrow.core.left
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class OverApplication

fun main(args: Array<String>) {
	runApplication<OverApplication>(*args)

	val result = TransformationService(LocalMachineComputation()).kata("DDD is basically structuring folders")

	result.fold(
		{ println(it.payload) },
		{ println("Process has failed") }
	)
}
