/***
 ## Copyright (c) 2018 5GTANGO [, ANY ADDITIONAL AFFILIATION]
 ## ALL RIGHTS RESERVED.
 ##
 ## Licensed under the Apache License, Version 2.0 (the "License");
 ## you may not use this file except in compliance with the License.
 ## You may obtain a copy of the License at
 ##
 ##     http://www.apache.org/licenses/LICENSE-2.0
 ##
 ## Unless required by applicable law or agreed to in writing, software
 ## distributed under the License is distributed on an "AS IS" BASIS,
 ## WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 ## See the License for the specific language governing permissions and
 ## limitations under the License.
 ##
 ## Neither the name of the SONATA-NFV [, ANY ADDITIONAL AFFILIATION]
 ## nor the names of its contributors may be used to endorse or promote
 ## products derived from this software without specific prior written
 ## permission.
 ##
 ## This work has been performed in the framework of the SONATA project,
 ## funded by the European Commission under Grant number 671517 through
 ## the Horizon 2020 and 5G-PPP programmes. The authors would like to
 ## acknowledge the contributions of their colleagues of the SONATA
 ## partner consortium (www.sonata-nfv.eu).
 */
package eu.h2020_5gtango.vnv.trr.config

import eu.h2020_5gtango.vnv.trr.AbstractSpec
import org.springframework.http.HttpStatus

class HealthSpec extends AbstractSpec {

    void "web app should be health"() {
        when:
        def entity = getForEntity('/tng-vnv-trr/health', Map.class)

        then:
        entity.statusCode == HttpStatus.OK
        entity.body.status == 'UP'
        entity.body.diskSpace.status == 'UP'

        keepServiceRunningWhenRunTheTestIndividually()
    }

    void keepServiceRunningWhenRunTheTestIndividually() {
        if (System.properties.getProperty('sun.java.command')?.endsWith('HealthSpec') || System.properties.getProperty('test.single')?.endsWith('HealthSpec')) {
            println "\nRun test in service mode, you may access the swagger api spec at:\nhttp://localhost:${port}/tng-vnv-trr/swagger-ui.html\n"
            Thread.sleep(Long.MAX_VALUE)
        }
    }

}