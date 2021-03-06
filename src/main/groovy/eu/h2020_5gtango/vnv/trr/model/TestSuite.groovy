package eu.h2020_5gtango.vnv.trr.model

import groovy.transform.EqualsAndHashCode
import io.swagger.annotations.ApiModelProperty

import javax.validation.constraints.NotNull

@EqualsAndHashCode
class TestSuite {
    @ApiModelProperty(required = true)
    @NotNull
    String name

    @ApiModelProperty(required = true)
    @NotNull
    String version

    String status

    String generateId(){
        "$name:$version"
    }
}
