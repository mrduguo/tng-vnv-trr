package eu.h2020_5gtango.vnv.trr.rest

import eu.h2020_5gtango.vnv.trr.AbstractSpec
import eu.h2020_5gtango.vnv.trr.model.TestPlan
import eu.h2020_5gtango.vnv.trr.repository.TestPlanRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus

class TestPlanResourceTest extends AbstractSpec {

    @Autowired
    TestPlanRepository testPlanRepository

    static def testPlan = [
            networkServices: [[
                                      name   : 'name',
                                      vendor : 'vendor',
                                      version: 'version',
                              ]],
            testSuites     : [
                    [
                            name   : 'name',
                            version: 'version',
                    ],
            ],
            status         : 'CREATED',
    ]

    void "create a test plan should return id"() {
        when:
        def entity = postForEntity('/tng-vnv-trr/api/v1/test-plans', testPlan, TestPlan.class)


        then:
        entity.statusCode == HttpStatus.OK
        (testPlan = entity.body).testPlanId != null
    }

    void "update a test plan should keep the status"() {
        when:
        testPlan.status = 'SUCCESS'
        def entity = postForEntity("/tng-vnv-trr/api/v1/test-plans/${testPlan.testPlanId}", testPlan, TestPlan.class)


        then:
        entity.statusCode == HttpStatus.OK
        (testPlan = entity.body).status == 'SUCCESS'
        testPlan.createdAt != testPlan.updatedAt
    }

    void "retrieve an existing test plan should return the item"() {
        when:
        def entity = getForEntity("/tng-vnv-trr/api/v1/test-plans/${testPlan.testPlanId}", TestPlan.class)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body.testPlanId == testPlan.testPlanId
        entity.body.status == 'SUCCESS'
    }

    void "find all test plans should return all items"() {
        when:
        def entity = getForEntity("/tng-vnv-trr/api/v1/test-plans", Map.class)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body.numberOfElements >= 1
        entity.body.numberOfElements <= 5
        entity.body.content.first().testPlanId == testPlan.testPlanId
    }
}
