#import "RudderSDKCordovaPlugin.h"
#import "Utils.h"
#import <Cordova/CDV.h>


@implementation RudderSDKCordovaPlugin : CDVPlugin

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(finishLaunching:) name:UIApplicationDidFinishLaunchingNotification object:nil];
}

- (void)finishLaunching:(NSNotification *)notification
{
}

- (void)initialize:(CDVInvokedUrlCommand*)command
{
    [RSLogger logInfo:@"Initializing Rudder Cordova SDK"];
    NSString* writeKey = [command.arguments objectAtIndex:0];
    RSConfig* config = [Utils getRudderConfig:[command.arguments objectAtIndex:1]];
    RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
    [RSClient getInstance:writeKey config:config options:option];
    [RSLogger logInfo:@"Initialized Rudder Cordova SDK"];
}

- (void)identify:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Identify call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* userId = [command.arguments objectAtIndex:0];
        NSDictionary* userTraits = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance] identify:userId traits:userTraits options:option];
    }];
}

- (void)group:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Group call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* groupId = [command.arguments objectAtIndex:0];
        NSDictionary* groupTraits = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance]group:groupId traits:groupTraits options:option];
    }];
}

- (void)track:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Track call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* eventName = [command.arguments objectAtIndex:0];
        NSDictionary* eventProperties = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance]track:eventName properties:eventProperties options:option];
    }];
}

- (void)screen:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Screen call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* screenName = [command.arguments objectAtIndex:0];
        NSDictionary* screenProperties = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance]screen:screenName properties:screenProperties options:option];
    }];
}

- (void)alias:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Alias call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* newId = [command.arguments objectAtIndex:0];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:1]];
        [[RSClient sharedInstance] alias:newId options:option];
    }];
}

- (void)reset:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Reset call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        [[RSClient sharedInstance] reset];
    }];
}

- (void)flush:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Flush call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        [[RSClient sharedInstance] flush];
    }];
}

- (void)optOut:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the optOut call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        BOOL optOut = [command.arguments objectAtIndex:0];
        [[RSClient sharedInstance] optOut:optOut];
    }];
}

- (void)putDeviceToken:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the putDeviceToken call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* deviceToken = [command.arguments objectAtIndex:0];
        [[[RSClient sharedInstance] getContext] putDeviceToken:deviceToken];
    }];
}

- (void)setAdvertisingId:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the setAdvertisingId call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        NSString* advertisingId = [command.arguments objectAtIndex:0];
        [[[RSClient sharedInstance] getContext] putAdvertisementId:advertisingId];
    }];
}

- (void)setAnonymousId:(CDVInvokedUrlCommand*)command
{
    NSString* anonymousId = [command.arguments objectAtIndex:0];
    [RSClient setAnonymousId:anonymousId];
}

@end
