#import "RudderSDKCordovaPlugin.h"
#import <Cordova/CDV.h>


@implementation RudderSDKCordovaPlugin : CDVPlugin

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(finishLaunching:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)finishLaunching:(NSNotification *)notification
{
    // To be implemented
}

- (void)initialize:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)identify:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)group:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)track:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)screen:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)alias:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)reset:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)flush:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)enable:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

- (void)disable:(CDVInvokedUrlCommand*)command
{
    // To be implemented
}

@end
