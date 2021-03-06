// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.fileuploads;

import de.bytefish.fileuploads.provider.IRootPathProvider;
import de.bytefish.fileuploads.provider.RootPathProvider;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SampleJerseyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        new SampleJerseyApplication()
                .configure(new SpringApplicationBuilder(SampleJerseyApplication.class))
                .run(args);
    }

    @Bean
    IRootPathProvider rootPathProvider() {
        return new RootPathProvider("./out");
    }
}