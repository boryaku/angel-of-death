package slayer.repos

import org.springframework.data.mongodb.repository.MongoRepository
import slayer.domain.War

/**
 * Created by sith on 2/11/16.
 */
interface WarRepository extends MongoRepository<War, String>{
}
