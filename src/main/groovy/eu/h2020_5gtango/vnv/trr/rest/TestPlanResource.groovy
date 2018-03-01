package eu.h2020_5gtango.vnv.trr.rest

import eu.h2020_5gtango.vnv.trr.model.TestPlan
import eu.h2020_5gtango.vnv.trr.repository.TestPlanRepository
import io.swagger.annotations.ApiImplicitParam
import io.swagger.annotations.ApiImplicitParams
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.*

@RestController
class TestPlanResource {

    @Autowired
    TestPlanRepository testPlanRepository

    @PostMapping('/api/v1/test-plans')
    TestPlan createTestPlan(@RequestBody TestPlan testPlan) {
        testPlan.testPlanId = UUID.randomUUID().toString()
        testPlan.createdAt = new Date()
        testPlan.updatedAt = testPlan.createdAt
        testPlanRepository.insert(testPlan)
    }


    @ApiImplicitParams([
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                value = '''Sorting criteria in the format: property(,asc|desc). 
                        Default sort order is ascending. 
                        Multiple sort criteria are supported.''')
    ])
    @GetMapping('/api/v1/test-plans')
    Page<TestPlan> findTestPlans(@PageableDefault(size = 5, sort = ['updatedAt'], direction = Sort.Direction.DESC) Pageable pageable) {
        testPlanRepository.findAll(pageable)
    }

    @GetMapping('/api/v1/test-plans/{testPlanId:.+}')
    TestPlan retrieveTestPlan(@PathVariable('testPlanId') String testPlanId) {
        testPlanRepository.findOne(testPlanId)
    }

    @PostMapping('/api/v1/test-plans/{testPlanId:.+}')
    TestPlan updateTestPlan(@RequestBody TestPlan testPlan, @PathVariable('testPlanId') String testPlanId) {
        testPlan.setTestPlanId(testPlanId)
        testPlan.updatedAt = new Date()
        testPlanRepository.save(testPlan)
    }

}
