#import "RudderSingularFactoryCordova.h"
#import "RudderSDKCordovaPlugin.h"
#import <Cordova/CDV.h>
#import <RudderSingularFactory.h>


@implementation RudderSingularFactoryCordova : CDVPlugin

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(finishLaunching:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)finishLaunching:(NSNotification *)notification
{
}

- (void)setup:(CDVInvokedUrlCommand*)command
{
    [RudderSDKCordovaPlugin addFactory:[RudderSingularFactory instance]];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}

@end
