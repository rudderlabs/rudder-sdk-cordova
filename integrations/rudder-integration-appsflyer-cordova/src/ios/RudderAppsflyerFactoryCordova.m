#import "RudderAppsflyerFactoryCordova.h"
#import "RudderSDKCordovaPlugin.h"
#import <Cordova/CDV.h>
#import <RudderAppsflyerFactory.h>


@implementation RudderAppsflyerFactoryCordova : CDVPlugin

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(finishLaunching:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)finishLaunching:(NSNotification *)notification
{
}

- (void)setup:(CDVInvokedUrlCommand*)command
{
    [RudderSDKCordovaPlugin addFactory:[RudderAppsflyerFactory instance]];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
