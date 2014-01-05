/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package work.route;

import org.apache.camel.builder.RouteBuilder;

/**
 *
 * @author TG3
 */
public class RouteBase extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        interceptFrom("direct:esl*").to("log:esl" + this.getClass().getName() + " IN ");
        interceptSendToEndpoint("direct:esl*").to("log:esl" + this.getClass().getName() + " OUT ");
        interceptFrom("direct:csl*").to("log:csl" + this.getClass().getName() + " IN ");
        interceptSendToEndpoint("direct:csl*").to("log:csl" + this.getClass().getName() + " OUT ");
    }
}
