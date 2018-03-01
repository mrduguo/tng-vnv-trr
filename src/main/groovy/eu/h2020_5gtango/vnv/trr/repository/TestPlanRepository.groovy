package eu.h2020_5gtango.vnv.trr.repository

import eu.h2020_5gtango.vnv.trr.model.TestPlan
import org.springframework.data.mongodb.repository.MongoRepository

interface TestPlanRepository extends MongoRepository<TestPlan, String> {

    TestPlan findByStatus(String status)

}