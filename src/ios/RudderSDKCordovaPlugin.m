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
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        NSString* writeKey = [command.arguments objectAtIndex:0];
        RSConfig* config = [Utils getRudderConfig:[command.arguments objectAtIndex:1]];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [RSClient getInstance:writeKey config:config options:option];
        if([RSClient sharedInstance] == nil)
        {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        }
        else
        {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
        }
        
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    }];
}

- (void)identify:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Identify call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* userId = [command.arguments objectAtIndex:0];
        NSDictionary* userTraits = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance] identify:userId traits:userTraits options:option];
    });
}

- (void)group:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Group call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* groupId = [command.arguments objectAtIndex:0];
        NSDictionary* groupTraits = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance]group:groupId traits:groupTraits options:option];
    });
}

- (void)track:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Track call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* eventName = [command.arguments objectAtIndex:0];
        NSDictionary* eventProperties = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance]track:eventName properties:eventProperties options:option];
    });
}

- (void)screen:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Screen call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* screenName = [command.arguments objectAtIndex:0];
        NSDictionary* screenProperties = [command.arguments objectAtIndex:1];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:2]];
        [[RSClient sharedInstance]screen:screenName properties:screenProperties options:option];
    });
}

- (void)alias:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Alias call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* newId = [command.arguments objectAtIndex:0];
        RSOption* option = [Utils getRudderOption:[command.arguments objectAtIndex:1]];
        [[RSClient sharedInstance] alias:newId options:option];
    });
}

- (void)reset:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Reset call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        [[RSClient sharedInstance] reset];
    });
}

- (void)flush:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the Flush call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        [[RSClient sharedInstance] flush];
    });
}

- (void)optOut:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the optOut call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        BOOL optOut = [command.arguments objectAtIndex:0];
        [[RSClient sharedInstance] optOut:optOut];
    });
}

- (void)putDeviceToken:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the putDeviceToken call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* deviceToken = [command.arguments objectAtIndex:0];
        [[[RSClient sharedInstance] getContext] putDeviceToken:deviceToken];
    });
}

- (void)setAdvertisingId:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the setAdvertisingId call as SDK is not initialized yet"];
        return;
    }
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* advertisingId = [command.arguments objectAtIndex:0];
        [[[RSClient sharedInstance] getContext] putAdvertisementId:advertisingId];
    });
}

- (void)setAnonymousId:(CDVInvokedUrlCommand*)command
{
    NSString* anonymousId = [command.arguments objectAtIndex:0];
    [RSClient setAnonymousId:anonymousId];
}

- (void)getRudderContext:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the getRudderContext call as SDK is not initialized yet"];
        return;
    }
    
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        
        CDVPluginResult* pluginResult = nil;
        NSDictionary* context = [[[RSClient sharedInstance] getContext] dict];
        
        if(context == nil)
        {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
        }
        else
        {
            pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:context];
        }
        
        [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    });
}



@end
