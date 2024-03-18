package learning.springbatch;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

@RequiredArgsConstructor
@Configuration
public class HelloJobConfiguration {

	@Bean
	public Job helloJob(
		final JobRepository jobRepository,
		@Qualifier("helloStep1") final Step helloStep1,
		@Qualifier("helloStep2") final Step helloStep2
	) {
		return new JobBuilder("helloJob", jobRepository)
			.start(helloStep1)
			.next(helloStep2)
			.build();
	}

	@Bean
	public Step helloStep1(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
		return new StepBuilder("helloStep1", jobRepository)
			.tasklet(
				(contribution, chunkContext) -> {
					System.out.println("=====================");
					System.out.println(">> step1 was executed");
					System.out.println("=====================");
					return FINISHED;
				},
				transactionManager
			)
			.build();
	}

	@Bean
	public Step helloStep2(final JobRepository jobRepository, final PlatformTransactionManager transactionManager) {
		return new StepBuilder("helloStep2", jobRepository)
			.tasklet(
				(contribution, chunkContext) -> {
					System.out.println("=====================");
					System.out.println(">> step2 was executed");
					System.out.println("=====================");
					return FINISHED;
				},
				transactionManager
			)
			.build();
	}
}
