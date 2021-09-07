#import <Foundation/Foundation.h>
#import <Cordova/CDV.h>
#import <Cordova/CDVPlugin.h>
#import <Rudder/Rudder.h>

@interface RudderSDKCordovaPlugin : CDVPlugin

- (void)identify:(CDVInvokedUrlCommand*)command;
- (void)group:(CDVInvokedUrlCommand*)command;
- (void)track:(CDVInvokedUrlCommand*)command;
- (void)screen:(CDVInvokedUrlCommand*)command;
- (void)alias:(CDVInvokedUrlCommand*)command;
- (void)reset:(CDVInvokedUrlCommand*)command;
- (void)flush:(CDVInvokedUrlCommand*)command;
- (void)optOut:(CDVInvokedUrlCommand*)command;
- (void)putDeviceToken:(CDVInvokedUrlCommand*)command;
- (void)setAdvertisingId:(CDVInvokedUrlCommand*)command;
- (void)setAnonymousId:(CDVInvokedUrlCommand*)command;

@end
