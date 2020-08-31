package com.company;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {SystemOutModule.class, LoginCommandModule.class, HelloWorldModule.class, UserCommandsModule.class})
interface CommandRouterFactory {
    CommandRouter router();
}
