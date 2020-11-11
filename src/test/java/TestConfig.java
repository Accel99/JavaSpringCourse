import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;

@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = {"ru.education.jpa"})
@EntityScan(basePackages = {"ru.education.entity"})
public class TestConfig {
}
