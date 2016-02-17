package slayer.repos

import org.springframework.data.mongodb.repository.MongoRepository
import slayer.domain.Configuration

/**
 * Created by sith on 2/11/16.
 */
interface ConfigurationRepository extends MongoRepository<Configuration, String> {
}
