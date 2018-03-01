package eu.h2020_5gtango.vnv.trr.model

import groovy.transform.EqualsAndHashCode
import io.swagger.annotations.ApiModelProperty
import org.springframework.data.annotation.Id

import javax.validation.constraints.NotNull

@EqualsAndHashCode
class TestPlan {
    @ApiModelProperty(required = true)
    @NotNull
    @Id
    String testPlanId

    @ApiModelProperty(required = true)
    @NotNull
    List<NetworkService> networkServices

    @ApiModelProperty(required = true)
    @NotNull
    List<TestSuite> testSuites

    Date createdAt
    Date updatedAt

    String status

}
