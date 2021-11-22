#import "RudderSDKCordovaPlugin.h"
#import "Utils.h"
#import <Cordova/CDV.h>

static NSNotification* _notification;

@implementation RudderSDKCordovaPlugin : CDVPlugin

- (void)pluginInitialize
{
    [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(finishLaunching:) name:UIApplicationDidFinishLaunchingNotification object:UIApplication.sharedApplication];

}

- (void)finishLaunching:(NSNotification *)notification
{
    _notification = notification;
}

- (void)initialize:(CDVInvokedUrlCommand*)command
{
    [self.commandDelegate runInBackground:^{
        CDVPluginResult* pluginResult = nil;
        NSString* writeKey = [Utils getStringFromArguments:command.arguments atIndex:0];
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
            if(_notification!= nil)
            {
                [[RSClient sharedInstance] trackLifecycleEvents:_notification.userInfo];
            }
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
        NSDictionary* userTraits = [Utils getDictionaryFromArguments:command.arguments atIndex:1];
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
        NSString* groupId = [Utils getStringFromArguments:command.arguments atIndex:0];
        NSDictionary* groupTraits = [Utils getDictionaryFromArguments:command.arguments atIndex:1];
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
        NSString* eventName = [Utils getStringFromArguments:command.arguments atIndex:0];
        NSDictionary* eventProperties = [Utils getDictionaryFromArguments:command.arguments atIndex:1];
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
        NSString* screenName = [Utils getStringFromArguments:command.arguments atIndex:0];
        NSDictionary* screenProperties = [Utils getDictionaryFromArguments:command.arguments atIndex:1];
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
        NSString* newId = [Utils getStringFromArguments:command.arguments atIndex:0];
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


- (void)putDeviceToken:(CDVInvokedUrlCommand*)command
{
    dispatch_sync(dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_DEFAULT, 0),
                  ^{
        NSString* deviceToken = [Utils getStringFromArguments:command.arguments atIndex:0];
        [RSClient putDeviceToken:deviceToken];
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
        NSString* advertisingId = [Utils getStringFromArguments:command.arguments atIndex:0];
        [[[RSClient sharedInstance] getContext] putAdvertisementId:advertisingId];
    });
}

- (void)setAnonymousId:(CDVInvokedUrlCommand*)command
{
    NSString* anonymousId = [command.arguments objectAtIndex:0];
    [RSClient setAnonymousId:anonymousId];
}

- (void)optOut:(CDVInvokedUrlCommand*)command
{
    if ([RSClient sharedInstance] == nil)
    {
        [RSLogger logWarn:@"Dropping the optOut call as SDK is not initialized yet"];
        return;
    }
    [self.commandDelegate runInBackground:^{
        BOOL optOut = [[command.arguments objectAtIndex:0] boolValue];
        [[RSClient sharedInstance] optOut:optOut];
    }];
}

@end
