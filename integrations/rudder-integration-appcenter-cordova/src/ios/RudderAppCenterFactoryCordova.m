#import "RudderAppCenterFactoryCordova.h"
#import "RudderSDKCordovaPlugin.h"
#import <Cordova/CDV.h>
#import <RudderAppCenterFactory.h>


@implementation RudderAppCenterFactoryCordova : CDVPlugin

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(finishLaunching:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)finishLaunching:(NSNotification *)notification
{
}

- (void)setup:(CDVInvokedUrlCommand*)command
{
    [RudderSDKCordovaPlugin addFactory:[RudderAppCenterFactory instance]];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
