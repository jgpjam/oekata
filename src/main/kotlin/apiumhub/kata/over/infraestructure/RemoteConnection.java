package apiumhub.kata.over.infraestructure;

import arrow.core.Either;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static apiumhub.kata.over.domain.Transformation.*;

@Component
@ConstructorBinding
@ConfigurationProperties
public class RemoteConnection implements Computation {
    Logger logger = LoggerFactory.getLogger(RemoteConnection.class);

    @Value("${remote.node1.ip}")
    private String ipFirstNode;

    @Value("${remote.node1.user}")
    private String userFirstNode;

    @Value("${remote.node1.password}")
    private String passFirstNode;

    @Value("${remote.node2.ip}")
    private String ipSecondNode;

    @Value("${remote.node2.user}")
    private String userSecondNode;

    @Value("${remote.node1.password}")
    private String passSecondNode;

    @NotNull
    @Override
    public Either<ValidTransformation, FailedTransformation> transformVowels(@NotNull String input) {
        logger.info("RemoteConnectionImplementation - Transforming vowels");
        try {
            ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

            RemoteTask taskFirstNode = new RemoteTask(ipFirstNode, userFirstNode, passFirstNode);
            RemoteTask taskSecondNode = new RemoteTask(ipSecondNode, userSecondNode, passSecondNode);

            executor.execute(taskFirstNode);
            executor.execute(taskSecondNode);

            return new Either.Right(new ValidTransformation(""));
        } catch (Exception e) {
            return new Either.Left(new Object());
        }

    }

    @NotNull
    @Override
    public Either<ValidTransformation, FailedTransformation> alternateLetters(@NotNull String input) {
        return new Either.Right(new ValidTransformation(""));
    }
}
