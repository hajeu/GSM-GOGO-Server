package team.gsmgogo.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import team.gsmgogo.domain.user.repository.UserQueryDslRepository;
import team.gsmgogo.job.DailyJob;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class DailyScheduler {
    private final JobLauncher jobLauncher;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final UserQueryDslRepository userQueryDslRepository;

//    @Scheduled(cron = "0 5 1 * * *")
//    public void resetLoginCount() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
//        Map<String, JobParameter<?>> confMap = new HashMap<>();
//        confMap.put("time", new JobParameter(System.currentTimeMillis(), String.class));
//        JobParameters jobParameters = new JobParameters(confMap);
//
//        jobLauncher.run(
//            new DailyJob(jobRepository, platformTransactionManager, userQueryDslRepository).resetCountJob(),
//            jobParameters
//        );
//    }

    @Scheduled(cron = "0 0 0 * * *")
    public void registerAlert() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
        Map<String, JobParameter<?>> confMap = new HashMap<>();
        confMap.put("time", new JobParameter(System.currentTimeMillis(), String.class));
        JobParameters jobParameters = new JobParameters(confMap);

        jobLauncher.run(
            new DailyJob(jobRepository, platformTransactionManager, userQueryDslRepository).resetCountJob(),
            jobParameters
        );
    }
}
